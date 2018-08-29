package com.egroup.amazon.cloudwatch.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogsClient;
import com.amazonaws.services.logs.model.*;
import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.credential.BasicConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class AwsLogsJsonAppender extends BasicConfig {
	private String logGroupName = LOG_GROUP_NAME;
    private final ObjectMapper objectMapper;
    private AWSLogsClient awsLogsClient;
    private String awsRegionName;  
    private String logStreamName;
    private int maxLogSize = 1024;
    private long logPollTimeMillis = 3000;
    private WorkerThread workerThread;
    private ArrayBlockingQueue<LogReport> logEvents;

    public AwsLogsJsonAppender() {
    	objectMapper = new ObjectMapper().findAndRegisterModules();
    	objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    	objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    	objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    	objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void start() {
        super.start();
        addInfo(getClass().getSimpleName() + " start " + getName());
        if (logStreamName == null) {
            try {
                logStreamName = LogbackUtil.dateFormatThreadLocal.get().format(System.currentTimeMillis()) + ' ' + InetAddress.getLocalHost().getCanonicalHostName();
            } catch (Exception e) {
                logStreamName = LogbackUtil.dateFormatThreadLocal.get().format(System.currentTimeMillis());
            }
            logStreamName = logStreamName.replace(':', '.');
        }
        awsLogsClient = new AWSLogsClient(AWS_CREDENTIALS);
        if (awsRegionName != null) {
            awsLogsClient.setRegion(Region.getRegion(Regions.fromName(awsRegionName)));
        }
        CreateLogGroupRequest createLogGroupRequest = new CreateLogGroupRequest(logGroupName);
        try {
            awsLogsClient.createLogGroup(createLogGroupRequest);
        } catch (ResourceAlreadyExistsException e) {
            addInfo("Log group " + logGroupName + "already exists");
        }

        CreateLogStreamRequest createLogStreamRequest = new CreateLogStreamRequest(logGroupName, logStreamName);
        try {
            awsLogsClient.createLogStream(createLogStreamRequest);
        } catch (ResourceAlreadyExistsException e) {
            addInfo("Log stream " + logStreamName + "already exists", e);
        }

        logEvents = new ArrayBlockingQueue<>(maxLogSize * 2);
    }

    @Override
    public void stop() {
        super.stop();
        addInfo(getClass().getSimpleName() + " stopping " + getName());

        // stop and wait worker
        if (workerThread != null) {
            try {
                workerThread.join(logPollTimeMillis);
            } catch (InterruptedException e) {
            }
            workerThread.interrupt();
            workerThread = null;
        }
        addInfo(getClass().getSimpleName() + " stopped " + getName());
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            logEvents.offer(LogbackUtil.iLoggingEvent2Map(eventObject));
            // start worker
            if (workerThread == null) {
                synchronized (this) {
                    if (workerThread == null) {
                        workerThread = new WorkerThread(getClass().getSimpleName() + ' ' + getName());
                        addInfo(getClass().getSimpleName() + " starting thread " + workerThread.getName());
                        workerThread.start();
                    }
                }
            }
        } catch (Exception e) {
            addError("Error while sending a message", e);
        }
    }

    public void setAwsRegionName(String awsRegionName) {
        this.awsRegionName = awsRegionName;
    }

    public void setLogGroupName(String logGroupName) {
        this.logGroupName = logGroupName;
    }

    public void setLogStreamName(String logStreamName) {
        this.logStreamName = logStreamName;
    }

    public void setMaxLogSize(String maxLogSize) {
        this.maxLogSize = Integer.valueOf(maxLogSize);
    }

    public void setLogPollTimeMillis(String logPollTimeMillis) {
        this.logPollTimeMillis = Long.valueOf(logPollTimeMillis);
    }

    /**
     * Sends event to CloudWatch
     */
    private final class WorkerThread extends Thread {

        private final List<InputLogEvent> logs = new ArrayList<>(maxLogSize);

        WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            assert isStarted();
            try {
                // Last log submission time
                long lastSubmit = System.currentTimeMillis();
                // cloudwatch sequence token
                String lastSequenceToken = null;
                while (isStarted()) {
                    long pollRemainTime = lastSubmit + logPollTimeMillis - System.currentTimeMillis();
                    while (pollRemainTime > 0 && logs.size() < maxLogSize) {
                        try {
                            final LogReport logReport = logEvents.poll(pollRemainTime, TimeUnit.MILLISECONDS);
                            final long now = System.currentTimeMillis();
                            if (logReport != null) {
                                // create event object for submission
                                final InputLogEvent inputLogEvent = new InputLogEvent();
                                inputLogEvent.setMessage(objectMapper.writeValueAsString(logReport));
                                inputLogEvent.setTimestamp(now);
                                logs.add(inputLogEvent);
                            }
                            pollRemainTime = lastSubmit - now + logPollTimeMillis;
                        } catch (InterruptedException e) {
                            addInfo(Thread.currentThread().getName() + " interrupted");
                            pollRemainTime = -1;
                        } catch (JsonProcessingException e) {
                            addError("Unable to serialize message", e);
                        }
                    }

                    if (!logs.isEmpty()) {
                        // submit events
                        try {
                            PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest(logGroupName, logStreamName, logs);
                            putLogEventsRequest.setSequenceToken(lastSequenceToken);

                            PutLogEventsResult putLogEventsResult = awsLogsClient.putLogEvents(putLogEventsRequest);
                            lastSequenceToken = putLogEventsResult.getNextSequenceToken();
                            logs.clear();
                        } catch (InvalidSequenceTokenException e) {
                            lastSequenceToken = e.getExpectedSequenceToken();
                        }
                    }
                    // always update polling time
                    lastSubmit = System.currentTimeMillis();
                }
            } finally {
                addInfo(getClass().getSimpleName() + " stopped thread " + Thread.currentThread().getName());
            }
        }
    }
}

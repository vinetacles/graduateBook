package com.egroup.amazon.cloudwatch.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import org.slf4j.Marker;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.logs.AWSLogsClient;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import com.amazonaws.services.logs.model.FilterLogEventsRequest;
import com.amazonaws.services.logs.model.FilterLogEventsResult;
import com.amazonaws.services.logs.model.FilteredLogEvent;
import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.amazon.cloudwatch.logback.entity.ThrowableProxy;
import com.egroup.credential.BasicConfig;
import com.egroup.util.AttributeCheck;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class LogbackUtil extends BasicConfig{
    static ThreadLocal<DateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return dateFormat;
    });

    static LogReport iLoggingEvent2Map(final ILoggingEvent eventObject) {   
    	final Gson gson = new Gson();
        final String formattedMessage =eventObject.getFormattedMessage();
        final LogReport logReport = gson.fromJson(formattedMessage, LogReport.class);
        logReport.level = eventObject.getLevel().toString();
        logReport.threadName = eventObject.getThreadName();
        logReport.loggerName = eventObject.getLoggerName();
        logReport.throwableProxy = getThrowableProxyMap(eventObject.getThrowableProxy());
        logReport.markers = getMarkers(eventObject);
        logReport.timeStamp = dateFormatThreadLocal.get().format(eventObject.getTimeStamp());
        final Map<String, String> mdcPropertyMap = eventObject.getMDCPropertyMap();
        if (!mdcPropertyMap.isEmpty())
        	logReport.mdcPropertyMap = new TreeMap<>(mdcPropertyMap);
        if (eventObject.hasCallerData())
        	logReport.callerData = eventObject.getCallerData();

        return logReport;
    }

    private static ThrowableProxy getThrowableProxyMap(final IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy == null) return null;
        final ThrowableProxy throwableProxy = new ThrowableProxy();
        throwableProxy.message = iThrowableProxy.getMessage();
        throwableProxy.className = iThrowableProxy.getClassName();
        throwableProxy.cause = getThrowableProxyMap(iThrowableProxy.getCause());
        final StackTraceElementProxy[] stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        throwableProxy.stackTraceElements = new String[stackTraceElementProxyArray.length];
        int i = 0;
        for (final StackTraceElementProxy stackTraceElementProxy : stackTraceElementProxyArray) {
            throwableProxy.stackTraceElements[i] = stackTraceElementProxy.getStackTraceElement().toString();
            i++;
        }
        return throwableProxy;
    }

    private static Set<String> getMarkers(final ILoggingEvent eventObject) {
        final Marker marker = eventObject.getMarker();
        if (marker == null) {
            return null;
        }
        final Set<String> markers = new TreeSet<>();
        Iterator<?> i = marker.iterator();
        while (i.hasNext()) {
            markers.add(i.next().toString());
        }
        return markers;
    }
     
    /**
     * 取得LogEvent List
     * @param startTime : 開始時間(long timestamp格式)
     * @param endTime : 結束時間(long timestamp格式)
     * @param filterPattern : 篩選條件(直接輸入字串，如需要多重篩選，請直接用空白鍵隔開即可 )
     * 範例：String filterPattern = "ERROR Chrome 59.0.3071.115";
     * @return logEventList
     */
    public List<FilteredLogEvent> getLogEventList(long startTime, long endTime, String filterPattern) {
    	// init func
    	final AttributeCheck attributeCheck = new AttributeCheck();
    	final AWSLogsClient awsLogsClient = (AWSLogsClient) AWSLogsClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider((AWSCredentials) AWS_CREDENTIALS)).build();
    	final FilterLogEventsRequest filterLogEventsRequest = new FilterLogEventsRequest();
    	filterLogEventsRequest.setLogGroupName(LOG_GROUP_NAME); // 設定要抓取的LogGroup
    	filterLogEventsRequest.setStartTime(startTime);
    	filterLogEventsRequest.setEndTime(endTime);
		if (attributeCheck.stringsNotNull(filterPattern)) {
			filterLogEventsRequest.setFilterPattern(filterPattern);
		}
		// init variable
		FilterLogEventsResult filterLogEventsResult = awsLogsClient.filterLogEvents(filterLogEventsRequest);	
		final List<FilteredLogEvent> logEventList = filterLogEventsResult.getEvents();
		while (attributeCheck.stringsNotNull(filterLogEventsResult.getNextToken())) {
			filterLogEventsRequest.setNextToken(filterLogEventsResult.getNextToken());
			filterLogEventsResult = awsLogsClient.filterLogEvents(filterLogEventsRequest);			
			logEventList.addAll(filterLogEventsResult.getEvents());
		}
		return logEventList;	
	}
    
    /**
     * 轉成 logReport List
     * @param logEventList
     * @return logReportList
     */
    public List<LogReport> toLogReportList(List<FilteredLogEvent> logEventList) {
    	// init func
    	final Gson gson = new Gson();
    	// init variable
    	final int logEventListSize = logEventList.size();
    	final List<LogReport> logReportList = new ArrayList<LogReport>();
    	LogReport logReport;	
    	if (logEventListSize > 0) {
    		for (int i = 0; i < logEventListSize; i ++) {
        		logReport = gson.fromJson(logEventList.get(i).getMessage(), LogReport.class);
        		logReportList.add(logReport);
        	}
    	}  	
		return logReportList;	
	}
}

package com.egroup.credential;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.egroup.credential.entity.Config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public abstract class SetConfig extends UnsynchronizedAppenderBase<ILoggingEvent>{
	private final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	private final Config config = (Config) annotationConfigApplicationContext.getBean("config");
	
	// AWS 參數
	protected static String BUCKETNAME;
	protected static String LOG_GROUP_NAME;
			
	SetConfig() {
		BUCKETNAME = config.getBucketName();
		LOG_GROUP_NAME = config.getLogGroupName();	
	}
}

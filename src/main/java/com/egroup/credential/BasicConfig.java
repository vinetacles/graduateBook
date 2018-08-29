package com.egroup.credential;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class BasicConfig extends SetConfig {
	// AWS 參數
	/*
	 * 測試版
	 */
	protected static AWSCredentials AWS_CREDENTIALS = new BasicAWSCredentials("AKIAJXROD3KZ6JKF76PA", "rNP+RkZuPJOlElbu6ZWpAEITcY1h824HkOtndD5E");
	/*
	 * 正式版
	 */
//	protected static InstanceProfileCredentialsProvider AWS_CREDENTIALS = new InstanceProfileCredentialsProvider();

	public BasicConfig() {
		super();
	}

	@Override
	protected void append(ILoggingEvent eventObject) {
		// TODO Auto-generated method stub

	}
}
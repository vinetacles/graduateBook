package com.egroup.credential.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "config")
public class Config {
	// AWS
	@Value("${aws.bucketname}")
    private String bucketName;
	
	@Value("${aws.loggroupname}")
    private String logGroupName;
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getLogGroupName() {
		return logGroupName;
	}

	public void setLogGroupName(String logGroupName) {
		this.logGroupName = logGroupName;
	}
}

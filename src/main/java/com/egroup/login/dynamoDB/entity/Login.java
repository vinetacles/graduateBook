package com.egroup.login.dynamoDB.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.egroup.amazon.cloudwatch.logback.entity.LogReport;

@DynamoDBTable(tableName="graduate_login_testDB")
public class Login {
	private String account;
	private String password;
	private String loginId;
	private String date;
	private String redirectURL;
	private String name;
	
	// program control
	private LogReport logReport = new LogReport();
	private String device;
	private String ip;
	private String place;
	private String tid;
	private String phone;
	private String newPassword;
	private boolean hasNext;	
	
	@DynamoDBHashKey(attributeName="loginAccount")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@DynamoDBAttribute(attributeName="loginPassword")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@DynamoDBAttribute(attributeName="loginId")	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	@DynamoDBAttribute(attributeName="loginDate")	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@DynamoDBAttribute(attributeName="name")	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@DynamoDBIgnore
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
	@DynamoDBIgnore
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@DynamoDBIgnore
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	@DynamoDBIgnore
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	@DynamoDBIgnore
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	@DynamoDBIgnore
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
	@DynamoDBIgnore
	public LogReport getLogReport() {
		return logReport;
	}
	public void setLogReport(LogReport logReport) {
		this.logReport = logReport;
	}
	
	@DynamoDBIgnore
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@DynamoDBIgnore
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}	
}

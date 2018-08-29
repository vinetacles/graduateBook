package com.egroup.login.dynamoDB.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

@DynamoDBTable(tableName="graduate_login_token_testDB")
public class LoginToken {
	private String loginId;
	private String createDate;
	private String loginTokenId;
	private String loginTokenId_previous;
	private String loginTokenKey;
	private String loginTokenKey_previous;
	private String loginName;
	private String loginAccount;
	private String loginStatus;
	private String loginPhone;
	private Long version;	
	
	//程�?�控?��
	private HttpServletResponse response;
	private HttpServletRequest request; 
	private boolean hasNext;
	private String tokenMsg;
	private Integer amount;
	private String redirectURL;

	@DynamoDBHashKey(attributeName="loginId")
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	@DynamoDBAttribute(attributeName="loginTokenCreateDate")
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@DynamoDBAttribute(attributeName="loginTokenId")
	@DynamoDBIndexHashKey(attributeName="loginTokenId",globalSecondaryIndexName="loginTokenId-index")
	public String getLoginTokenId() {
		return loginTokenId;
	}
	public void setLoginTokenId(String loginTokenId) {
		this.loginTokenId = loginTokenId;
	}
	
	@DynamoDBAttribute(attributeName="loginTokenID_previous")
	public String getLoginTokenId_previous() {
		return loginTokenId_previous;
	}
	public void setLoginTokenId_previous(String loginTokenId_previous) {
		this.loginTokenId_previous = loginTokenId_previous;
	}
	
	@DynamoDBAttribute(attributeName="loginTokenKey")
	public String getLoginTokenKey() {
		return loginTokenKey;
	}
	public void setLoginTokenKey(String loginTokenKey) {
		this.loginTokenKey = loginTokenKey;
	}

	@DynamoDBAttribute(attributeName="loginTokenKey_previous")
	public String getLoginTokenKey_previous() {
		return loginTokenKey_previous;
	}
	public void setLoginTokenKey_previous(String loginTokenKey_previous) {
		this.loginTokenKey_previous = loginTokenKey_previous;
	}
	
	@DynamoDBAttribute(attributeName="loginName")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@DynamoDBAttribute(attributeName="loginAccount")
	@DynamoDBIndexHashKey(attributeName="loginAccount",globalSecondaryIndexName="loginAccount-index")
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	@DynamoDBAttribute(attributeName="loginStatus")
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	@DynamoDBAttribute(attributeName="loginPhone")
	public String getLoginPhone() {
		return loginPhone;
	}
	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}
	
	@DynamoDBIgnore
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	@DynamoDBIgnore
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@DynamoDBIgnore
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@DynamoDBIgnore
	public String getTokenMsg() {
		return tokenMsg;
	}
	public void setTokenMsg(String tokenMsg) {
		this.tokenMsg = tokenMsg;
	}
	
	@DynamoDBIgnore
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@DynamoDBVersionAttribute
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@DynamoDBIgnore
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}	
	
	public void removeConfidential(){
		setCreateDate(null);
		setLoginTokenId_previous(null);
		setLoginTokenKey(null);
		setLoginTokenKey_previous(null);
		setLoginAccount(null);
		setLoginStatus(null);
		setVersion(null);
	}
}
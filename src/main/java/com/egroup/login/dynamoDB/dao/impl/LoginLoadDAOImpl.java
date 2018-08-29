package com.egroup.login.dynamoDB.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.egroup.amazon.util.DynamoDBUtil;
import com.egroup.login.dynamoDB.dao.LoginLoadDAO;
import com.egroup.login.dynamoDB.entity.Login;
import com.egroup.login.dynamoDB.entity.LoginToken;

public class LoginLoadDAOImpl implements LoginLoadDAO{

	@Override
	public LoginToken loginToken(LoginToken loginToken) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();		
		loginToken = dynamoDBMapper.load(LoginToken.class, loginToken.getLoginId());
		return loginToken;
	}

	@Override
	public Login login(Login login) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();	
		login = dynamoDBMapper.load(Login.class, login.getAccount());
		return login;
	}
}

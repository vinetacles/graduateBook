package com.egroup.login.dynamoDB.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.egroup.login.dynamoDB.dao.LoginLoadDAO;
import com.egroup.login.dynamoDB.dao.LoginDAO;
import com.egroup.login.dynamoDB.entity.Login;
import com.egroup.util.AttributeCheck;
import com.google.gson.Gson;

public class LoginService {
	static final ApplicationContext CONTEXT_DYNAMODB = new ClassPathXmlApplicationContext("spring-module-dynamoDB.xml");

	/*
	 * ??��?�登?��驗�?�ID資�??
	 * 
	 * @param loginToken
	 * 
	 * @return
	 */
	public Login get(Login login) {
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (login != null && attributeCheck.stringsNotNull(login.getAccount())) {
			final LoginLoadDAO loadDAO = (LoginLoadDAO) CONTEXT_DYNAMODB.getBean("loadDAO");
			return loadDAO.login(login);
		}
		return null;
	}

	/**
	 * 驗�?��?�員?��?��
	 * 
	 * @param login
	 * @return
	 */
	public Login get_FtByPassword(Login login) {
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (login != null && attributeCheck.stringsNotNull(login.getAccount())) {
			final LoginDAO loginDAO = (LoginDAO) CONTEXT_DYNAMODB.getBean("loginDAO");
			final Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":loginAccount", new AttributeValue().withS(login.getAccount()));
			map.put(":loginPassword", new AttributeValue().withS(login.getPassword()));
			final String conditionExpression = "loginAccount = :loginAccount";
			final String filterExpression = "loginPassword = :loginPassword";
			final List<Login> loginList = loginDAO.queryByIndex(conditionExpression, filterExpression, map, null, 0,
					false, true);
			if (loginList.size() > 0) {
				return loginList.get(0);
			}
		}
		return null;
	}
}

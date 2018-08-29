package com.egroup.login.dynamoDB.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.egroup.login.dynamoDB.dao.LoginLoadDAO;
import com.egroup.login.dynamoDB.dao.LoginTokenDAO;
import com.egroup.login.dynamoDB.entity.LoginToken;
import com.egroup.util.AttributeCheck;

public class LoginTokenService {
	static final ApplicationContext CONTEXT_DYNAMODB = new ClassPathXmlApplicationContext("spring-module-dynamoDB.xml");

	/**
	 * Get LoginToken
	 * @author Daniel
	 *
	 * @param loginToken
	 * @return
	 */
	public LoginToken get(LoginToken loginToken) {
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (loginToken != null && attributeCheck.stringsNotNull(loginToken.getLoginId())) {
			final LoginLoadDAO loadDAO = (LoginLoadDAO) CONTEXT_DYNAMODB.getBean("loadDAO");
			return loadDAO.loginToken(loginToken);
		}
		return null;
	}

	/**
	 * Get Login Token By TokenID
	 * @author Daniel
	 *
	 * @param loginToken
	 * @return
	 */
	public LoginToken get_ByLoginTokenID(LoginToken loginToken) {
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (loginToken != null && attributeCheck.stringsNotNull(loginToken.getLoginTokenId())) {
			final LoginTokenDAO loginTokenDAO = (LoginTokenDAO) CONTEXT_DYNAMODB.getBean("loginTokenDAO");
			final Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			final String index = "loginTokenID-index";
			map.put(":loginTokenID", new AttributeValue().withS(loginToken.getLoginTokenId()));
			final String conditionExpression = "loginTokenID = :loginTokenID";
			final List<LoginToken> loginTokenList = loginTokenDAO.queryBySecondIndex(index, conditionExpression, null,
					map, null, 0, false, false);
			if (loginTokenList.size() > 0) {
				return loginTokenList.get(0);
			}
		}
		return null;
	}

	/**
	 * Get LoginToken By Account
	 * @author Daniel
	 *
	 * @param loginToken
	 * @return
	 */
	public LoginToken get_ByAccount(LoginToken loginToken) {
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (loginToken != null && attributeCheck.stringsNotNull(loginToken.getLoginAccount())) {
			final LoginTokenDAO loginTokenDAO = (LoginTokenDAO) CONTEXT_DYNAMODB.getBean("loginTokenDAO");
			final Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			final String index = "loginAccount-index";
			map.put(":loginAccount", new AttributeValue().withS(loginToken.getLoginAccount()));
			final String conditionExpression = "loginAccount = :loginAccount";
			final List<LoginToken> loginTokenList = loginTokenDAO.queryBySecondIndex(index, conditionExpression, null,
					map, null, 0, false, false);
			if (loginTokenList.size() > 0) {
				return loginTokenList.get(0);
			}
		}
		return null;
	}
}

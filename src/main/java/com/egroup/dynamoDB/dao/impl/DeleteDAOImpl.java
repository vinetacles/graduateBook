package com.egroup.dynamoDB.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.egroup.amazon.util.DynamoDBUtil;
import com.egroup.dynamoDB.dao.DeleteDAO;

public class DeleteDAOImpl implements DeleteDAO{
	
	public String delete(Object object) {
		final DynamoDBUtil dynamoDBUtil = new DynamoDBUtil();
		final DynamoDBMapper mapper = dynamoDBUtil.getMapper();	
		try {
			mapper.delete(object);
		} catch (com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void batchDelete(Object... objects) {
		final DynamoDBUtil dynamoDBUtil = new DynamoDBUtil();
		final DynamoDBMapper mapper = dynamoDBUtil.getMapper();	
		mapper.batchDelete(objects);        
	}
}

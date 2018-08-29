package com.egroup.dynamoDB.dao.impl;

import com.egroup.amazon.util.DynamoDBUtil;
import com.egroup.dynamoDB.dao.SaveDAO;

public class SaveDAOImpl implements SaveDAO{
	
	public String save(Object object){
		final DynamoDBUtil dynamoDBUtil = new DynamoDBUtil();
		return dynamoDBUtil.save(object);		
	}
	
	public void saveBatch(Object... object){
		final DynamoDBUtil dynamoDBUtil = new DynamoDBUtil();
		dynamoDBUtil.batchSave(object);		
	}
}

package com.egroup.dynamoDB.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.egroup.dynamoDB.dao.SaveDAO;

public class SaveService {
	static final ApplicationContext CONTEXT_DYNAMODB = new ClassPathXmlApplicationContext("spring-module-dynamoDB.xml");

	public String save(Object object){
		final SaveDAO saveDAO = (SaveDAO)CONTEXT_DYNAMODB.getBean("saveDAO");
		return saveDAO.save(object);
	}
	
	public void batchSave(Object... object){
		final SaveDAO saveDAO = (SaveDAO)CONTEXT_DYNAMODB.getBean("saveDAO");
		saveDAO.saveBatch(object);
	}
}

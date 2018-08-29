package com.egroup.dynamoDB.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.egroup.dynamoDB.dao.DeleteDAO;

public class DeleteService {
	static final ApplicationContext CONTEXT_DYNAMODB = new ClassPathXmlApplicationContext("spring-module-dynamoDB.xml");
	
	/**
	 * Single Delete
	 * @author Daniel
	 * @param object delete object
	 * @return
	 */
	public String delete(Object object){	
		final DeleteDAO deleteDAO = (DeleteDAO)CONTEXT_DYNAMODB.getBean("deleteDAO");	
		return deleteDAO.delete(object);
	}
	
	/**
	 * Multiple delete
	 * @author Daniel
	 * @param objects delete objects
	 */
	public void batchDelete(Object...objects){	
		final DeleteDAO deleteDAO = (DeleteDAO)CONTEXT_DYNAMODB.getBean("deleteDAO");	
		deleteDAO.batchDelete(objects);
	}
	
}

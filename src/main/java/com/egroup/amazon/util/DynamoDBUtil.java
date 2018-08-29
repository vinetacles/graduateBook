package com.egroup.amazon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.credential.BasicConfig;
import com.google.gson.Gson;

public class DynamoDBUtil extends BasicConfig{
	 static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBUtil.class);
	
	private static AmazonDynamoDBClient AMAZON_DYNAMODB_CLIENT = new AmazonDynamoDBClient(AWS_CREDENTIALS).withRegion(Regions.AP_NORTHEAST_1);
	private DynamoDBMapper mapper = new DynamoDBMapper(AMAZON_DYNAMODB_CLIENT);

	/**
	 * 連線
	 */
	/*public void getConnected() {
		mapper = new DynamoDBMapper(AMAZON_DYNAMODB_CLIENT);
	}*/
	
	public void update(String tableName,String pk,String pkValue,String updateExpression
			, ValueMap valueMap) {		
		final DynamoDB dynamoDB = new DynamoDB(AMAZON_DYNAMODB_CLIENT);
		final Table table = dynamoDB.getTable(tableName);
        try {
            final UpdateItemSpec updateItemSpec = new UpdateItemSpec()
            .withPrimaryKey(pk, pkValue)
            .withUpdateExpression(updateExpression)
            .withValueMap(valueMap)
            .withReturnValues(ReturnValue.ALL_NEW);
            
            final  UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            // Check the response.
            /*System.out.println("Printing item after conditional update to new attribute...");
            System.out.println(outcome.getItem().toJSONPretty());*/

        } catch (Exception e) {
        	final LogReport logReport = new LogReport();
        	logReport.setMessage("Error updating item in "+ tableName);
        	LOGGER.error(new Gson().toJson(logReport), e);
        }
    }
	
	/**
	 * Single Save
	 * @param object Save 
	 */
	public String save(Object object){
		try {
			mapper.save(object);
		} catch (com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException e) {
			// LogReport
			final LogReport logReport = new LogReport();
			logReport.setMessage("save 錯誤");
			logReport.setFunction("save(object)");
			logReport.setAttributes(object);
			LOGGER.error(new Gson().toJson(object), e); 
			return e.getErrorMessage();
		}
		return null;
	}
	
	/**
	 * Multiple save
	 * @param objectsToSave 
	 */
	public void batchSave(Object... objectsToSave){
		mapper.batchSave(objectsToSave);
	}
	
	public DynamoDBMapper getMapper() {
		return mapper;
	}
}

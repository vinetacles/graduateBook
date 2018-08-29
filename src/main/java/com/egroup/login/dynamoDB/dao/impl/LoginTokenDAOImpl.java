package com.egroup.login.dynamoDB.dao.impl;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.egroup.amazon.util.DynamoDBUtil;
import com.egroup.login.dynamoDB.dao.LoginTokenDAO;
import com.egroup.login.dynamoDB.entity.LoginToken;

public class LoginTokenDAOImpl implements LoginTokenDAO{
	@Override
	public List queryByIndex(String conditionExpression, String filterExpression, Map<String, AttributeValue> map,
			Map<String, AttributeValue> exclusiveStartKey, int limit, boolean scanIndexForward,
			boolean consistentRead) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();		
		DynamoDBQueryExpression<LoginToken> queryExpression = null;
		if(limit==0){
			if(filterExpression==null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//DESC 
			}     
			else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withFilterExpression(filterExpression)
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//DESC 
			}
		}else{
			if(filterExpression==null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withScanIndexForward(scanIndexForward)				//DESC 
	            .withLimit(limit)									//限制比數
	            .withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}     
			else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withFilterExpression(filterExpression)
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withScanIndexForward(scanIndexForward)				//DESC 
	            .withLimit(limit)									//限制比數
	            .withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}
		}  
		//取得資料
  		List<LoginToken> list = dynamoDBMapper.queryPage(LoginToken.class, queryExpression).getResults();	
  		if(list.size()==0){
  			list = dynamoDBMapper.query(LoginToken.class, queryExpression);
  		}else{
  			 //若有lastEvaluatedKey代表後面還有資料
  	        if(list!=null&&list.size()!=0&&dynamoDBMapper.queryPage(LoginToken.class, queryExpression).getLastEvaluatedKey()!=null){
  	        	list.get(list.size()-1).setHasNext(true); 
  	        }   
  		}
        return list;
	}

	@Override
	public List queryBySecondIndex(String index, String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit,
			boolean scanIndexForward, boolean consistentRead) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();			
		DynamoDBQueryExpression<LoginToken> queryExpression = null;
		if(limit==0){
			if(filterExpression!=null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withFilterExpression(filterExpression)				//篩選條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//ASC|DESC
			}else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//ASC|DESC
			}
		}else{
			if(filterExpression!=null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withFilterExpression(filterExpression)				//篩選條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withLimit(limit)  									//取得數量
		    	.withScanIndexForward(scanIndexForward)				//ASC|DESC
		    	.withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withLimit(limit)  									//取得數量
		    	.withScanIndexForward(scanIndexForward)				//ASC|DESC
		    	.withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}
		}
		//取得資料
  		List<LoginToken> list = dynamoDBMapper.queryPage(LoginToken.class, queryExpression).getResults();	
  		if(list.size()==0){
  			list = dynamoDBMapper.query(LoginToken.class, queryExpression);
  		}else{
  			 //若有lastEvaluatedKey代表後面還有資料
  	        if(list!=null&&list.size()!=0&&dynamoDBMapper.queryPage(LoginToken.class, queryExpression).getLastEvaluatedKey()!=null){
  	        	list.get(list.size()-1).setHasNext(true); 
  	        }   
  		}
        return list;
	}
	
	@Override
	public int countByIndex(String conditionExpression, String filterExpression, Map<String, AttributeValue> map,
			Map<String, AttributeValue> exclusiveStartKey, int limit, boolean scanIndexForward,
			boolean consistentRead) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();		
		DynamoDBQueryExpression<LoginToken> queryExpression = null;
		if(limit==0){
			if(filterExpression==null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//DESC 
			}     
			else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withFilterExpression(filterExpression)
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//DESC 
			}
		}else{
			if(filterExpression==null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withScanIndexForward(scanIndexForward)				//DESC 
	            .withLimit(limit)									//限制比數
	            .withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}     
			else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
				.withKeyConditionExpression(conditionExpression)	//條件
	            .withExpressionAttributeValues(map)					//條件參數對應
	            .withFilterExpression(filterExpression)
	            .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
	            .withScanIndexForward(scanIndexForward)				//DESC 
	            .withLimit(limit)									//限制比數
	            .withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}
		}
		return dynamoDBMapper.count(LoginToken.class, queryExpression);
	}

	@Override
	public int countBySecondIndex(String index, String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit,
			boolean scanIndexForward, boolean consistentRead) {
		final DynamoDBMapper dynamoDBMapper = new DynamoDBUtil().getMapper();			
		DynamoDBQueryExpression<LoginToken> queryExpression = null;
		if(limit==0){
			if(filterExpression!=null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withFilterExpression(filterExpression)				//篩選條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//ASC|DESC
			}else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withExclusiveStartKey(exclusiveStartKey) 			//前一次查詢最後一筆資料
	            .withScanIndexForward(scanIndexForward);			//ASC|DESC
			}
		}else{
			if(filterExpression!=null){
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withFilterExpression(filterExpression)				//篩選條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withLimit(limit)  									//取得數量
		    	.withScanIndexForward(scanIndexForward)				//ASC|DESC
		    	.withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}else{
				queryExpression = new DynamoDBQueryExpression<LoginToken>()
		    	.withIndexName(index)								//second-index
		        .withKeyConditionExpression(conditionExpression)	//條件
		        .withExpressionAttributeValues(map)					//條件參數對應
		        .withConsistentRead(consistentRead)					//依照排序取值(false)，second index not support consistendRead
		        .withLimit(limit)  									//取得數量
		    	.withScanIndexForward(scanIndexForward)				//ASC|DESC
		    	.withExclusiveStartKey(exclusiveStartKey); 			//前一次查詢最後一筆資料
			}
		}
		return dynamoDBMapper.count(LoginToken.class, queryExpression);
	}
}

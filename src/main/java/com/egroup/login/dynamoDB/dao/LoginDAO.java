package com.egroup.login.dynamoDB.dao;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public interface LoginDAO {
	/**
	 * 主鍵取資料
	 * @param conditionExpression 	取資料主條件(必填)
	 * @param filterExpression		取資料篩選條件(選填)
	 * @param map 					取資料條件屬性資料(必填)
	 * @param exclusiveStartKey 	上次取資料的最後一筆資料主鍵(選填，e.g:若第一次只取100筆，第二次要取後須資料，需提供100的PK和RK)
	 * @param limit					取得比數(必填)
	 * @param scanIndexForward		正排序或倒排序(必填)
	 * @param consistentRead		是否為連續資料(必填)
	 * @return
	 */
	public List queryByIndex(String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit
			, boolean scanIndexForward,	boolean consistentRead);
	
	/**
	 * 次主鍵取資料
	 * @param index					索引名稱
	 * @param conditionExpression 	取資料主條件(必填)
	 * @param filterExpression		取資料篩選條件(選填)
	 * @param map 					取資料條件屬性資料(必填)
	 * @param exclusiveStartKey 	上次取資料的最後一筆資料主鍵(選填，e.g:若第一次只取100筆，第二次要取後須資料，需提供100的PK和RK)
	 * @param limit					取得比數(必填)
	 * @param scanIndexForward		正排序或倒排序(必填)
	 * @param consistentRead		是否為連續資料(必填)
	 * @return
	 */
	public List queryBySecondIndex(String index, String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit
			, boolean scanIndexForward, boolean consistentRead);
	
	/**
	 * 主鍵計算資料數量
	 * @param conditionExpression 	取資料主條件(必填)
	 * @param filterExpression		取資料篩選條件(選填)
	 * @param map 					取資料條件屬性資料(必填)
	 * @param exclusiveStartKey 	上次取資料的最後一筆資料主鍵(選填，e.g:若第一次只取100筆，第二次要取後須資料，需提供100的PK和RK)
	 * @param limit					取得比數(必填)
	 * @param scanIndexForward		正排序或倒排序(必填)
	 * @param consistentRead		是否為連續資料(必填)
	 * @return
	 */
	public int countByIndex(String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit
			, boolean scanIndexForward, boolean consistentRead);
	
	/**
	 * 次主鍵計算資料數量
	 * @param index					索引名稱
	 * @param conditionExpression 	取資料主條件(必填)
	 * @param filterExpression		取資料篩選條件(選填)
	 * @param map 					取資料條件屬性資料(必填)
	 * @param exclusiveStartKey 	上次取資料的最後一筆資料主鍵(選填，e.g:若第一次只取100筆，第二次要取後須資料，需提供100的PK和RK)
	 * @param limit					取得比數(必填)
	 * @param scanIndexForward		正排序或倒排序(必填)
	 * @param consistentRead		是否為連續資料(必填)
	 * @return
	 */
	public int countBySecondIndex(String index, String conditionExpression, String filterExpression,
			Map<String, AttributeValue> map, Map<String, AttributeValue> exclusiveStartKey, int limit
			, boolean scanIndexForward, boolean consistentRead);
}

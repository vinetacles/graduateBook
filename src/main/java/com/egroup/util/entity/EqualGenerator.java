package com.egroup.util.entity;

import java.util.HashMap;
import java.util.Map;

import com.egroup.util.AttributeCheck;


public class EqualGenerator {
	final AttributeCheck attributeCheck = new AttributeCheck();
	private HashMap<String, String> equalHashMap;
	private String equalSql;

	public void setEqualHashMap(String column, String value) {
		if (!attributeCheck.objectNotNull(equalHashMap)) {
			equalHashMap = new HashMap<>();
		}
		if (attributeCheck.stringsNotNull(column) && attributeCheck.stringsNotNull(value)) {
			equalHashMap.put(column, value);
		}
	}

	public String getEqualSql() {
		equalSql = "";
		if (attributeCheck.objectNotNull(getEqualHashMap()) && getEqualHashMap().size() > 0) {
			for (Map.Entry<String, String> entry : getEqualHashMap().entrySet()) {
				equalSql += entry.getKey() + " = '" + entry.getValue() + "' AND ";
			}
			equalSql = " " + equalSql.substring(0, equalSql.length() - 4) + " ";
		}
		return equalSql;
	}

	public HashMap<String, String> getEqualHashMap() {
		return equalHashMap;
	}

}

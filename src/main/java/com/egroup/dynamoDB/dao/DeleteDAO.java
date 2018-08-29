package com.egroup.dynamoDB.dao;

public interface DeleteDAO {
	public String delete(Object object);
	public void batchDelete(Object...objects);
}

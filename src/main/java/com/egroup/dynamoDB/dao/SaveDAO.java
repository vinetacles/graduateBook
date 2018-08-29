package com.egroup.dynamoDB.dao;

public interface SaveDAO {
	public String save(Object object);
	public void saveBatch(Object... object);
}

package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.LayoutType;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface LayoutTypeDAO {
public void insert(LayoutType layoutType);
public void insert(List<LayoutType> layoutTypeList);
public void update(LayoutType layoutType,LayoutType oldLayoutType);
public void delete(LayoutType layoutType);
public LayoutType get(LayoutType layoutType);
public List<LayoutType> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

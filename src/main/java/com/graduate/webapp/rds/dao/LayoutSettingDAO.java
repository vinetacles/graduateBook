package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.LayoutSetting;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface LayoutSettingDAO {
public void insert(LayoutSetting layoutSetting);
public void insert(List<LayoutSetting> layoutSettingList);
public void update(LayoutSetting layoutSetting,LayoutSetting oldLayoutSetting);
public void delete(LayoutSetting layoutSetting);
public LayoutSetting get(LayoutSetting layoutSetting);
public List<LayoutSetting> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

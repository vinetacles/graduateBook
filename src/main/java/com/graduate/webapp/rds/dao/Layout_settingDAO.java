package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Layout_setting;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Layout_settingDAO {
public void insert(Layout_setting layout_setting);
public void insert(List<Layout_setting> layout_settingList);
public void update(Layout_setting layout_setting,Layout_setting oldLayout_setting);
public void delete(Layout_setting layout_setting);
public Layout_setting get(Layout_setting layout_setting);
public List<Layout_setting> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

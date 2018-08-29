package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Layout_type;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Layout_typeDAO {
public void insert(Layout_type layout_type);
public void insert(List<Layout_type> layout_typeList);
public void update(Layout_type layout_type,Layout_type oldLayout_type);
public void delete(Layout_type layout_type);
public Layout_type get(Layout_type layout_type);
public List<Layout_type> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

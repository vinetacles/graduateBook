package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Page;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface PageDAO {
public void insert(Page page);
public void insert(List<Page> pageList);
public void update(Page page,Page oldPage);
public void delete(Page page);
public Page get(Page page);
public List<Page> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

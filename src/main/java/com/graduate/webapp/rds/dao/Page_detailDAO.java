package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Page_detail;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Page_detailDAO {
public void insert(Page_detail page_detail);
public void insert(List<Page_detail> page_detailList);
public void update(Page_detail page_detail,Page_detail oldPage_detail);
public void delete(Page_detail page_detail);
public Page_detail get(Page_detail page_detail);
public List<Page_detail> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

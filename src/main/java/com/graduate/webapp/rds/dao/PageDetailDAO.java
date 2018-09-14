package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.PageDetail;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface PageDetailDAO {
public void insert(PageDetail pageDetail);
public void insert(List<PageDetail> pageDetailList);
public void update(PageDetail pageDetail,PageDetail oldPageDetail);
public void delete(PageDetail pageDetail);
public PageDetail get(PageDetail pageDetail);
public List<PageDetail> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

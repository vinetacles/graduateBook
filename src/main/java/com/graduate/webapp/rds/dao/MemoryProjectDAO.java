package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.MemoryProject;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface MemoryProjectDAO {
public void insert(MemoryProject memoryProject);
public void insert(List<MemoryProject> memoryProjectList);
public void update(MemoryProject memoryProject,MemoryProject oldMemoryProject);
public void delete(MemoryProject memoryProject);
public MemoryProject get(MemoryProject memoryProject);
public List<MemoryProject> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

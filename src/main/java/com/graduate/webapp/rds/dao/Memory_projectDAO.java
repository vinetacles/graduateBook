package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.MemoryProject;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Memory_projectDAO {
public void insert(MemoryProject memory_project);
public void insert(List<MemoryProject> memory_projectList);
public void update(MemoryProject memory_project,MemoryProject oldMemory_project);
public void delete(MemoryProject memory_project);
public MemoryProject get(MemoryProject memory_project);
public List<MemoryProject> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

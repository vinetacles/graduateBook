package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Memory_project_invited;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface Memory_project_invitedDAO {
public void insert(Memory_project_invited memory_project_invited);
public void insert(List<Memory_project_invited> memory_project_invitedList);
public void update(Memory_project_invited memory_project_invited,Memory_project_invited oldMemory_project_invited);
public void delete(Memory_project_invited memory_project_invited);
public Memory_project_invited get(Memory_project_invited memory_project_invited);
public Memory_project_invited get_JoinByMemoryProjectInvitedAccount(Memory_project_invited memory_project_invited);
public List getList_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil);
public List<Memory_project_invited> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
public Integer countTotal_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil);
}

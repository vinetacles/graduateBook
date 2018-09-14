package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.MemoryProjectInvited;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface MemoryProjectInvitedDAO {
public void insert(MemoryProjectInvited memoryProjectInvited);
public void insert(List<MemoryProjectInvited> memoryProjectInvitedList);
public void update(MemoryProjectInvited memoryProjectInvited,MemoryProjectInvited oldMemoryProjectInvited);
public void delete(MemoryProjectInvited memoryProjectInvited);
public MemoryProjectInvited get(MemoryProjectInvited memoryProjectInvited);
public MemoryProjectInvited get_JoinByMemoryProjectInvitedAccount(MemoryProjectInvited memoryProjectInvited);
public List getList_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil);
public List<MemoryProjectInvited> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
public Integer countTotal_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil);
}

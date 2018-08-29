package com.graduate.webapp.rds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

import com.graduate.webapp.rds.dao.Memory_project_invitedDAO;
import com.graduate.webapp.rds.entity.Memory_project_invited;

public class Memory_project_invitedDAOImpl implements Memory_project_invitedDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Memory_project_invited
public void insert(Memory_project_invited memory_project_invited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_project_invited(memoryProjectInvitedAccount,memoryProjectId , memoryProjectInvitedStatus , memoryProjectInvitedCreateDate , memoryProjectInvitedUpdateDate , memoryProjectInvitedNo ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memory_project_invited.getMember().getMemoryProjectInvitedAccount());
smt.setString(2, memory_project_invited.getMemoryProject().getMemoryProjectId());
smt.setInt(3, memory_project_invited.getMemoryProjectInvitedStatus());
smt.setString(6, memory_project_invited.getMemoryProjectInvitedNo());
smt.executeUpdate();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
}


// insert Memory_project_invited
public void insert(List<Memory_project_invited> memory_project_invitedList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_project_invited(memoryProjectInvitedAccount,memoryProjectId , memoryProjectInvitedStatus , memoryProjectInvitedCreateDate , memoryProjectInvitedUpdateDate , memoryProjectInvitedNo ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < memory_project_invitedList.size() ; i++) {
smt.setString(1, memory_project_invitedList.get(0).getMember().getMemoryProjectInvitedAccount());
smt.setString(2, memory_project_invitedList.get(1).getMemoryProject().getMemoryProjectId());
smt.setInt(3, memory_project_invitedList.get(2).getMemoryProjectInvitedStatus());
smt.setString(6, memory_project_invitedList.get(5).getMemoryProjectInvitedNo());
smt.addBatch();
}
smt.executeBatch();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
}


// update Memory_project_invited
public void update(Memory_project_invited memory_project_invited,Memory_project_invited oldMemory_project_invited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE memory_project_invited SET memoryProjectId = ? , memoryProjectInvitedStatus = ? , memoryProjectInvitedCreateDate = ? , memoryProjectInvitedUpdateDate = ? , memoryProjectInvitedNo = ?  WHERE memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt.setString(1, memory_project_invited.getMemoryProject().getMemoryProjectId()!=null?memory_project_invited.getMemoryProject().getMemoryProjectId():oldMemory_project_invited.getMemoryProject().getMemoryProjectId());
smt.setInt(2, memory_project_invited.getMemoryProjectInvitedStatus()!=null?memory_project_invited.getMemoryProjectInvitedStatus():oldMemory_project_invited.getMemoryProjectInvitedStatus());
smt.setString(5, memory_project_invited.getMemoryProjectInvitedNo()!=null?memory_project_invited.getMemoryProjectInvitedNo():oldMemory_project_invited.getMemoryProjectInvitedNo());
smt.setString(6, memory_project_invited.getMember().getMemoryProjectInvitedAccount()!=null?memory_project_invited.getMember().getMemoryProjectInvitedAccount():oldMemory_project_invited.getMember().getMemoryProjectInvitedAccount());
smt.executeUpdate();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
}


// delete Memory_project_invited
public void delete(Memory_project_invited memory_project_invited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM memory_project_invited WHERE memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memory_project_invited.getMember().getMemoryProjectInvitedAccount());
smt.executeUpdate();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
}


// GET Memory_project_invited
public Memory_project_invited get(Memory_project_invited memory_project_invited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a WHERE a.memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memory_project_invited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
memory_project_invited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invited;
}


// get_JoinBy Memory_project_invited
public Memory_project_invited get_JoinByMemoryProjectInvitedAccount(Memory_project_invited memory_project_invited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memory_project_invited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
memory_project_invited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invited;
}

// get_JoinBy Memory_project_invited
public Memory_project_invited get_JoinByMemoryProjectId(Memory_project_invited memory_project_invited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memory_project_invited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
memory_project_invited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invited;
}



// getList Memory_project_invited
public List<Memory_project_invited> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Memory_project_invited> memory_project_invitedList = new ArrayList<Memory_project_invited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Memory_project_invited memory_project_invited = new Memory_project_invited();
while(rs.next()){
memory_project_invited = new Memory_project_invited();
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
memory_project_invitedList.add(memory_project_invited);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invitedList;
}


//Get List  Memory_project_invited
public List<Memory_project_invited> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Memory_project_invited> memory_project_invitedList = new ArrayList<Memory_project_invited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Memory_project_invited memory_project_invited = new Memory_project_invited();
while(rs.next()){
memory_project_invited = new Memory_project_invited();
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
memory_project_invitedList.add(memory_project_invited);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invitedList;
}



// getList_JoinByFK  Memory_project_invited
public List<Memory_project_invited> getList_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Memory_project_invited> memory_project_invitedList = new ArrayList<Memory_project_invited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHRER b.memoryProjectInvitedAccount = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Memory_project_invited memory_project_invited = new Memory_project_invited();
while(rs.next()){
memory_project_invited = new Memory_project_invited();
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
memory_project_invitedList.add(memory_project_invited);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invitedList;
}

// getList_JoinByFK  Memory_project_invited
public List<Memory_project_invited> getList_JoinByMemoryProjectId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Memory_project_invited> memory_project_invitedList = new ArrayList<Memory_project_invited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHRER b.memoryProjectId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Memory_project_invited memory_project_invited = new Memory_project_invited();
while(rs.next()){
memory_project_invited = new Memory_project_invited();
memory_project_invited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memory_project_invited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
memory_project_invited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
memory_project_invitedList.add(memory_project_invited);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return memory_project_invitedList;
}



//countTotal Memory_project_invited
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_project_invited";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
if(rs.next()){
countTotal = rs.getInt(1);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return countTotal;
}


//countTotalList Memory_project_invited
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_project_invited "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
if(rs.next()){
countTotal = rs.getInt(1);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return countTotal;
}


// countTotalList_JoinByFk Memory_project_invited
public Integer countTotal_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM  layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.layoutTypeId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
if(rs.next()){
countTotal = rs.getInt(1);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return countTotal;
}

// countTotalList_JoinByFk Memory_project_invited
public Integer countTotal_JoinByMemoryProjectId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM  layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.templateId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
if(rs.next()){
countTotal = rs.getInt(1);
}
rs.close();
smt.close();

} catch (SQLException e) {

throw new RuntimeException(e);

} finally {
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {}
}
}
return countTotal;
}




}

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

import com.graduate.webapp.rds.dao.MemoryProjectInvitedDAO;
import com.graduate.webapp.rds.entity.MemoryProjectInvited;

public class MemoryProjectInvitedDAOImpl implements MemoryProjectInvitedDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert MemoryProjectInvited
public void insert(MemoryProjectInvited MemoryProjectInvited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO MemoryProjectInvited(memoryProjectInvitedAccount,memoryProjectId , memoryProjectInvitedStatus , memoryProjectInvitedCreateDate , memoryProjectInvitedUpdateDate , memoryProjectInvitedNo ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
smt.setString(2, MemoryProjectInvited.getMemoryProject().getMemoryProjectId());
smt.setInt(3, MemoryProjectInvited.getMemoryProjectInvitedStatus());
smt.setString(6, MemoryProjectInvited.getMemoryProjectInvitedNo());
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


// insert MemoryProjectInvited
public void insert(List<MemoryProjectInvited> MemoryProjectInvitedList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO MemoryProjectInvited(memoryProjectInvitedAccount,memoryProjectId , memoryProjectInvitedStatus , memoryProjectInvitedCreateDate , memoryProjectInvitedUpdateDate , memoryProjectInvitedNo ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < MemoryProjectInvitedList.size() ; i++) {
smt.setString(1, MemoryProjectInvitedList.get(0).getMember().getMemoryProjectInvitedAccount());
smt.setString(2, MemoryProjectInvitedList.get(1).getMemoryProject().getMemoryProjectId());
smt.setInt(3, MemoryProjectInvitedList.get(2).getMemoryProjectInvitedStatus());
smt.setString(6, MemoryProjectInvitedList.get(5).getMemoryProjectInvitedNo());
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


// update MemoryProjectInvited
public void update(MemoryProjectInvited MemoryProjectInvited,MemoryProjectInvited oldMemoryProjectInvited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE MemoryProjectInvited SET memoryProjectId = ? , memoryProjectInvitedStatus = ? , memoryProjectInvitedCreateDate = ? , memoryProjectInvitedUpdateDate = ? , memoryProjectInvitedNo = ?  WHERE memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt.setString(1, MemoryProjectInvited.getMemoryProject().getMemoryProjectId()!=null?MemoryProjectInvited.getMemoryProject().getMemoryProjectId():oldMemoryProjectInvited.getMemoryProject().getMemoryProjectId());
smt.setInt(2, MemoryProjectInvited.getMemoryProjectInvitedStatus()!=null?MemoryProjectInvited.getMemoryProjectInvitedStatus():oldMemoryProjectInvited.getMemoryProjectInvitedStatus());
smt.setString(5, MemoryProjectInvited.getMemoryProjectInvitedNo()!=null?MemoryProjectInvited.getMemoryProjectInvitedNo():oldMemoryProjectInvited.getMemoryProjectInvitedNo());
smt.setString(6, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount()!=null?MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount():oldMemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
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


// delete MemoryProjectInvited
public void delete(MemoryProjectInvited MemoryProjectInvited) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM MemoryProjectInvited WHERE memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
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


// GET MemoryProjectInvited
public MemoryProjectInvited get(MemoryProjectInvited MemoryProjectInvited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a WHERE a.memoryProjectInvitedAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
MemoryProjectInvited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
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
return MemoryProjectInvited;
}


// get_JoinBy MemoryProjectInvited
public MemoryProjectInvited get_JoinByMemoryProjectInvitedAccount(MemoryProjectInvited MemoryProjectInvited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
MemoryProjectInvited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
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
return MemoryProjectInvited;
}

// get_JoinBy MemoryProjectInvited
public MemoryProjectInvited get_JoinByMemoryProjectId(MemoryProjectInvited MemoryProjectInvited) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, MemoryProjectInvited.getMember().getMemoryProjectInvitedAccount());
rs = smt.executeQuery();
if(rs.next()){
MemoryProjectInvited.getMember().setMemoryProjectInvitedAccount(rs.getString("memoryProjectInvitedAccount"));
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
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
return MemoryProjectInvited;
}



// getList MemoryProjectInvited
public List<MemoryProjectInvited> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProjectInvited> MemoryProjectInvitedList = new ArrayList<MemoryProjectInvited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProjectInvited MemoryProjectInvited = new MemoryProjectInvited();
while(rs.next()){
MemoryProjectInvited = new MemoryProjectInvited();
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
MemoryProjectInvitedList.add(MemoryProjectInvited);
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
return MemoryProjectInvitedList;
}


//Get List  MemoryProjectInvited
public List<MemoryProjectInvited> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProjectInvited> MemoryProjectInvitedList = new ArrayList<MemoryProjectInvited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProjectInvited MemoryProjectInvited = new MemoryProjectInvited();
while(rs.next()){
MemoryProjectInvited = new MemoryProjectInvited();
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
MemoryProjectInvitedList.add(MemoryProjectInvited);
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
return MemoryProjectInvitedList;
}



// getList_JoinByFK  MemoryProjectInvited
public List<MemoryProjectInvited> getList_JoinByMemoryProjectInvitedAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProjectInvited> MemoryProjectInvitedList = new ArrayList<MemoryProjectInvited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHRER b.memoryProjectInvitedAccount = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProjectInvited MemoryProjectInvited = new MemoryProjectInvited();
while(rs.next()){
MemoryProjectInvited = new MemoryProjectInvited();
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
MemoryProjectInvitedList.add(MemoryProjectInvited);
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
return MemoryProjectInvitedList;
}

// getList_JoinByFK  MemoryProjectInvited
public List<MemoryProjectInvited> getList_JoinByMemoryProjectId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProjectInvited> MemoryProjectInvitedList = new ArrayList<MemoryProjectInvited>();
final String sql = "SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM MemoryProjectInvited a  WHRER b.memoryProjectId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProjectInvited MemoryProjectInvited = new MemoryProjectInvited();
while(rs.next()){
MemoryProjectInvited = new MemoryProjectInvited();
MemoryProjectInvited.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
MemoryProjectInvited.setMemoryProjectInvitedStatus(rs.getInt("memoryProjectInvitedStatus"));
MemoryProjectInvited.setMemoryProjectInvitedNo(rs.getString("memoryProjectInvitedNo"));
MemoryProjectInvitedList.add(MemoryProjectInvited);
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
return MemoryProjectInvitedList;
}



//countTotal MemoryProjectInvited
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM MemoryProjectInvited";
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


//countTotalList MemoryProjectInvited
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM MemoryProjectInvited "+
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


// countTotalList_JoinByFk MemoryProjectInvited
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

// countTotalList_JoinByFk MemoryProjectInvited
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

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

import com.graduate.webapp.rds.dao.MemoryProjectDAO;
import com.graduate.webapp.rds.entity.MemoryProject;

public class MemoryProjectDAOImpl implements MemoryProjectDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Memory_project
public void insert(MemoryProject memoryProject) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_project(memoryProjectId,memoryProjectName , memoryProjectCreateDate , memoryProjectUpdateDate , memberAccount ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memoryProject.getMemoryProjectId());
smt.setString(2, memoryProject.getMemoryProjectName());
smt.setString(5, memoryProject.getMember().getMemberAccount());
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


// insert Memory_project
public void insert(List<MemoryProject> memoryProjectList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_project(memoryProjectId,memoryProjectName , memoryProjectCreateDate , memoryProjectUpdateDate , memberAccount ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < memoryProjectList.size() ; i++) {
smt.setString(1, memoryProjectList.get(0).getMemoryProjectId());
smt.setString(2, memoryProjectList.get(1).getMemoryProjectName());
smt.setString(5, memoryProjectList.get(4).getMember().getMemberAccount());
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


// update Memory_project
public void update(MemoryProject memoryProject,MemoryProject oldMemoryProject) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE memory_project SET memoryProjectName = ? , memoryProjectCreateDate = ? , memoryProjectUpdateDate = ? , memberAccount = ?  WHERE memoryProjectId = ? ";
try {
conn = dataSource.getConnection();
smt.setString(1, memoryProject.getMemoryProjectName()!=null?memoryProject.getMemoryProjectName():oldMemoryProject.getMemoryProjectName());
smt.setString(4, memoryProject.getMember().getMemberAccount()!=null?memoryProject.getMember().getMemberAccount():oldMemoryProject.getMember().getMemberAccount());
smt.setString(5, memoryProject.getMemoryProjectId()!=null?memoryProject.getMemoryProjectId():oldMemoryProject.getMemoryProjectId());
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


// delete Memory_project
public void delete(MemoryProject memoryProject) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM memory_project WHERE memoryProjectId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memoryProject.getMemoryProjectId());
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


// GET Memory_project
public MemoryProject get(MemoryProject memoryProject) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a WHERE a.memoryProjectId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memoryProject.getMemoryProjectId());
rs = smt.executeQuery();
if(rs.next()){
memoryProject.setMemoryProjectId(rs.getString("memoryProjectId"));
memoryProject.setMemoryProjectName(rs.getString("memoryProjectName"));
memoryProject.getMember().setMemberAccount(rs.getString("memberAccount"));
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
return memoryProject;
}


// get_JoinBy Memory_project
public MemoryProject get_JoinByMemberAccount(MemoryProject memoryProject) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memoryProject.getMemoryProjectId());
rs = smt.executeQuery();
if(rs.next()){
memoryProject.setMemoryProjectId(rs.getString("memoryProjectId"));
memoryProject.setMemoryProjectName(rs.getString("memoryProjectName"));
memoryProject.getMember().setMemberAccount(rs.getString("memberAccount"));
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
return memoryProject;
}



// getList Memory_project
public List<MemoryProject> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProject> memoryProjectList = new ArrayList<MemoryProject>();
final String sql = "SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProject memoryProject = new MemoryProject();
while(rs.next()){
memoryProject = new MemoryProject();
memoryProject.setMemoryProjectId(rs.getString("memoryProjectId"));
memoryProject.setMemoryProjectName(rs.getString("memoryProjectName"));
memoryProjectList.add(memoryProject);
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
return memoryProjectList;
}


//Get List  Memory_project
public List<MemoryProject> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProject> memoryProjectList = new ArrayList<MemoryProject>();
final String sql = "SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProject memoryProject = new MemoryProject();
while(rs.next()){
memoryProject = new MemoryProject();
memoryProject.setMemoryProjectId(rs.getString("memoryProjectId"));
memoryProject.setMemoryProjectName(rs.getString("memoryProjectName"));
memoryProjectList.add(memoryProject);
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
return memoryProjectList;
}



// getList_JoinByFK  Memory_project
public List<MemoryProject> getList_JoinByMemberAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryProject> memoryProjectList = new ArrayList<MemoryProject>();
final String sql = "SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHRER b.memberAccount = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryProject memoryProject = new MemoryProject();
while(rs.next()){
memoryProject = new MemoryProject();
memoryProject.setMemoryProjectId(rs.getString("memoryProjectId"));
memoryProject.setMemoryProjectName(rs.getString("memoryProjectName"));
memoryProjectList.add(memoryProject);
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
return memoryProjectList;
}



//countTotal Memory_project
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_project";
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


//countTotalList MemoryProject
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_project "+
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


// countTotalList_JoinByFk Memory_project
public Integer countTotal_JoinByMemberAccount(SqlUtil sqlUtil) {
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




}

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

import com.graduate.webapp.rds.dao.MemoryBookDAO;
import com.graduate.webapp.rds.entity.MemoryBook;

public class MemoryBookDAOImpl implements MemoryBookDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Memory_book
public void insert(MemoryBook memoryBook) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_book(memoryBookId,memoryBookName , memoryBookCreateDate , memoryBookModifyDate , memberAccount , memoryProgectId , templateId , memoryBookStatusId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
smt.setString(2, memoryBook.getMemoryBookName());
smt.setString(5, memoryBook.getMember().getMemberAccount());
smt.setString(6, memoryBook.getMemoryProject().getMemoryProjectId());
smt.setInt(7, memoryBook.getTemplate().getTemplateId());
smt.setInt(8, memoryBook.getMemoryBookStatusId());
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


// insert Memory_book
public void insert(List<MemoryBook> memoryBookList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO memory_book(memoryBookId,memoryBookName , memoryBookCreateDate , memoryBookModifyDate , memberAccount , memoryProgectId , templateId , memoryBookStatusId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < memoryBookList.size() ; i++) {
smt.setInt(1, memoryBookList.get(0).getMemoryBookId());
smt.setString(2, memoryBookList.get(1).getMemoryBookName());
smt.setString(5, memoryBookList.get(4).getMember().getMemberAccount());
smt.setString(6, memoryBookList.get(5).getMemoryProject().getMemoryProjectId());
smt.setInt(7, memoryBookList.get(6).getTemplate().getTemplateId());
smt.setInt(8, memoryBookList.get(7).getMemoryBookStatusId());
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


// update Memory_book
public void update(MemoryBook memoryBook,MemoryBook oldMemoryBook) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE memory_book SET memoryBookName = ? , memoryBookCreateDate = ? , memoryBookModifyDate = ? , memberAccount = ? , memoryProgectId = ? , templateId = ? , memoryBookStatusId = ?  WHERE memoryBookId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, memoryBook.getMemoryBookName()!=null?memoryBook.getMemoryBookName():oldMemoryBook.getMemoryBookName());
smt.setString(4, memoryBook.getMember().getMemberAccount()!=null?memoryBook.getMember().getMemberAccount():oldMemoryBook.getMember().getMemberAccount());
smt.setString(5, memoryBook.getMemoryProject().getMemoryProjectId()!=null?memoryBook.getMemoryProject().getMemoryProjectId():oldMemoryBook.getMemoryProject().getMemoryProjectId());
smt.setInt(6, memoryBook.getTemplate().getTemplateId()!=null?memoryBook.getTemplate().getTemplateId():oldMemoryBook.getTemplate().getTemplateId());
smt.setInt(7, memoryBook.getMemoryBookStatusId()!=null?memoryBook.getMemoryBookStatusId():oldMemoryBook.getMemoryBookStatusId());
smt.setInt(8, memoryBook.getMemoryBookId()!=null?memoryBook.getMemoryBookId():oldMemoryBook.getMemoryBookId());
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


// delete Memory_book
public void delete(MemoryBook memoryBook) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM memory_book WHERE memoryBookId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
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


// GET Memory_book
public MemoryBook get(MemoryBook memoryBook) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a WHERE a.memoryBookId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
rs = smt.executeQuery();
if(rs.next()){
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMember().setMemberAccount(rs.getString("memberAccount"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memoryBook.getTemplate().setTemplateId(rs.getInt("templateId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
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
return memoryBook;
}


// get_JoinBy Memory_book
public MemoryBook get_JoinByMemberAccount(MemoryBook memoryBook) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProjectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
rs = smt.executeQuery();
if(rs.next()){
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMember().setMemberAccount(rs.getString("memberAccount"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memoryBook.getTemplate().setTemplateId(rs.getInt("templateId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
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
return memoryBook;
}

// get_JoinBy Memory_book
public MemoryBook get_JoinByMemoryProgectId(MemoryBook memoryBook) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProjectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProjectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProjectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
rs = smt.executeQuery();
if(rs.next()){
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMember().setMemberAccount(rs.getString("memberAccount"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memoryBook.getTemplate().setTemplateId(rs.getInt("templateId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
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
return memoryBook;
}

// get_JoinBy Memory_book
public MemoryBook get_JoinByTemplateId(MemoryBook memoryBook) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, memoryBook.getMemoryBookId());
rs = smt.executeQuery();
if(rs.next()){
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMember().setMemberAccount(rs.getString("memberAccount"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProgectId"));
memoryBook.getTemplate().setTemplateId(rs.getInt("templateId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
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
return memoryBook;
}



// getList Memory_book
public List<MemoryBook> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryBook> memoryBookList = new ArrayList<MemoryBook>();
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryBook memoryBook = new MemoryBook();
while(rs.next()){
memoryBook = new MemoryBook();
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
memoryBookList.add(memoryBook);
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
return memoryBookList;
}


//Get List  Memory_book
public List<MemoryBook> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryBook> memoryBookList = new ArrayList<MemoryBook>();
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryBook memoryBook = new MemoryBook();
while(rs.next()){
memoryBook = new MemoryBook();
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProgectId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
memoryBookList.add(memoryBook);
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
return memoryBookList;
}



// getList_JoinByFK  MemoryBook
public List<MemoryBook> getList_JoinByMemberAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryBook> memoryBookList = new ArrayList<MemoryBook>();
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHRER b.memberAccount = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryBook memoryBook = new MemoryBook();
while(rs.next()){
memoryBook = new MemoryBook();
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProgectId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
memoryBookList.add(memoryBook);
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
return memoryBookList;
}

// getList_JoinByFK  Memory_book
public List<MemoryBook> getList_JoinByMemoryProgectId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryBook> memoryBookList = new ArrayList<MemoryBook>();
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHRER b.memoryProgectId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryBook memoryBook = new MemoryBook();
while(rs.next()){
memoryBook = new MemoryBook();
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProgectId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
memoryBookList.add(memoryBook);
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
return memoryBookList;
}

// getList_JoinByFK  Memory_book
public List<MemoryBook> getList_JoinByTemplateId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<MemoryBook> memoryBookList = new ArrayList<MemoryBook>();
final String sql = "SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHRER b.templateId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
MemoryBook memoryBook = new MemoryBook();
while(rs.next()){
memoryBook = new MemoryBook();
memoryBook.setMemoryBookId(rs.getInt("memoryBookId"));
memoryBook.setMemoryBookName(rs.getString("memoryBookName"));
memoryBook.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
memoryBook.setMemoryBookStatusId(rs.getInt("memoryBookStatusId"));
memoryBookList.add(memoryBook);
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
return memoryBookList;
}



//countTotal Memory_book
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_book";
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


//countTotalList Memory_book
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM memory_book "+
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


// countTotalList_JoinByFk Memory_book
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

// countTotalList_JoinByFk Memory_book
public Integer countTotal_JoinByMemoryProgectId(SqlUtil sqlUtil) {
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

// countTotalList_JoinByFk Memory_book
public Integer countTotal_JoinByTemplateId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM  memory_book a  WHRER b.memberAccount = ? "+
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

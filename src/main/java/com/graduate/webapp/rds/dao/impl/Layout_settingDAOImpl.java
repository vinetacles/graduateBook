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

import com.graduate.webapp.rds.dao.Layout_settingDAO;
import com.graduate.webapp.rds.entity.Layout_setting;

public class Layout_settingDAOImpl implements Layout_settingDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Layout_setting
public void insert(Layout_setting layout_setting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_setting(layoutSettingId,layoutTypeId , frameX , frameY , frameWidth , frameHeight , templateId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutSettingId());
smt.setInt(2, layout_setting.getLayoutType().getLayoutTypeId());
smt.setInt(3, layout_setting.getFrameX());
smt.setInt(4, layout_setting.getFrameY());
smt.setInt(5, layout_setting.getFrameWidth());
smt.setInt(6, layout_setting.getFrameHeight());
smt.setInt(7, layout_setting.getTemplate().getTemplateId());
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


// insert Layout_setting
public void insert(List<Layout_setting> layout_settingList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_setting(layoutSettingId,layoutTypeId , frameX , frameY , frameWidth , frameHeight , templateId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < layout_settingList.size() ; i++) {
smt.setInt(1, layout_settingList.get(0).getLayoutSettingId());
smt.setInt(2, layout_settingList.get(1).getLayoutType().getLayoutTypeId());
smt.setInt(3, layout_settingList.get(2).getFrameX());
smt.setInt(4, layout_settingList.get(3).getFrameY());
smt.setInt(5, layout_settingList.get(4).getFrameWidth());
smt.setInt(6, layout_settingList.get(5).getFrameHeight());
smt.setInt(7, layout_settingList.get(6).getTemplate().getTemplateId());
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


// update Layout_setting
public void update(Layout_setting layout_setting,Layout_setting oldLayout_setting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE layout_setting SET layoutTypeId = ? , frameX = ? , frameY = ? , frameWidth = ? , frameHeight = ? , templateId = ?  WHERE layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutType().getLayoutTypeId()!=null?layout_setting.getLayoutType().getLayoutTypeId():oldLayout_setting.getLayoutType().getLayoutTypeId());
smt.setInt(2, layout_setting.getFrameX()!=null?layout_setting.getFrameX():oldLayout_setting.getFrameX());
smt.setInt(3, layout_setting.getFrameY()!=null?layout_setting.getFrameY():oldLayout_setting.getFrameY());
smt.setInt(4, layout_setting.getFrameWidth()!=null?layout_setting.getFrameWidth():oldLayout_setting.getFrameWidth());
smt.setInt(5, layout_setting.getFrameHeight()!=null?layout_setting.getFrameHeight():oldLayout_setting.getFrameHeight());
smt.setInt(6, layout_setting.getTemplate().getTemplateId()!=null?layout_setting.getTemplate().getTemplateId():oldLayout_setting.getTemplate().getTemplateId());
smt.setInt(7, layout_setting.getLayoutSettingId()!=null?layout_setting.getLayoutSettingId():oldLayout_setting.getLayoutSettingId());
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


// delete Layout_setting
public void delete(Layout_setting layout_setting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM layout_setting WHERE layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutSettingId());
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


// GET Layout_setting
public Layout_setting get(Layout_setting layout_setting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a WHERE a.layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_setting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layout_setting;
}


// get_JoinBy Layout_setting
public Layout_setting get_JoinByLayoutTypeId(Layout_setting layout_setting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_setting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layout_setting;
}

// get_JoinBy Layout_setting
public Layout_setting get_JoinByTemplateId(Layout_setting layout_setting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_setting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_setting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layout_setting;
}



// getList Layout_setting
public List<Layout_setting> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Layout_setting> layout_settingList = new ArrayList<Layout_setting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Layout_setting layout_setting = new Layout_setting();
while(rs.next()){
layout_setting = new Layout_setting();
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_settingList.add(layout_setting);
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
return layout_settingList;
}


//Get List  Layout_setting
public List<Layout_setting> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Layout_setting> layout_settingList = new ArrayList<Layout_setting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Layout_setting layout_setting = new Layout_setting();
while(rs.next()){
layout_setting = new Layout_setting();
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_settingList.add(layout_setting);
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
return layout_settingList;
}



// getList_JoinByFK  Layout_setting
public List<Layout_setting> getList_JoinByLayoutTypeId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Layout_setting> layout_settingList = new ArrayList<Layout_setting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.layoutTypeId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Layout_setting layout_setting = new Layout_setting();
while(rs.next()){
layout_setting = new Layout_setting();
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_settingList.add(layout_setting);
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
return layout_settingList;
}

// getList_JoinByFK  Layout_setting
public List<Layout_setting> getList_JoinByTemplateId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Layout_setting> layout_settingList = new ArrayList<Layout_setting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.templateId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Layout_setting layout_setting = new Layout_setting();
while(rs.next()){
layout_setting = new Layout_setting();
layout_setting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layout_setting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_setting.setFrameX(rs.getInt("frameX"));
layout_setting.setFrameY(rs.getInt("frameY"));
layout_setting.setFrameWidth(rs.getInt("frameWidth"));
layout_setting.setFrameHeight(rs.getInt("frameHeight"));
layout_settingList.add(layout_setting);
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
return layout_settingList;
}



//countTotal Layout_setting
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM layout_setting";
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


//countTotalList Layout_setting
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM layout_setting "+
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


// countTotalList_JoinByFk Layout_setting
public Integer countTotal_JoinByLayoutTypeId(SqlUtil sqlUtil) {
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

// countTotalList_JoinByFk Layout_setting
public Integer countTotal_JoinByTemplateId(SqlUtil sqlUtil) {
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

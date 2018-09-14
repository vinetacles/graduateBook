package com.graduate.webapp.rds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;


import javax.sql.DataSource;

import com.egroup.util.SqlUtil;
import com.graduate.webapp.rds.dao.LayoutSettingDAO;
import com.graduate.webapp.rds.entity.LayoutSetting;

public class LayoutSettingDAOImpl implements LayoutSettingDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Layout_setting
public void insert(LayoutSetting layoutSetting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_setting(layoutSettingId,layoutTypeId , frameX , frameY , frameWidth , frameHeight , templateId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutSettingId());
smt.setInt(2, layoutSetting.getLayoutType().getLayoutTypeId());
smt.setInt(3, layoutSetting.getFrameX());
smt.setInt(4, layoutSetting.getFrameY());
smt.setInt(5, layoutSetting.getFrameWidth());
smt.setInt(6, layoutSetting.getFrameHeight());
smt.setInt(7, layoutSetting.getTemplate().getTemplateId());
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
public void insert(List<LayoutSetting> layoutSettingList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_setting(layoutSettingId,layoutTypeId , frameX , frameY , frameWidth , frameHeight , templateId ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < layoutSettingList.size() ; i++) {
smt.setInt(1, layoutSettingList.get(0).getLayoutSettingId());
smt.setInt(2, layoutSettingList.get(1).getLayoutType().getLayoutTypeId());
smt.setInt(3, layoutSettingList.get(2).getFrameX());
smt.setInt(4, layoutSettingList.get(3).getFrameY());
smt.setInt(5, layoutSettingList.get(4).getFrameWidth());
smt.setInt(6, layoutSettingList.get(5).getFrameHeight());
smt.setInt(7, layoutSettingList.get(6).getTemplate().getTemplateId());
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
public void update(LayoutSetting layoutSetting,LayoutSetting oldLayoutSetting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE layout_setting SET layoutTypeId = ? , frameX = ? , frameY = ? , frameWidth = ? , frameHeight = ? , templateId = ?  WHERE layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutType().getLayoutTypeId()!=null?layoutSetting.getLayoutType().getLayoutTypeId():oldLayoutSetting.getLayoutType().getLayoutTypeId());
smt.setInt(2, layoutSetting.getFrameX()!=null?layoutSetting.getFrameX():oldLayoutSetting.getFrameX());
smt.setInt(3, layoutSetting.getFrameY()!=null?layoutSetting.getFrameY():oldLayoutSetting.getFrameY());
smt.setInt(4, layoutSetting.getFrameWidth()!=null?layoutSetting.getFrameWidth():oldLayoutSetting.getFrameWidth());
smt.setInt(5, layoutSetting.getFrameHeight()!=null?layoutSetting.getFrameHeight():oldLayoutSetting.getFrameHeight());
smt.setInt(6, layoutSetting.getTemplate().getTemplateId()!=null?layoutSetting.getTemplate().getTemplateId():oldLayoutSetting.getTemplate().getTemplateId());
smt.setInt(7, layoutSetting.getLayoutSettingId()!=null?layoutSetting.getLayoutSettingId():oldLayoutSetting.getLayoutSettingId());
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
public void delete(LayoutSetting layoutSetting) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM layout_setting WHERE layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutSettingId());
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
public LayoutSetting get(LayoutSetting layoutSetting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a WHERE a.layoutSettingId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSetting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layoutSetting;
}


// get_JoinBy Layout_setting
public LayoutSetting get_JoinByLayoutTypeId(LayoutSetting layoutSetting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSetting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layoutSetting;
}

// get_JoinBy Layout_setting
public LayoutSetting get_JoinByTemplateId(LayoutSetting layoutSetting) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutSetting.getLayoutSettingId());
rs = smt.executeQuery();
if(rs.next()){
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSetting.getTemplate().setTemplateId(rs.getInt("templateId"));
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
return layoutSetting;
}



// getList Layout_setting
public List<LayoutSetting> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<LayoutSetting> layoutSettingList = new ArrayList<LayoutSetting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
LayoutSetting layoutSetting = new LayoutSetting();
while(rs.next()){
layoutSetting = new LayoutSetting();
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSettingList.add(layoutSetting);
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
return layoutSettingList;
}


//Get List  Layout_setting
public List<LayoutSetting> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<LayoutSetting> layoutSettingList = new ArrayList<LayoutSetting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
LayoutSetting layoutSetting = new LayoutSetting();
while(rs.next()){
layoutSetting = new LayoutSetting();
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSettingList.add(layoutSetting);
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
return layoutSettingList;
}



// getList_JoinByFK  Layout_setting
public List<LayoutSetting> getList_JoinByLayoutTypeId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<LayoutSetting> layoutSettingList = new ArrayList<LayoutSetting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.layoutTypeId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
LayoutSetting layoutSetting = new LayoutSetting();
while(rs.next()){
layoutSetting = new LayoutSetting();
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSettingList.add(layoutSetting);
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
return layoutSettingList;
}

// getList_JoinByFK  Layout_setting
public List<LayoutSetting> getList_JoinByTemplateId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<LayoutSetting> layoutSettingList = new ArrayList<LayoutSetting>();
final String sql = "SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHRER b.templateId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
LayoutSetting layoutSetting = new LayoutSetting();
while(rs.next()){
layoutSetting = new LayoutSetting();
layoutSetting.setLayoutSettingId(rs.getInt("layoutSettingId"));
layoutSetting.getLayoutType().setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutSetting.setFrameX(rs.getInt("frameX"));
layoutSetting.setFrameY(rs.getInt("frameY"));
layoutSetting.setFrameWidth(rs.getInt("frameWidth"));
layoutSetting.setFrameHeight(rs.getInt("frameHeight"));
layoutSettingList.add(layoutSetting);
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
return layoutSettingList;
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

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

import com.graduate.webapp.rds.dao.PageDetailDAO;
import com.graduate.webapp.rds.entity.PageDetail;

public class PageDetailDAOImpl implements PageDetailDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Page_detail
public void insert(PageDetail pageDetail) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO pageDetail(pageDetailId,pageId , photoId , layoutSettingId ) VALUES ( ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
smt.setInt(2, pageDetail.getPage().getPageId());
smt.setInt(3, pageDetail.getPhoto().getPhotoId());
smt.setInt(4, pageDetail.getLayoutSetting().getLayoutSettingId());
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


// insert PageDetail
public void insert(List<PageDetail> pageDetailList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO page_detail(pageDetailId,pageId , photoId , layoutSettingId ) VALUES ( ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < pageDetailList.size() ; i++) {
smt.setInt(1, pageDetailList.get(0).getPageDetailId());
smt.setInt(2, pageDetailList.get(1).getPage().getPageId());
smt.setInt(3, pageDetailList.get(2).getPhoto().getPhotoId());
smt.setInt(4, pageDetailList.get(3).getLayoutSetting().getLayoutSettingId());
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


// update PageDetail
public void update(PageDetail pageDetail,PageDetail oldPageDetail) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE page_detail SET pageId = ? , photoId = ? , layoutSettingId = ?  WHERE pageDetailId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPage().getPageId()!=null?pageDetail.getPage().getPageId():oldPageDetail.getPage().getPageId());
smt.setInt(2, pageDetail.getPhoto().getPhotoId()!=null?pageDetail.getPhoto().getPhotoId():oldPageDetail.getPhoto().getPhotoId());
smt.setInt(3, pageDetail.getLayoutSetting().getLayoutSettingId()!=null?pageDetail.getLayoutSetting().getLayoutSettingId():oldPageDetail.getLayoutSetting().getLayoutSettingId());
smt.setInt(4, pageDetail.getPageDetailId()!=null?pageDetail.getPageDetailId():oldPageDetail.getPageDetailId());
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


// delete PageDetail
public void delete(PageDetail pageDetail) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM page_detail WHERE pageDetailId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
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


// GET Page_detail
public PageDetail get(PageDetail pageDetail) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a WHERE a.pageDetailId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
rs = smt.executeQuery();
if(rs.next()){
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getPage().setPageId(rs.getInt("pageId"));
pageDetail.getPhoto().setPhotoId(rs.getInt("photoId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
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
return pageDetail;
}


// get_JoinBy PageDetail
public PageDetail get_JoinByPageId(PageDetail pageDetail) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
rs = smt.executeQuery();
if(rs.next()){
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getPage().setPageId(rs.getInt("pageId"));
pageDetail.getPhoto().setPhotoId(rs.getInt("photoId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
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
return pageDetail;
}

// get_JoinBy Page_detail
public PageDetail get_JoinByPhotoId(PageDetail pageDetail) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
rs = smt.executeQuery();
if(rs.next()){
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getPage().setPageId(rs.getInt("pageId"));
pageDetail.getPhoto().setPhotoId(rs.getInt("photoId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
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
return pageDetail;
}

// get_JoinBy Page_detail
public PageDetail get_JoinByLayoutSettingId(PageDetail pageDetail) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, pageDetail.getPageDetailId());
rs = smt.executeQuery();
if(rs.next()){
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getPage().setPageId(rs.getInt("pageId"));
pageDetail.getPhoto().setPhotoId(rs.getInt("photoId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
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
return pageDetail;
}



// getList Page_detail
public List<PageDetail> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<PageDetail> pageDetailList = new ArrayList<PageDetail>();
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
PageDetail pageDetail = new PageDetail();
while(rs.next()){
pageDetail = new PageDetail();
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
pageDetailList.add(pageDetail);
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
return pageDetailList;
}


//Get List  PageDetail
public List<PageDetail> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<PageDetail> pageDetailList = new ArrayList<PageDetail>();
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
PageDetail pageDetail = new PageDetail();
while(rs.next()){
pageDetail = new PageDetail();
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
pageDetailList.add(pageDetail);
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
return pageDetailList;
}



// getList_JoinByFK  Page_detail
public List<Page_detail> getList_JoinByPageId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Page_detail> page_detailList = new ArrayList<Page_detail>();
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHRER b.pageId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Page_detail page_detail = new Page_detail();
while(rs.next()){
page_detail = new Page_detail();
page_detail.setPageDetailId(rs.getInt("pageDetailId"));
page_detail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
page_detailList.add(page_detail);
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
return page_detailList;
}

// getList_JoinByFK  Page_detail
public List<PageDetail> getList_JoinByPhotoId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<PageDetail> pageDetailList = new ArrayList<PageDetail>();
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHRER b.photoId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
PageDetail pageDetail = new PageDetail();
while(rs.next()){
pageDetail = new PageDetail();
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
pageDetailList.add(pageDetail);
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
return pageDetailList;
}

// getList_JoinByFK  PageDetail
public List<PageDetail> getList_JoinByLayoutSettingId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<PageDetail> pageDetailList = new ArrayList<PageDetail>();
final String sql = "SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHRER b.layoutSettingId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
PageDetail pageDetail = new PageDetail();
while(rs.next()){
pageDetail = new PageDetail();
pageDetail.setPageDetailId(rs.getInt("pageDetailId"));
pageDetail.getLayoutSetting().setLayoutSettingId(rs.getInt("layoutSettingId"));
pageDetailList.add(pageDetail);
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
return pageDetailList;
}



//countTotal Page_detail
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM page_detail";
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


//countTotalList Page_detail
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM page_detail "+
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


// countTotalList_JoinByFk Page_detail
public Integer countTotal_JoinByPageId(SqlUtil sqlUtil) {
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

// countTotalList_JoinByFk Page_detail
public Integer countTotal_JoinByPhotoId(SqlUtil sqlUtil) {
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

// countTotalList_JoinByFk Page_detail
public Integer countTotal_JoinByLayoutSettingId(SqlUtil sqlUtil) {
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

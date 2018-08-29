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

import com.graduate.webapp.rds.dao.PhotoDAO;
import com.graduate.webapp.rds.entity.Photo;

public class PhotoDAOImpl implements PhotoDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Photo
public void insert(Photo photo) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO photo(photoId,memoryProjectId , photoPath , photoLocation , photoDate , memberAccount , photoSize , photoHeight , photoWeight , photoCreateDate , photoModifyDate ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, photo.getPhotoId());
smt.setString(2, photo.getMemoryProject().getMemoryProjectId());
smt.setString(3, photo.getPhotoPath());
smt.setString(4, photo.getPhotoLocation());
smt.setString(6, photo.getMember().getMemberAccount());
smt.setInt(7, photo.getPhotoSize());
smt.setInt(8, photo.getPhotoHeight());
smt.setInt(9, photo.getPhotoWeight());
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


// insert Photo
public void insert(List<Photo> photoList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO photo(photoId,memoryProjectId , photoPath , photoLocation , photoDate , memberAccount , photoSize , photoHeight , photoWeight , photoCreateDate , photoModifyDate ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < photoList.size() ; i++) {
smt.setInt(1, photoList.get(0).getPhotoId());
smt.setString(2, photoList.get(1).getMemoryProject().getMemoryProjectId());
smt.setString(3, photoList.get(2).getPhotoPath());
smt.setString(4, photoList.get(3).getPhotoLocation());
smt.setString(6, photoList.get(5).getMember().getMemberAccount());
smt.setInt(7, photoList.get(6).getPhotoSize());
smt.setInt(8, photoList.get(7).getPhotoHeight());
smt.setInt(9, photoList.get(8).getPhotoWeight());
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


// update Photo
public void update(Photo photo,Photo oldPhoto) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE photo SET memoryProjectId = ? , photoPath = ? , photoLocation = ? , photoDate = ? , memberAccount = ? , photoSize = ? , photoHeight = ? , photoWeight = ? , photoCreateDate = ? , photoModifyDate = ?  WHERE photoId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, photo.getMemoryProject().getMemoryProjectId()!=null?photo.getMemoryProject().getMemoryProjectId():oldPhoto.getMemoryProject().getMemoryProjectId());
smt.setString(2, photo.getPhotoPath()!=null?photo.getPhotoPath():oldPhoto.getPhotoPath());
smt.setString(3, photo.getPhotoLocation()!=null?photo.getPhotoLocation():oldPhoto.getPhotoLocation());
smt.setString(5, photo.getMember().getMemberAccount()!=null?photo.getMember().getMemberAccount():oldPhoto.getMember().getMemberAccount());
smt.setInt(6, photo.getPhotoSize()!=null?photo.getPhotoSize():oldPhoto.getPhotoSize());
smt.setInt(7, photo.getPhotoHeight()!=null?photo.getPhotoHeight():oldPhoto.getPhotoHeight());
smt.setInt(8, photo.getPhotoWeight()!=null?photo.getPhotoWeight():oldPhoto.getPhotoWeight());
smt.setInt(11, photo.getPhotoId()!=null?photo.getPhotoId():oldPhoto.getPhotoId());
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


// delete Photo
public void delete(Photo photo) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM photo WHERE photoId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, photo.getPhotoId());
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


// GET Photo
public Photo get(Photo photo) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a WHERE a.photoId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, photo.getPhotoId());
rs = smt.executeQuery();
if(rs.next()){
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.getMember().setMemberAccount(rs.getString("memberAccount"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
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
return photo;
}


// get_JoinBy Photo
public Photo get_JoinByMemoryProjectId(Photo photo) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, photo.getPhotoId());
rs = smt.executeQuery();
if(rs.next()){
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.getMember().setMemberAccount(rs.getString("memberAccount"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
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
return photo;
}

// get_JoinBy Photo
public Photo get_JoinByMemberAccount(Photo photo) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "[SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.layoutSettingId , a.layoutTypeId , a.frameX , a.frameY , a.frameWidth , a.frameHeight , a.templateId FROM layout_setting a  JOIN template b ON a.templateId = b.templateId WHERE a.layoutSettingId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryBookId , a.memoryBookName , a.memoryBookCreateDate , a.memoryBookModifyDate , a.memberAccount , a.memoryProgectId , a.templateId , a.memoryBookStatusId FROM memory_book a  WHERE a.memoryBookId = ? , SELECT a.memoryProjectId , a.memoryProjectName , a.memoryProjectCreateDate , a.memoryProjectUpdateDate , a.memberAccount FROM memory_project a  JOIN member b ON a.memberAccount = b.memberAccount WHERE a.memoryProjectId = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.memoryProjectInvitedAccount , a.memoryProjectId , a.memoryProjectInvitedStatus , a.memoryProjectInvitedCreateDate , a.memoryProjectInvitedUpdateDate , a.memoryProjectInvitedNo FROM memory_project_invited a  WHERE a.memoryProjectInvitedAccount = ? , SELECT a.pageId , a.memoryBookId , a.pagenumebr FROM page a  WHERE a.pageId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.pageDetailId , a.pageId , a.photoId , a.layoutSettingId FROM page_detail a  JOIN layout_setting b ON a.layoutSettingId = b.layoutSettingId WHERE a.pageDetailId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? , SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHERE a.photoId = ? ]";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, photo.getPhotoId());
rs = smt.executeQuery();
if(rs.next()){
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.getMember().setMemberAccount(rs.getString("memberAccount"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
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
return photo;
}



// getList Photo
public List<Photo> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Photo> photoList = new ArrayList<Photo>();
final String sql = "SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Photo photo = new Photo();
while(rs.next()){
photo = new Photo();
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
photoList.add(photo);
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
return photoList;
}


//Get List  Photo
public List<Photo> getList_Join(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Photo> photoList = new ArrayList<Photo>();
final String sql = "SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Photo photo = new Photo();
while(rs.next()){
photo = new Photo();
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
photoList.add(photo);
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
return photoList;
}



// getList_JoinByFK  Photo
public List<Photo> getList_JoinByMemoryProjectId(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Photo> photoList = new ArrayList<Photo>();
final String sql = "SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHRER b.memoryProjectId = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Photo photo = new Photo();
while(rs.next()){
photo = new Photo();
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
photoList.add(photo);
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
return photoList;
}

// getList_JoinByFK  Photo
public List<Photo> getList_JoinByMemberAccount(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Photo> photoList = new ArrayList<Photo>();
final String sql = "SELECT a.photoId , a.memoryProjectId , a.photoPath , a.photoLocation , a.photoDate , a.memberAccount , a.photoSize , a.photoHeight , a.photoWeight , a.photoCreateDate , a.photoModifyDate FROM photo a  WHRER b.memberAccount = ? "+
sqlUtil.getWhereGenerator().getWhereSql(false)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Photo photo = new Photo();
while(rs.next()){
photo = new Photo();
photo.setPhotoId(rs.getInt("photoId"));
photo.getMemoryProject().setMemoryProjectId(rs.getString("memoryProjectId"));
photo.setPhotoPath(rs.getString("photoPath"));
photo.setPhotoLocation(rs.getString("photoLocation"));
photo.setPhotoSize(rs.getInt("photoSize"));
photo.setPhotoHeight(rs.getInt("photoHeight"));
photo.setPhotoWeight(rs.getInt("photoWeight"));
photoList.add(photo);
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
return photoList;
}



//countTotal Photo
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM photo";
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


//countTotalList Photo
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM photo "+
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


// countTotalList_JoinByFk Photo
public Integer countTotal_JoinByMemoryProjectId(SqlUtil sqlUtil) {
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

// countTotalList_JoinByFk Photo
public Integer countTotal_JoinByMemberAccount(SqlUtil sqlUtil) {
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

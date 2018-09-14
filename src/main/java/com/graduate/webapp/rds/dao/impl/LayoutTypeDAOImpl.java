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

import com.graduate.webapp.rds.dao.LayoutTypeDAO;
import com.graduate.webapp.rds.entity.LayoutType;

public class LayoutTypeDAOImpl implements LayoutTypeDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Layout_type
public void insert(LayoutType layoutType) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_type(layoutTypeId,frameNumber ) VALUES ( ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutType.getLayoutTypeId());
smt.setInt(2, layoutType.getFrameNumber());
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


// insert Layout_type
public void insert(List<LayoutType> layoutTypeList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layoutType(layoutTypeId,frameNumber ) VALUES ( ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < layoutTypeList.size() ; i++) {
smt.setInt(1, layoutTypeList.get(0).getLayoutTypeId());
smt.setInt(2, layoutTypeList.get(1).getFrameNumber());
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


// update Layout_type
public void update(LayoutType layoutType,LayoutType oldLayoutType) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE layoutType SET frameNumber = ?  WHERE layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutType.getFrameNumber()!=null?layoutType.getFrameNumber():oldLayoutType.getFrameNumber());
smt.setInt(2, layoutType.getLayoutTypeId()!=null?layoutType.getLayoutTypeId():oldLayoutType.getLayoutTypeId());
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


// delete Layout_type
public void delete(LayoutType layoutType) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM layoutType WHERE layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutType.getLayoutTypeId());
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


// GET Layout_type
public LayoutType get(LayoutType layoutType) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.layoutTypeId , a.frameNumber FROM layoutType a WHERE a.layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layoutType.getLayoutTypeId());
rs = smt.executeQuery();
if(rs.next()){
layoutType.setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutType.setFrameNumber(rs.getInt("frameNumber"));
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
return layoutType;
}




// getList Layout_type
public List<LayoutType> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<LayoutType> layoutTypeList = new ArrayList<LayoutType>();
final String sql = "SELECT a.layoutTypeId , a.frameNumber FROM layoutType a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
LayoutType layoutType = new LayoutType();
while(rs.next()){
layoutType = new LayoutType();
layoutType.setLayoutTypeId(rs.getInt("layoutTypeId"));
layoutType.setFrameNumber(rs.getInt("frameNumber"));
layoutTypeList.add(layoutType);
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
return layoutTypeList;
}






//countTotal LayoutType
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM layout_type";
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


//countTotalList Layout_type
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM layout_type "+
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





}

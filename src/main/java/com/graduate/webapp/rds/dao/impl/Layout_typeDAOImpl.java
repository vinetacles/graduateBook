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

import com.graduate.webapp.rds.dao.Layout_typeDAO;
import com.graduate.webapp.rds.entity.Layout_type;

public class Layout_typeDAOImpl implements Layout_typeDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Layout_type
public void insert(Layout_type layout_type) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_type(layoutTypeId,frameNumber ) VALUES ( ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_type.getLayoutTypeId());
smt.setInt(2, layout_type.getFrameNumber());
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
public void insert(List<Layout_type> layout_typeList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO layout_type(layoutTypeId,frameNumber ) VALUES ( ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < layout_typeList.size() ; i++) {
smt.setInt(1, layout_typeList.get(0).getLayoutTypeId());
smt.setInt(2, layout_typeList.get(1).getFrameNumber());
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
public void update(Layout_type layout_type,Layout_type oldLayout_type) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE layout_type SET frameNumber = ?  WHERE layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_type.getFrameNumber()!=null?layout_type.getFrameNumber():oldLayout_type.getFrameNumber());
smt.setInt(2, layout_type.getLayoutTypeId()!=null?layout_type.getLayoutTypeId():oldLayout_type.getLayoutTypeId());
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
public void delete(Layout_type layout_type) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM layout_type WHERE layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_type.getLayoutTypeId());
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
public Layout_type get(Layout_type layout_type) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.layoutTypeId , a.frameNumber FROM layout_type a WHERE a.layoutTypeId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, layout_type.getLayoutTypeId());
rs = smt.executeQuery();
if(rs.next()){
layout_type.setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_type.setFrameNumber(rs.getInt("frameNumber"));
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
return layout_type;
}




// getList Layout_type
public List<Layout_type> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Layout_type> layout_typeList = new ArrayList<Layout_type>();
final String sql = "SELECT a.layoutTypeId , a.frameNumber FROM layout_type a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Layout_type layout_type = new Layout_type();
while(rs.next()){
layout_type = new Layout_type();
layout_type.setLayoutTypeId(rs.getInt("layoutTypeId"));
layout_type.setFrameNumber(rs.getInt("frameNumber"));
layout_typeList.add(layout_type);
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
return layout_typeList;
}






//countTotal Layout_type
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

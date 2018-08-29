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

import com.graduate.webapp.rds.dao.TemplateDAO;
import com.graduate.webapp.rds.entity.Template;

public class TemplateDAOImpl implements TemplateDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Template
public void insert(Template template) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO template(templateId,photoNumberRangeMin , photoNumberRangeMax , pageWidth , pageHeight ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, template.getTemplateId());
smt.setInt(2, template.getPhotoNumberRangeMin());
smt.setInt(3, template.getPhotoNumberRangeMax());
smt.setInt(4, template.getPageWidth());
smt.setInt(5, template.getPageHeight());
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


// insert Template
public void insert(List<Template> templateList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO template(templateId,photoNumberRangeMin , photoNumberRangeMax , pageWidth , pageHeight ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < templateList.size() ; i++) {
smt.setInt(1, templateList.get(0).getTemplateId());
smt.setInt(2, templateList.get(1).getPhotoNumberRangeMin());
smt.setInt(3, templateList.get(2).getPhotoNumberRangeMax());
smt.setInt(4, templateList.get(3).getPageWidth());
smt.setInt(5, templateList.get(4).getPageHeight());
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


// update Template
public void update(Template template,Template oldTemplate) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE template SET photoNumberRangeMin = ? , photoNumberRangeMax = ? , pageWidth = ? , pageHeight = ?  WHERE templateId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, template.getPhotoNumberRangeMin()!=null?template.getPhotoNumberRangeMin():oldTemplate.getPhotoNumberRangeMin());
smt.setInt(2, template.getPhotoNumberRangeMax()!=null?template.getPhotoNumberRangeMax():oldTemplate.getPhotoNumberRangeMax());
smt.setInt(3, template.getPageWidth()!=null?template.getPageWidth():oldTemplate.getPageWidth());
smt.setInt(4, template.getPageHeight()!=null?template.getPageHeight():oldTemplate.getPageHeight());
smt.setInt(5, template.getTemplateId()!=null?template.getTemplateId():oldTemplate.getTemplateId());
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


// delete Template
public void delete(Template template) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM template WHERE templateId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, template.getTemplateId());
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


// GET Template
public Template get(Template template) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.templateId , a.photoNumberRangeMin , a.photoNumberRangeMax , a.pageWidth , a.pageHeight FROM template a WHERE a.templateId = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setInt(1, template.getTemplateId());
rs = smt.executeQuery();
if(rs.next()){
template.setTemplateId(rs.getInt("templateId"));
template.setPhotoNumberRangeMin(rs.getInt("photoNumberRangeMin"));
template.setPhotoNumberRangeMax(rs.getInt("photoNumberRangeMax"));
template.setPageWidth(rs.getInt("pageWidth"));
template.setPageHeight(rs.getInt("pageHeight"));
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
return template;
}




// getList Template
public List<Template> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Template> templateList = new ArrayList<Template>();
final String sql = "SELECT a.templateId , a.photoNumberRangeMin , a.photoNumberRangeMax , a.pageWidth , a.pageHeight FROM template a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Template template = new Template();
while(rs.next()){
template = new Template();
template.setTemplateId(rs.getInt("templateId"));
template.setPhotoNumberRangeMin(rs.getInt("photoNumberRangeMin"));
template.setPhotoNumberRangeMax(rs.getInt("photoNumberRangeMax"));
template.setPageWidth(rs.getInt("pageWidth"));
template.setPageHeight(rs.getInt("pageHeight"));
templateList.add(template);
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
return templateList;
}






//countTotal Template
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM template";
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


//countTotalList Template
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM template "+
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

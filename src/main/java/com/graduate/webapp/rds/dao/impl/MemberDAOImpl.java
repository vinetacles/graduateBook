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

import com.graduate.webapp.rds.dao.MemberDAO;
import com.graduate.webapp.rds.entity.Member;

public class MemberDAOImpl implements MemberDAO{
private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
this.dataSource = dataSource;
}
// insert Member
public void insert(Member member) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO member(memberAccount,memberPassword , memberEmail , memberName , memberPhotoPath , isManager , memberRegisterDate , memberUpdateDate , memberResetPasswordDate ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, member.getMemberAccount());
smt.setString(2, member.getMemberPassword());
smt.setString(3, member.getMemberEmail());
smt.setString(4, member.getMemberName());
smt.setString(5, member.getMemberPhotoPath());
smt.setInt(6, member.getIsManager());
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


// insert Member
public void insert(List<Member> memberList) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "INSERT INTO member(memberAccount,memberPassword , memberEmail , memberName , memberPhotoPath , isManager , memberRegisterDate , memberUpdateDate , memberResetPasswordDate ) VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? )";
try {
conn = dataSource.getConnection();
for (int i = 0; i < memberList.size() ; i++) {
smt.setString(1, memberList.get(0).getMemberAccount());
smt.setString(2, memberList.get(1).getMemberPassword());
smt.setString(3, memberList.get(2).getMemberEmail());
smt.setString(4, memberList.get(3).getMemberName());
smt.setString(5, memberList.get(4).getMemberPhotoPath());
smt.setInt(6, memberList.get(5).getIsManager());
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


// update Member
public void update(Member member,Member oldMember) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "UPDATE member SET memberPassword = ? , memberEmail = ? , memberName = ? , memberPhotoPath = ? , isManager = ? , memberRegisterDate = ? , memberUpdateDate = ? , memberResetPasswordDate = ?  WHERE memberAccount = ? ";
try {
conn = dataSource.getConnection();
smt.setString(1, member.getMemberPassword()!=null?member.getMemberPassword():oldMember.getMemberPassword());
smt.setString(2, member.getMemberEmail()!=null?member.getMemberEmail():oldMember.getMemberEmail());
smt.setString(3, member.getMemberName()!=null?member.getMemberName():oldMember.getMemberName());
smt.setString(4, member.getMemberPhotoPath()!=null?member.getMemberPhotoPath():oldMember.getMemberPhotoPath());
smt.setInt(5, member.getIsManager()!=null?member.getIsManager():oldMember.getIsManager());
smt.setString(9, member.getMemberAccount()!=null?member.getMemberAccount():oldMember.getMemberAccount());
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


// delete Member
public void delete(Member member) {
Connection conn = null ;
PreparedStatement smt = null ;
final String sql = "DELETE FROM member WHERE memberAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, member.getMemberAccount());
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


// GET Member
public Member get(Member member) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
final String sql = "SELECT a.memberAccount , a.memberPassword , a.memberEmail , a.memberName , a.memberPhotoPath , a.isManager , a.memberRegisterDate , a.memberUpdateDate , a.memberResetPasswordDate FROM member a WHERE a.memberAccount = ? ";
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
smt.setString(1, member.getMemberAccount());
rs = smt.executeQuery();
if(rs.next()){
member.setMemberAccount(rs.getString("memberAccount"));
member.setMemberPassword(rs.getString("memberPassword"));
member.setMemberEmail(rs.getString("memberEmail"));
member.setMemberName(rs.getString("memberName"));
member.setMemberPhotoPath(rs.getString("memberPhotoPath"));
member.setIsManager(rs.getInt("isManager"));
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
return member;
}




// getList Member
public List<Member> getList(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
List<Member> memberList = new ArrayList<Member>();
final String sql = "SELECT a.memberAccount , a.memberPassword , a.memberEmail , a.memberName , a.memberPhotoPath , a.isManager , a.memberRegisterDate , a.memberUpdateDate , a.memberResetPasswordDate FROM member a "+
sqlUtil.getWhereGenerator().getWhereSql(true)+
sqlUtil.getOrderGenerator().getOrderSql()+
sqlUtil.getLimitGenerator().getLimitSql();
try {
conn = dataSource.getConnection();
smt = conn.prepareStatement(sql);
rs = smt.executeQuery();
Member member = new Member();
while(rs.next()){
member = new Member();
member.setMemberAccount(rs.getString("memberAccount"));
member.setMemberPassword(rs.getString("memberPassword"));
member.setMemberEmail(rs.getString("memberEmail"));
member.setMemberName(rs.getString("memberName"));
member.setMemberPhotoPath(rs.getString("memberPhotoPath"));
member.setIsManager(rs.getInt("isManager"));
memberList.add(member);
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
return memberList;
}






//countTotal Member
public Integer countTotal() {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM member";
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


//countTotalList Member
public Integer countTotal(SqlUtil sqlUtil) {
Connection conn = null ;
ResultSet rs = null ;
PreparedStatement smt = null ;
Integer countTotal = 0;
final String sql = "SELECT COUNT(*) FROM member "+
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

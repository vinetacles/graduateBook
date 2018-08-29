package com.graduate.webapp.rds.dao;

import java.util.List;
import com.graduate.webapp.rds.entity.Member;
import com.egroup.util.SqlUtil;

import javax.sql.DataSource;

public interface MemberDAO {
public void insert(Member member);
public void insert(List<Member> memberList);
public void update(Member member,Member oldMember);
public void delete(Member member);
public Member get(Member member);
public List<Member> getList(SqlUtil sqlUtil);
public Integer countTotal();
public Integer countTotal(SqlUtil sqlUtil);
}

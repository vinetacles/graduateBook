package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class MemoryProject {
// Database Column
private String memoryProjectId;
private String memoryProjectName;
private Member member = new Member();
// Program control

public String getMemoryProjectId() {
return memoryProjectId;
}
public void setMemoryProjectId(String memoryProjectId) {
this.memoryProjectId = memoryProjectId;
}

public String getMemoryProjectName() {
return memoryProjectName;
}
public void setMemoryProjectName(String memoryProjectName) {
this.memoryProjectName = memoryProjectName;
}

public Member getMember() {
return member;
}
public void setMember(Member member) {
this.member = member;
}

}

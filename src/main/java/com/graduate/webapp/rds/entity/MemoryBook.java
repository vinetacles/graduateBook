package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class MemoryBook {
// Database Column
private Integer memoryBookId;
private String memoryBookName;
private Member member = new Member();
private MemoryProject memoryProject = new MemoryProject();
private Template template = new Template();
private Integer memoryBookStatusId;
// Program control

public Integer getMemoryBookId() {
return memoryBookId;
}
public void setMemoryBookId(Integer memoryBookId) {
this.memoryBookId = memoryBookId;
}

public String getMemoryBookName() {
return memoryBookName;
}
public void setMemoryBookName(String memoryBookName) {
this.memoryBookName = memoryBookName;
}

public Member getMember() {
return member;
}
public void setMember(Member member) {
this.member = member;
}

public MemoryProject getMemoryProject() {
return memoryProject;
}
public void setMemoryProject(MemoryProject memoryProject) {
this.memoryProject = memoryProject;
}

public Template getTemplate() {
return template;
}
public void setTemplate(Template template) {
this.template = template;
}

public Integer getMemoryBookStatusId() {
return memoryBookStatusId;
}
public void setMemoryBookStatusId(Integer memoryBookStatusId) {
this.memoryBookStatusId = memoryBookStatusId;
}

}

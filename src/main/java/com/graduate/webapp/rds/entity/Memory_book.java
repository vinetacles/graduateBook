package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Memory_book {
// Database Column
private Integer memoryBookId;
private String memoryBookName;
private Member member = new Member();
private MemoryProject memory_project = new MemoryProject();
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

public MemoryProject getMemory_project() {
return memory_project;
}
public void setMemory_project(MemoryProject memory_project) {
this.memory_project = memory_project;
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

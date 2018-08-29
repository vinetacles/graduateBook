package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Page {
// Database Column
private Integer pageId;
private Memory_book memory_book = new Memory_book();
private Integer pagenumebr;
// Program control

public Integer getPageId() {
return pageId;
}
public void setPageId(Integer pageId) {
this.pageId = pageId;
}

public Memory_book getMemory_book() {
return memory_book;
}
public void setMemory_book(Memory_book memory_book) {
this.memory_book = memory_book;
}

public Integer getPagenumebr() {
return pagenumebr;
}
public void setPagenumebr(Integer pagenumebr) {
this.pagenumebr = pagenumebr;
}

}

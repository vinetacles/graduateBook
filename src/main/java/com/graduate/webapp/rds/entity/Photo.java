package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Photo {
// Database Column
private Integer photoId;
private MemoryProject memory_project = new MemoryProject();
private String photoPath;
private String photoLocation;
private Member member = new Member();
private Integer photoSize;
private Integer photoHeight;
private Integer photoWeight;
// Program control

public Integer getPhotoId() {
return photoId;
}
public void setPhotoId(Integer photoId) {
this.photoId = photoId;
}

public MemoryProject getMemory_project() {
return memory_project;
}
public void setMemory_project(MemoryProject memory_project) {
this.memory_project = memory_project;
}

public String getPhotoPath() {
return photoPath;
}
public void setPhotoPath(String photoPath) {
this.photoPath = photoPath;
}

public String getPhotoLocation() {
return photoLocation;
}
public void setPhotoLocation(String photoLocation) {
this.photoLocation = photoLocation;
}

public Member getMember() {
return member;
}
public void setMember(Member member) {
this.member = member;
}

public Integer getPhotoSize() {
return photoSize;
}
public void setPhotoSize(Integer photoSize) {
this.photoSize = photoSize;
}

public Integer getPhotoHeight() {
return photoHeight;
}
public void setPhotoHeight(Integer photoHeight) {
this.photoHeight = photoHeight;
}

public Integer getPhotoWeight() {
return photoWeight;
}
public void setPhotoWeight(Integer photoWeight) {
this.photoWeight = photoWeight;
}

}

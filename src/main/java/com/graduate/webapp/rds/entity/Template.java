package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Template {
// Database Column
private Integer templateId;
private Integer photoNumberRangeMin;
private Integer photoNumberRangeMax;
private Integer pageWidth;
private Integer pageHeight;
// Program control

public Integer getTemplateId() {
return templateId;
}
public void setTemplateId(Integer templateId) {
this.templateId = templateId;
}

public Integer getPhotoNumberRangeMin() {
return photoNumberRangeMin;
}
public void setPhotoNumberRangeMin(Integer photoNumberRangeMin) {
this.photoNumberRangeMin = photoNumberRangeMin;
}

public Integer getPhotoNumberRangeMax() {
return photoNumberRangeMax;
}
public void setPhotoNumberRangeMax(Integer photoNumberRangeMax) {
this.photoNumberRangeMax = photoNumberRangeMax;
}

public Integer getPageWidth() {
return pageWidth;
}
public void setPageWidth(Integer pageWidth) {
this.pageWidth = pageWidth;
}

public Integer getPageHeight() {
return pageHeight;
}
public void setPageHeight(Integer pageHeight) {
this.pageHeight = pageHeight;
}

}

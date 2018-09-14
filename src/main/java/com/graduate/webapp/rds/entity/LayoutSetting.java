package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class LayoutSetting {
// Database Column
private Integer layoutSettingId;
private LayoutType layoutType = new LayoutType();
private Integer frameX;
private Integer frameY;
private Integer frameWidth;
private Integer frameHeight;
private Template template = new Template();
// Program control

public Integer getLayoutSettingId() {
return layoutSettingId;
}
public void setLayoutSettingId(Integer layoutSettingId) {
this.layoutSettingId = layoutSettingId;
}

public LayoutType getLayoutType() {
return layoutType;
}
public void setLayoutType(LayoutType layoutType) {
this.layoutType = layoutType;
}

public Integer getFrameX() {
return frameX;
}
public void setFrameX(Integer frameX) {
this.frameX = frameX;
}

public Integer getFrameY() {
return frameY;
}
public void setFrameY(Integer frameY) {
this.frameY = frameY;
}

public Integer getFrameWidth() {
return frameWidth;
}
public void setFrameWidth(Integer frameWidth) {
this.frameWidth = frameWidth;
}

public Integer getFrameHeight() {
return frameHeight;
}
public void setFrameHeight(Integer frameHeight) {
this.frameHeight = frameHeight;
}

public Template getTemplate() {
return template;
}
public void setTemplate(Template template) {
this.template = template;
}

}

package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class LayoutType {
// Database Column
private Integer layoutTypeId;
private Integer frameNumber;
// Program control

public Integer getLayoutTypeId() {
return layoutTypeId;
}
public void setLayoutTypeId(Integer layoutTypeId) {
this.layoutTypeId = layoutTypeId;
}

public Integer getFrameNumber() {
return frameNumber;
}
public void setFrameNumber(Integer frameNumber) {
this.frameNumber = frameNumber;
}

}

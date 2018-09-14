package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class PageDetail {
// Database Column
private Integer pageDetailId;
private Page page = new Page();
private Photo photo = new Photo();
private LayoutSetting layoutSetting = new LayoutSetting();
// Program control

public Integer getPageDetailId() {
return pageDetailId;
}
public void setPageDetailId(Integer pageDetailId) {
this.pageDetailId = pageDetailId;
}

public Page getPage() {
return page;
}
public void setPage(Page page) {
this.page = page;
}

public Photo getPhoto() {
return photo;
}
public void setPhoto(Photo photo) {
this.photo = photo;
}

public LayoutSetting getLayoutSetting() {
return layoutSetting;
}
public void setLayout_setting(LayoutSetting layoutSetting) {
this.layoutSetting = layoutSetting;
}

}

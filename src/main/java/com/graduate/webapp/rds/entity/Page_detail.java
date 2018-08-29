package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Page_detail {
// Database Column
private Integer pageDetailId;
private Page page = new Page();
private Photo photo = new Photo();
private Layout_setting layout_setting = new Layout_setting();
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

public Layout_setting getLayout_setting() {
return layout_setting;
}
public void setLayout_setting(Layout_setting layout_setting) {
this.layout_setting = layout_setting;
}

}

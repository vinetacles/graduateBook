package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class Member {
// Database Column
private String memberAccount;
private String memberPassword;
private String memberEmail;
private String memberName;
private String memberPhotoPath;
private Integer isManager;
// Program control
private String isManagerString;

public String getMemberAccount() {
return memberAccount;
}
public void setMemberAccount(String memberAccount) {
this.memberAccount = memberAccount;
}

public String getMemberPassword() {
return memberPassword;
}
public void setMemberPassword(String memberPassword) {
this.memberPassword = memberPassword;
}

public String getMemberEmail() {
return memberEmail;
}
public void setMemberEmail(String memberEmail) {
this.memberEmail = memberEmail;
}

public String getMemberName() {
return memberName;
}
public void setMemberName(String memberName) {
this.memberName = memberName;
}

public String getMemberPhotoPath() {
return memberPhotoPath;
}
public void setMemberPhotoPath(String memberPhotoPath) {
this.memberPhotoPath = memberPhotoPath;
}

public String getIsManagerString() {
return isManagerString;
}
public void setIsManagerString(String isManagerString) {
this.isManagerString = isManagerString;
}

public Integer getIsManager() {
return isManager;
}
public void setIsManager(Integer isManager) {
this.isManager = isManager;
}

}

package com.graduate.webapp.rds.entity;

import java.sql.Date;

public class MemoryProjectInvited {
// Database Column
	private Member member = new Member();
	private MemoryProject memoryProject = new MemoryProject();
	private Integer memoryProjectInvitedStatus;
	private String memoryProjectInvitedNo;
// Program control

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemoryProject getmemoryProject() {
		return memoryProject;
	}

	public void setmemoryProject(MemoryProject memoryProject) {
		this.memoryProject = memoryProject;
	}

	public Integer getMemoryProjectInvitedStatus() {
		return memoryProjectInvitedStatus;
	}

	public void setMemoryProjectInvitedStatus(Integer memoryProjectInvitedStatus) {
		this.memoryProjectInvitedStatus = memoryProjectInvitedStatus;
	}

	public String getMemoryProjectInvitedNo() {
		return memoryProjectInvitedNo;
	}

	public void setMemoryProjectInvitedNo(String memoryProjectInvitedNo) {
		this.memoryProjectInvitedNo = memoryProjectInvitedNo;
	}

}

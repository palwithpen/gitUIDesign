package com.palwithpen.restService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "usercreds")
public class UserModel {

	@Id
	@Column(name="user_id", length=15)
	private String userId;
	
	@Column(name="pass_key",length=200)
	private String passKey;
	
	@Column(name="created_on",length=25)
	private String creationDate;
	
	@Column(name="user_role", length=30)
	private String userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassKey() {
		return passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", passKey=" + passKey + ", creationDate=" + creationDate + ", userRole="
				+ userRole + "]";
	}
	
	
}

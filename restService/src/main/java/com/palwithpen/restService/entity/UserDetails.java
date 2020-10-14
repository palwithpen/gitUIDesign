package com.palwithpen.restService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.palwithpen.restService.bo.ContactDetailsBO;
import com.palwithpen.restService.bo.UserDetailsBO;

@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@Column(name = "user_id" , length = 25)
	private String userId;
	
	@Column(name = "user_details")
	@Lob
	private String userDetails;
	
	@Column(name = "contact_details")
	@Lob
	private String contactDetails;
	
	@Column(name = "creation_date" ,length = 25)
	private String creationDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(String userDetails) {
		this.userDetails = userDetails;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	
}

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
	
	@Lob
	@Column(name = "user_details")
	private UserDetailsBO userDetails;
	
	@Lob
	@Column(name = "contact_details")
	private ContactDetailsBO contactDetails;
	
	@Column(name = "creation_date" ,length = 25)
	private String creationDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserDetailsBO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsBO userDetails) {
		this.userDetails = userDetails;
	}

	public ContactDetailsBO getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetailsBO contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	
}

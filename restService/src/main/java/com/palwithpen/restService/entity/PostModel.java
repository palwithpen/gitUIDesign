package com.palwithpen.restService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post_dump")
public class PostModel {

	@Column(name ="post_id", length =10)
	private String postId;
	
	@Column(name="created_by", length=100)
	private String creater;
	
	@Column(name = "creation_date", length=25)
	private String createdOn;
	
	@Column(name = "post_header" , length = 250)
	private String postHeader;
	
	@Column(name = "post_tag", length = 50)
	private String postTag;
	
	@Column(name = "post_body")
	private String postBody;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(String postHeader) {
		this.postHeader = postHeader;
	}

	public String getPostTag() {
		return postTag;
	}

	public void setPostTag(String postTag) {
		this.postTag = postTag;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	@Override
	public String toString() {
		return "PostModel [postId=" + postId + ", creater=" + creater + ", createdOn=" + createdOn + ", postHeader="
				+ postHeader + ", postTag=" + postTag + ", postBody=" + postBody + "]";
	}
	
	
}

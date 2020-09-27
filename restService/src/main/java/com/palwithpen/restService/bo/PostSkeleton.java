package com.palwithpen.restService.bo;

public class PostSkeleton {

	private String postTags;
	private String postDate;
	private String postBody;
	private String posterId;
	public String getPostTags() {
		return postTags;
	}
	public void setPostTags(String postTags) {
		this.postTags = postTags;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPostBody() {
		return postBody;
	}
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}
	public String getPosterId() {
		return posterId;
	}
	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}
	@Override
	public String toString() {
		return "PostSkeleton [postTags=" + postTags + ", postDate=" + postDate + ", postBody=" + postBody
				+ ", posterId=" + posterId + "]";
	}
	
	
}

package com.hstore.vn.dto.request;

public class ContactRequest {
	
	public String userName;
	
	public String phoneNum;
	
	public String contactContent;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getContactContent() {
		return contactContent;
	}

	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}
	
	
}

package com.hstore.vn.dto.response;

public class ContactResponse {
	public Long id;
	
	public String userName;
	
	public String phoneNum;
	
	public String contactContent;

	public ContactResponse(Long id, String userName, String phoneNum, String contactContent) {
		this.id = id;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.contactContent = contactContent;
	}
	
	
	
	
}

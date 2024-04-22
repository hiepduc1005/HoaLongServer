package com.hstore.vn.service;

import java.util.List;

import com.hstore.vn.entity.Contact;

public interface ContactService {
	
	Contact getContactById(Long id);
	
	List<Contact> getContactByUserPhoneNumber(String phoneNum);
	
	List<Contact> getAllContact();
	
	Contact createContact(Contact contact);
	
	Contact updateContact(Contact contact);
	
	void deleteContact(Long contactId);
}

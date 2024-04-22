package com.hstore.vn.service.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hstore.vn.dto.request.ContactRequest;
import com.hstore.vn.dto.response.ContactResponse;
import com.hstore.vn.entity.Contact;

@Service
public class ContactConvert {
	
	public ContactResponse contactConvertToContactResponse(Contact contact) { 
			return new	ContactResponse(
						contact.getId(),
						contact.getUserName(),
						contact.getPhoneNum(),
						contact.getContactContent());
	}
	
	
	public List<ContactResponse> contactsConvertToContactsResponse(List<Contact> contacts) { 
		List<ContactResponse> contactResponses = new ArrayList<ContactResponse>();
		
		for(Contact contact : contacts) {
			contactResponses.add(contactConvertToContactResponse(contact));
		}
		
		return contactResponses;
	}
	
	public Contact contactRequestConverToContact(ContactRequest contactRequest) {
		Contact contact = new Contact();
		contact.setUserName(contactRequest.getUserName());
		contact.setPhoneNum(contactRequest.getPhoneNum());
		contact.setContactContent(contactRequest.getContactContent());
		
		return contact;
	}

}

package com.hstore.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hstore.vn.dto.request.ContactByPhoneRequest;
import com.hstore.vn.dto.request.ContactRequest;
import com.hstore.vn.dto.response.ContactResponse;
import com.hstore.vn.entity.Contact;
import com.hstore.vn.service.ContactService;
import com.hstore.vn.service.convert.ContactConvert;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
	
	@Autowired
	public ContactService contactService;
	
	@Autowired
	public ContactConvert contactConvert;
	
	@GetMapping("/all")
	public ResponseEntity<List<ContactResponse>> getAllContact(){
		List<Contact> contacts = contactService.getAllContact();
		
		List<ContactResponse> contactResponses = contactConvert.contactsConvertToContactsResponse(contacts);
		
		return new ResponseEntity<List<ContactResponse>>(contactResponses,HttpStatus.OK);
	}
	
	@GetMapping("/all/phone-num")
	public ResponseEntity<List<ContactResponse>> getAllContactByPhoneNum(@RequestBody ContactByPhoneRequest phoneRequest){
		List<Contact> contacts = contactService.getContactByUserPhoneNumber(phoneRequest.getPhoneNumber());
		
		List<ContactResponse> contactResponses = contactConvert.contactsConvertToContactsResponse(contacts);
		
		return new ResponseEntity<List<ContactResponse>>(contactResponses,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ContactResponse> postContact(@RequestBody ContactRequest contactRequest){
		Contact contact = contactService.createContact(contactConvert.contactRequestConverToContact(contactRequest));
		
		ContactResponse contactResponse = contactConvert.contactConvertToContactResponse(contact);
		
		return new ResponseEntity<ContactResponse>(contactResponse, HttpStatus.OK);
	}
 }

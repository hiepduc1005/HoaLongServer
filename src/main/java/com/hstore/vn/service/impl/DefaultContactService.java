package com.hstore.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.hstore.vn.entity.Contact;
import com.hstore.vn.exception.ContactException;
import com.hstore.vn.repository.ContactRepository;
import com.hstore.vn.service.ContactService;

@Service
public class DefaultContactService implements ContactService{
	
	@Autowired
	public ContactRepository contactRepository;

	@Override
	public Contact getContactById(Long id) {
		
		if(id == null || id <= 0 ) {
			throw new IllegalArgumentException("Contact id must not be null!");
		}
		
		return contactRepository.findById(id).orElse(null);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Contact> getContactByUserPhoneNumber(String phoneNum) {
		
		if(phoneNum == null) {
			throw new IllegalArgumentException("Phone number must not be null");
		}
		
		return contactRepository.getContactsByUserPhoneNum(phoneNum);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Contact> getAllContact() {
		return contactRepository.findAll();
	}

	@Override
	public Contact createContact(Contact contact){
		if(contact == null) {
			throw new ContactException("Contact must not be null !");
		}
		return contactRepository.save(contact);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Contact updateContact(Contact contact) {
		if(contact == null) {
			throw new ContactException("Contact must not be null !");
		}
		
		return contactRepository.save(contact);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteContact(Long contactId) {
		if(contactId == null || contactId <= 0 ) {
			throw new IllegalArgumentException("Contact id must not be null!");
		}
		
		contactRepository.delete(getContactById(contactId));
	}

}

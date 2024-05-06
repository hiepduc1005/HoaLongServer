package com.hstore.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionGlobalHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<String> userEmailException(DataNotFoundException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> userEmailException(UsernameNotFoundException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDenieException(AccessDeniedException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	public ResponseEntity<String> handleAuthException(AuthenticationCredentialsNotFoundException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
    }
	    
    @ExceptionHandler(ContactException.class)
    public ResponseEntity<String> handleContactException(ContactException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleProductException(ProductException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PurchaseException.class)
    public ResponseEntity<String> handlePurchaseException(PurchaseException ex) {	 
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}

package com.creditcard.exception;

import java.util.HashMap;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserControllerExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = DuplicateUserException.class)
	public ResponseEntity<Object> userExists(DuplicateUserException duplicateUserException) {
		HashMap<String, String> response = new HashMap<>();
		response.put("Message", "UserName Already Exists .. Choose Different UserName");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(value = InvalidLoginCredentialException.class)
	public ResponseEntity<Object> userLoginException(InvalidLoginCredentialException e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message", "Invalid Credentials");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = InvalidUserIdException.class)
	public ResponseEntity<Object> invalidUid(InvalidUserIdException e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message !", "Invalid User ID");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = InvalidUserNameException.class)
	public ResponseEntity<Object> invalidUid(InvalidUserNameException e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message", "Invalid User Name");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}	
}

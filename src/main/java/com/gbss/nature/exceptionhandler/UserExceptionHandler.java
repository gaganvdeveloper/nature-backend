package com.gbss.nature.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gbss.nature.exceptionclasses.InvalidUserIdException;
import com.gbss.nature.exceptionclasses.NoUserFoundException;
import com.gbss.nature.responsestructure.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ResponseStructure<String>> invalidUserIdExceptionHandler(InvalidUserIdException invalidUserIdException){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Unable To Find User");
		structure.setBody(invalidUserIdException.getMessage());
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noUserFoundExceptionHandler(NoUserFoundException noUserFoundException){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("User not Present in Database...");
		structure.setBody(noUserFoundException.getMessage());
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
}

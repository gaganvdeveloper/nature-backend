package com.gbss.nature.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gbss.nature.exceptionclasses.InvalidAddressIdException;
import com.gbss.nature.responsestructure.ResponseStructure;

@RestControllerAdvice
public class AddressExceptionHandler {
	@ExceptionHandler(InvalidAddressIdException.class)
	public ResponseEntity<ResponseStructure<String>> invalidAddressIdException(InvalidAddressIdException invalidAddressIdException){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Inavlid Address Exception");
		structure.setBody(invalidAddressIdException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	
	
	
}

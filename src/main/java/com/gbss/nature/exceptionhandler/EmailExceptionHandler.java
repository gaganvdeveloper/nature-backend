package com.gbss.nature.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gbss.nature.exceptionclasses.InvalidEmailException;
import com.gbss.nature.responsestructure.ResponseStructure;

@RestControllerAdvice
public class EmailExceptionHandler {

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ResponseStructure<String>> handleInVaildEmailException(
			InvalidEmailException invalidEmailException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Invalid Email Entered...");
		structure.setBody(invalidEmailException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}

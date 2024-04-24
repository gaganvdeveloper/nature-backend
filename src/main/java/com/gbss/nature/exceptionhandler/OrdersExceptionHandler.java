package com.gbss.nature.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gbss.nature.exceptionclasses.InvalidOrderIdException;
import com.gbss.nature.responsestructure.ResponseStructure;

@RestControllerAdvice
public class OrdersExceptionHandler {

	@ExceptionHandler(InvalidOrderIdException.class)
	public ResponseEntity<ResponseStructure<String>> invalidOrdersIdExceptionHandler(
			InvalidOrderIdException invalidOrderIdException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Invalid Order Id Please Check It");
		structure.setBody(invalidOrderIdException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}

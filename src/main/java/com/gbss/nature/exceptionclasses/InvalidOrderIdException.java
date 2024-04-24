package com.gbss.nature.exceptionclasses;

public class InvalidOrderIdException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid Email Id Found Please Check it...";
	}
}

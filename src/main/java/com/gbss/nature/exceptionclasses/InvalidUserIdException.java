package com.gbss.nature.exceptionclasses;

public class InvalidUserIdException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid User Id";
	}
}

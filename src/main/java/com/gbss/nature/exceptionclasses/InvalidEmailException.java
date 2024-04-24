package com.gbss.nature.exceptionclasses;

public class InvalidEmailException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Email Id Please Check It...";
	}
}

package com.gbss.nature.exceptionclasses;

public class InvalidAddressIdException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Address Id";
	}
}

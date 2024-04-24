package com.gbss.nature.exceptionclasses;

public class NoUserFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "No User Present in Database";
	}
}

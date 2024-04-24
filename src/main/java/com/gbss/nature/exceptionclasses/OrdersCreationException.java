package com.gbss.nature.exceptionclasses;

public class OrdersCreationException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Unable To Create Order Please Try After Some Time...";
	}
}

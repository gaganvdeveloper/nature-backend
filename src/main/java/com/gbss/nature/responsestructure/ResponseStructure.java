package com.gbss.nature.responsestructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private int code;
	private String message;
	private T body;
}

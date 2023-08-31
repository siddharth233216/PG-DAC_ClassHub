package com.classhub.custom_exceptions;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	public CustomException(String mesg) {
		super(mesg);
	}

}

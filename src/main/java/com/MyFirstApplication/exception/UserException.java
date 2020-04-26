package com.MyFirstApplication.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	int errorCode;

	public UserException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}
}

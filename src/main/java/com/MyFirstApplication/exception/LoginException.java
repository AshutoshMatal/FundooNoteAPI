package com.MyFirstApplication.exception;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	int errorCode;

	public LoginException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}
}

package com.MyFirstApplication.exception;

	public class EmailNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 3691397454430391187L;

		int errorCode;

		public EmailNotFoundException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;

		}

}

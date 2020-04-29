package com.MyFirstApplication.exception;

	public class InvalidTokenException extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public InvalidTokenException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}
}

package com.MyFirstApplication.exception;

	public class ErrorException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		int errorCode;

		public ErrorException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}

}

package com.MyFirstApplication.exception;

	public class ResetPasswordException extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public ResetPasswordException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}


}

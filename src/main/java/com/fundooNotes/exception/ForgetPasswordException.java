package com.fundooNotes.exception;

	public class ForgetPasswordException extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public ForgetPasswordException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}

}

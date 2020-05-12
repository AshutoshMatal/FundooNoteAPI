package com.fundooNotes.exception;

	public class ValidateException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		int errorCode;

		public ValidateException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}

}

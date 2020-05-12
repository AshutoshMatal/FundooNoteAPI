package com.fundooNotes.exception;

	public class InvalidPassword extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public InvalidPassword(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}

}

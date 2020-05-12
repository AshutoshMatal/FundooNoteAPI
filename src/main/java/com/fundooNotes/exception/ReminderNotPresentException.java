package com.fundooNotes.exception;
	public class ReminderNotPresentException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		int errorCode;

		public ReminderNotPresentException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}


}

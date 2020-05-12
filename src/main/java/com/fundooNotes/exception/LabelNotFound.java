package com.fundooNotes.exception;

	public class  LabelNotFound extends RuntimeException {

		private static final long serialVersionUID = 1L;

		int errorCode;

		
		public  LabelNotFound(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}


}

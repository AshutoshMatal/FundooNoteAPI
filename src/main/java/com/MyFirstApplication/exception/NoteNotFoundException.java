package com.MyFirstApplication.exception;
	public class NoteNotFoundException  extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public NoteNotFoundException (int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}
}

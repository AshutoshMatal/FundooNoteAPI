package com.MyFirstApplication.exception;

	public class NoteNotExistInTrash extends RuntimeException {

		private static final long serialVersionUID = 632734427788097094L;

		int errorCode;

		
		public NoteNotExistInTrash(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}

}

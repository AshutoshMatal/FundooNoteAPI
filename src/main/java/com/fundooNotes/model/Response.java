package com.fundooNotes.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Response {
	private int statuscode;
	private String message;
	private Object data;

	public Response() {
		super();
	}
	@Autowired
	public Response(int statuscode, String message, Object data) {
		this.statuscode = statuscode;
		this.message = message;
		this.data = data;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
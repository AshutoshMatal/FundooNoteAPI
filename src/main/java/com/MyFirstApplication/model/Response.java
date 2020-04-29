package com.MyFirstApplication.model;

public class Response {
	private String ResponseStatus;
	private int ResponseCode;
	private Object data;

	public Response() {
		super();
	}

	public Response(String responseStatus, int responseCode, Object data) {
		super();
		ResponseStatus = responseStatus;
		ResponseCode = responseCode;
		this.data = data;
	}

	public String getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		ResponseStatus = responseStatus;
	}
	public int getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(int responseCode) {
		ResponseCode = responseCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

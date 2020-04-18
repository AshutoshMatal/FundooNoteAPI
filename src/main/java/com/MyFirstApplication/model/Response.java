package com.MyFirstApplication.model;

public class Response {
	private String ResponseStatus;
	private int ResponseCode;

	public Response() {
		super();
	}
	public Response(String responseStatus, int responseCode) {
		super();
		ResponseStatus = responseStatus;
		ResponseCode = responseCode;
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

}

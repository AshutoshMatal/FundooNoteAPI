package com.MyFirstApplication.model;

public class Response 
{ 
	//VARIABLES
	private String ResponseStatus;
	private int ResponseCode;
	//CONSTRUCTOR
	public Response() {
		super();
	}
	public Response(String responseStatus, int responseCode) {
		super();
		ResponseStatus = responseStatus;
		ResponseCode = responseCode;
	}
	//GETTERS AND SETTERS
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

package com.MyFirstApplication.service;

import com.MyFirstApplication.model.Response;

public class ResponseImpl {

	public static Response getResponse(String StatusMessage,int StatusCode)
	{
		Response responseObj=new Response();
		responseObj.setResponseStatus(StatusMessage);
		responseObj.setResponseCode(StatusCode);
		return responseObj;	
	}
}

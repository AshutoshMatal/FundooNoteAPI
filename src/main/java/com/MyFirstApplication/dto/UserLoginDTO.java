package com.MyFirstApplication.dto;

public class UserLoginDTO 
{
	//VARIABLES
	String UserName;
	String Password;
	//CONSTRUCTOR
	public UserLoginDTO(String userName, String password)
	{
		super();
		UserName = userName;
		Password = password;
	}
	//GETTERS AND SETTERS
	public String getUserName() 
	{
		return UserName;
	}
	public void setUserName(String userName)
	{
		UserName = userName;
	}
	public String getPassword() 
	{
		return Password;
	}
	public void setPassword(String password)
	{
		Password = password;
	}
}




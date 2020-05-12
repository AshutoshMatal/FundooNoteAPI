package com.fundooNotes.dto;

public class UserLoginDTO 
{
	//VARIABLES
	String emailId;
	String password;
	//CONSTRUCTOR
	public UserLoginDTO(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}
	//GETTERS AND SETTERS
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}




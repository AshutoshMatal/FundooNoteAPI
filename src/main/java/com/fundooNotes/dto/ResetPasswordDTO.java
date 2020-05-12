package com.fundooNotes.dto;
public class ResetPasswordDTO {
	//VARIABLES 
	private String password;
	 private String confirmPassword;
	//cONSTRUCTOR  
	public ResetPasswordDTO() {
		super();
	}
	public ResetPasswordDTO(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	//GETTERS AND SETTERS
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

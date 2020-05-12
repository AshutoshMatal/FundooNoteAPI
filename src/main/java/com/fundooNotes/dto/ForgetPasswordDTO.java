package com.fundooNotes.dto;
public class ForgetPasswordDTO 
{
	private String emailId;
	private long mobileNo;
	public ForgetPasswordDTO(String emailId, long mobileNo) 
	{
		super();
		this.emailId = emailId;
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
}
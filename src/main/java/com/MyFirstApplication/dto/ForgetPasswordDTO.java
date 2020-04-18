package com.MyFirstApplication.dto;

public class ForgetPasswordDTO 
{
	//VARIABLES
	long mobileNo;
	String nickName;
	//CONSTRUCTOR
	public ForgetPasswordDTO(int mobileNo, String nickName) {
		super();
		this.mobileNo = mobileNo;
		this.nickName = nickName;
	}
	//GETTERS AND SETTERS
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}

package com.MyFirstApplication.dto;

public class ForgetPasswordDTO {
	long mobileNo;
	String nickName;
	
	public ForgetPasswordDTO(int mobileNo, String nickName) {
		super();
		this.mobileNo = mobileNo;
		this.nickName = nickName;
	}
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

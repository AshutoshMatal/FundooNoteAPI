package com.MyFirstApplication.dto;

public class LabelDTO {
	private String labelName;
	private long userId;
	public LabelDTO() {
		super();
	}
	public LabelDTO(String labelName, long userId) {
		super();
		this.labelName = labelName;
		this.userId = userId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

}

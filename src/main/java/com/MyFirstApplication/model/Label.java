package com.MyFirstApplication.model;

public class Label {
	private long id;
	 private String labelName;
	 private  long userId;
	public Label(String labelName, long userId) {
		super();
		this.labelName = labelName;
		this.userId = userId;
	}
	public Label() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

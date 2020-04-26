package com.MyFirstApplication.dto;

public class NoteDTO {
	private String title;
	private String content;
	
	
	public NoteDTO() {
		super();
	}
	public NoteDTO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}

package com.MyFirstApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;
	private boolean isTrash;
	private boolean isPined;
	private boolean isArchieve;

	public Note() {
		super();
	}

	public Note(String title, String content, boolean isTrash, boolean isPined, boolean isArchieve) {
		super();
		this.title = title;
		this.content = content;
		this.isTrash = isTrash;
		this.isPined = isPined;
		this.isArchieve = isArchieve;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public boolean isPined() {
		return isPined;
	}
	public void setPined(boolean isPined) {
		this.isPined = isPined;
	}
	public boolean isArchieve() {
		return isArchieve;
	}
	public void setArchieve(boolean isArchieve) {
		this.isArchieve = isArchieve;
	}

}

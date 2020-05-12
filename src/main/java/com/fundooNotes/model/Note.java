package com.fundooNotes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notes")
@JsonIgnoreProperties({ "user", "label" })
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	private boolean isTrash;
	private boolean isPined;
	private boolean isArchieve;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToMany
	private List<Label> labelList = new ArrayList<Label>();
	@OneToOne(mappedBy = "note")
	private Reminder reminder;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

}

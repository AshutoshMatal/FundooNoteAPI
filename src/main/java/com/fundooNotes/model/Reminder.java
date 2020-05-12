package com.fundooNotes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "reminder")
@JsonIgnoreProperties({ "note" })
public class Reminder {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
private Date dateAndTime;


@OneToOne
@JoinColumn(name = "note_id", nullable = false)
private Note note;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Date getDateAndTime() {
	return dateAndTime;
}

public void setDateAndTime(Date dateAndTime) {
	this.dateAndTime = dateAndTime;
}


public Note getNote() {
	return note;
}

public void setNote(Note note) {
	this.note = note;
}




}

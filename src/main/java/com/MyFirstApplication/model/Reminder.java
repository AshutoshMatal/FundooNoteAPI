package com.MyFirstApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "reminder")
@JsonIgnoreProperties({ "notes" })
public class Reminder {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
private Date dateAndTime;
private boolean repeatDaily;
private boolean repeatWeekly;
private boolean repeatMonthly;
private boolean Yearly;
private boolean doNotRepeat=true;

@OneToMany
@JoinColumn(name = "id", nullable = false)
private Note note;

public long getId() {
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

public boolean isRepeatDaily() {
	return repeatDaily;
}

public void setRepeatDaily(boolean repeatDaily) {
	this.repeatDaily = repeatDaily;
}

public boolean isRepeatWeekly() {
	return repeatWeekly;
}

public void setRepeatWeekly(boolean repeatWeekly) {
	this.repeatWeekly = repeatWeekly;
}

public boolean isRepeatMonthly() {
	return repeatMonthly;
}

public void setRepeatMonthly(boolean repeatMonthly) {
	this.repeatMonthly = repeatMonthly;
}

public boolean isYearly() {
	return Yearly;
}

public void setYearly(boolean yearly) {
	Yearly = yearly;
}

public boolean isDoNotRepeat() {
	return doNotRepeat;
}

public void setDoNotRepeat(boolean doNotRepeat) {
	this.doNotRepeat = doNotRepeat;
}

public Note getNote() {
	return note;
}

public void setNote(Note note) {
	this.note = note;
}




}

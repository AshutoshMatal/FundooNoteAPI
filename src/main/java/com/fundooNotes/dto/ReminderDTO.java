package com.fundooNotes.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReminderDTO {
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date ;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

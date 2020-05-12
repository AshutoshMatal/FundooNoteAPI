package com.fundooNotes.service;


import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	public void sendEmail(SimpleMailMessage emailId);
	}


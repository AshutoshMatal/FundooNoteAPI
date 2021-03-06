package com.fundooNotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	 private JavaMailSender javaMailSender;

	    @Autowired
	    public EmailServiceImpl(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    @Async
	    public void sendEmail(SimpleMailMessage emailId) {
	        javaMailSender.send(emailId);
	    }
	}


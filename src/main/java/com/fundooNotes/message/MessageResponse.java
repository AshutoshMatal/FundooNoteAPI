package com.fundooNotes.message;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
@Component
public class MessageResponse {
		// SEND MAIL FOR VERIFICATION
		public SimpleMailMessage verifyMail(String receiverEmailId, String receiverName, String token) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(receiverEmailId);
			message.setFrom("ashutoshmatal33@gmail.com");
			message.setSubject("Complete Verification!!!! ");
			message.setText("Hi, " + receiverName + " ,\n" + " Your email is verify with "
					+ " Token :- http://localhost:8080/user/validateUser/" + token);
			return message;
		}

		// SEND MAIL FOR FORGOT PASSWORD
		public SimpleMailMessage passwordReset(String receiverEmailId, String receiverName, String token) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(receiverEmailId);
			message.setFrom("ashutoshmatal33@gmail.com");
			message.setSubject("Reset Password!!!! ");
			message.setText("Hi, " + receiverName + "  For reset Password, your token is " + "  Token :- " + token);
			return message;
		}

}

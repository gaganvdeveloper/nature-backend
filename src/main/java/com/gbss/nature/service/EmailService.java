package com.gbss.nature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	
	@Autowired
	JavaMailSender javaMailSender;

	public boolean sendSimpleEmail(String toEmail, String subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("gaganvdeveloper@gmail.com");
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		System.out.println("Sending Mail...");
		try {
		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail Sent Successfully...");
		return true;
		}catch(MailException e) {
			System.out.println("Incorrect Email id...");
			return false;
		}
	}
	
	
	
}

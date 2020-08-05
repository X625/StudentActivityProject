package edu.txstate.cs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	private static JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	
	public void sendEmail(String to, String subject, String text) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("d15223a36d-55323a@inbox.mailtrap.io");
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.send(msg);
		
	}
	

}

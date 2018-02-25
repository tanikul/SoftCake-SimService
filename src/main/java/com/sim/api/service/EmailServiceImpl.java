package com.sim.api.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sim.api.common.AppProperties;
import com.sim.api.model.User;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	@Qualifier("emailSender")
    public JavaMailSender emailSender;
	
	@Autowired
	public AppProperties app;
	
	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
	      
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	     
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	         
	    FileSystemResource file 
	      = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);
	 
	    emailSender.send(message);
		
	}

	@Override
	public void sendEmailRegisterUser(User user) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);	 
		helper.setFrom(new InternetAddress("noreply@xxx.com"));
	    helper.setTo(user.getEmail());
	    helper.setSubject("Â×¹ÂÑ¹µÑÇµ¹");
	    helper.setText("<a href=\"" + app.getWebUrl() + "/activateEmail?" + user.getActivateEmail() + "\">" + app.getWebUrl() + "/activateEmail?" + user.getActivateEmail() + "</a>", true);
	    emailSender.send(message);
	}

}

package com.sim.api.service;

import javax.mail.MessagingException;

import com.sim.api.model.User;

public interface EmailService {

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
	public void sendEmailRegisterUser(User user) throws MessagingException;
}

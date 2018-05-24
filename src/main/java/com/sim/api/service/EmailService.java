package com.sim.api.service;

import javax.mail.MessagingException;

import com.sim.api.model.Email;
import com.sim.api.model.User;

public interface EmailService {

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
	public void sendEmailRegisterUser(User user) throws MessagingException;
	public void sendEmailForgotPassword(User user) throws MessagingException;
	public Email getEmailById(int id);
	public void updateEmail(Email email);
	void sendEmailSubmitBooking(String bookingId) throws Exception;
}

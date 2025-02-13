package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMessageValidate(String email, String code) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject(code + " | Account Verification");
			helper.setText("<h1>Thank you for registering with EcoLink</h1>" +
					"<p>Your verification code is: <strong>" + code + "</strong></p>", true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			System.err.println("Error to send email: " + e.getMessage());
		}
	}

	public void sendNewCode(String email, String code) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("Account Verification");
			helper.setText("<p>Your new verification code is: <strong>" + code + "</strong></p>", true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			System.err.println("Error to send email: " + e.getMessage());
		}
	}

	public void accountVerified(String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("Account Verified");
			helper.setText("<h1>Your account has been successfully verified</h1>", true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			System.err.println("Error to send email: " + e.getMessage());
		}
	}

	public void sendPaymentCancelled(String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("Payment Cancelled");
			helper.setText("<h1>Payment Cancelled</h1>" +
					"<p>Your payment has been cancelled.</p>", true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			System.err.println("Error to send email: " + e.getMessage());
		}
	}

	public void sendPaymentSuccess(String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("Payment Successful");
			helper.setText("<h1>Payment Successful</h1>" +
					"<p>Your payment has been successfully processed. Thank you for your purchase!</p>", true);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			System.err.println("Error to send email: " + e.getMessage());
		}
	}
}

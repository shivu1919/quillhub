package com.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public int sendOtp(String email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Email OTP verification");
		Random r = new Random();
		int otp = 100000 + r.nextInt(899999);
		msg.setText("The OTP for email verification is :" + otp);
		javaMailSender.send(msg);
		return otp;
	}
}

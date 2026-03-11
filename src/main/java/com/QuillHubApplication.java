package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.service.OtpService;

@SpringBootApplication
public class QuillHubApplication {

	public static void main(String[] args) {
		ApplicationContext container = SpringApplication.run(QuillHubApplication.class, args);

	
	}

}

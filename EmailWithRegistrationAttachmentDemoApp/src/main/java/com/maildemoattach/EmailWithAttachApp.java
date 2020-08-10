package com.maildemoattach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailWithAttachApp {
	public static void main(String[] args) {
		SpringApplication.run(EmailWithAttachApp.class, args);
		System.out.println("Started");
	}
}

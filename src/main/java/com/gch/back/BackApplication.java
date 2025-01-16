package com.gch.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
		logger.info("GCH Back Application Started");
	}

}

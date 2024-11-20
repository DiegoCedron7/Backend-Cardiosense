package com.cardiosense.cardiosense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CardiosenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardiosenseApplication.class, args);
	}

}

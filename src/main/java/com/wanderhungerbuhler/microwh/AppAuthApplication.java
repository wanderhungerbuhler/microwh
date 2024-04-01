package com.wanderhungerbuhler.microwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wanderhungerbuhler.microwh")
public class AppAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAuthApplication.class, args);
	}

}

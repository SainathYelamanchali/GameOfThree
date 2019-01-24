package com.takeaway.gameofthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the Spring Boot Application
 */
@SpringBootApplication(scanBasePackages={"com.takeaway.gameofthree"})
public class GameOfThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfThreeApplication.class, args);
	}

}


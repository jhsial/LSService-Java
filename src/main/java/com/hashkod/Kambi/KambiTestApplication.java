package com.hashkod.Kambi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the application.
 * This application uses graceful shutdown using the configuration
 * from application.properties.
 */
@SpringBootApplication
public class KambiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KambiTestApplication.class, args);
	}

}

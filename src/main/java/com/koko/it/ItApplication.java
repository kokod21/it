package com.koko.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ItApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItApplication.class, args);
	}
}

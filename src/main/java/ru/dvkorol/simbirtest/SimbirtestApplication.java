package ru.dvkorol.simbirtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SimbirtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimbirtestApplication.class, args);
	}

}

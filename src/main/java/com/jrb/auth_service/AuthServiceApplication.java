package com.jrb.auth_service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.User;

@SpringBootApplication
public class AuthServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(AuthServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			User usuario1 = User.builder()
					.firstName("Roman")
					.lastname("Juan")
					.email("roman@gmail.com")
					.password("1234")
					.build();
			repository.save(usuario1);
			logger.info("User load");

			logger.info("User found with findAll()");
			logger.info("--------------");
			repository.findAll().forEach(user -> logger.info(user.toString()));
		};
	}
}

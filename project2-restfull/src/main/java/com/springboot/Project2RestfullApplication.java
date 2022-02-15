package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.springboot.*")
@EntityScan(basePackages = "com.springboot.*")
@EnableJpaRepositories("com.springboot.*")
public class Project2RestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2RestfullApplication.class, args);
	}

}

package com.br.barberq.barberq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.br.barberq.barberq")
@ComponentScan(basePackages = "com.br.barberq.barberq.service")
@EntityScan(basePackages = "com.br.barberq.barberq.model")
@EnableJpaRepositories(basePackages = "com.br.barberq.barberq.repository")
public class BarberqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberqApplication.class, args);
	}

}

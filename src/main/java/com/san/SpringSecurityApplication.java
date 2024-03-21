package com.san;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Data api documentation", version = "3.0.1", description = "Data api documentation description",
termsOfService = "T&C",contact = @Contact(name = "global logic", email = "globallogic@gmail.com")),
servers = {@Server(description = "dev", url = "http://localhost:8080"), @Server(description = "test", url = "http://localhost:8080")})
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}

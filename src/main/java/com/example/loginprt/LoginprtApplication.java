package com.example.loginprt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LoginprtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginprtApplication.class, args);
	}

}

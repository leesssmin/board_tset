package com.sm.mustache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sm.mustache")
public class MustacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(MustacheApplication.class, args);
	}

}

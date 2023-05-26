package com.marvel;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MarvelApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarvelApplication.class, args);
	}
}

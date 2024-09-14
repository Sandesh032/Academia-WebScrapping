package com.testselenium;

import org.springframework.boot.SpringApplication;

public class TestSeleniumtestApplication {

	public static void main(String[] args) {
		SpringApplication.from(SeleniumtestApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

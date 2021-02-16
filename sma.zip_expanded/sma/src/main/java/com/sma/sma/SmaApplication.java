package com.sma.sma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SmaApplication {
    @RequestMapping("/")
    String home() {
        return "Hello World - v3!";
    }
	public static void main(String[] args) {
		SpringApplication.run(SmaApplication.class, args);
	}

}

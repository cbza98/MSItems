package com.bankntt.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemNttApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemNttApplication.class, args);
	}

}
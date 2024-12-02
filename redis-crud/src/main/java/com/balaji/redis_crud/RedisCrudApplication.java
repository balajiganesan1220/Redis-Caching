package com.balaji.redis_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCrudApplication.class, args);
	}

}

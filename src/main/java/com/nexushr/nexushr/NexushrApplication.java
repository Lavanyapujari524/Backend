package com.nexushr.nexushr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class NexushrApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexushrApplication.class, args);
	}

}
   
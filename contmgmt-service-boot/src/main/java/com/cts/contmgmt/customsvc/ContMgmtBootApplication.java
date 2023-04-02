package com.cts.contmgmt.customsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = { "com.cts.contmgmt.api" })
@ComponentScan(basePackages = {"com.cts.*"})
public class ContMgmtBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContMgmtBootApplication.class, args);
	}

}

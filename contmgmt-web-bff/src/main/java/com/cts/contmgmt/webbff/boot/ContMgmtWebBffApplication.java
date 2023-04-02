package com.cts.contmgmt.webbff.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = { "com.cts.contmgmt.api" })
@ComponentScan(basePackages = { "com.cts.*" })
public class ContMgmtWebBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContMgmtWebBffApplication.class, args);
	}

}

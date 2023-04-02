package com.cts.contmgmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {
		"classpath:/${spring.profiles.active}/contmgmt-application.properties" })
@PropertySource(ignoreResourceNotFound = true, value = { "classpath:/contmgmt-application.properties" })
public class PropertiesConfig {

}

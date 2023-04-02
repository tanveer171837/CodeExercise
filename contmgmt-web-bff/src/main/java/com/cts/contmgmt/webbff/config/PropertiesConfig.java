package com.cts.contmgmt.webbff.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {
		"classpath:/${spring.profiles.active}/contmgmt-webbff-application.properties" })
public class PropertiesConfig {

}

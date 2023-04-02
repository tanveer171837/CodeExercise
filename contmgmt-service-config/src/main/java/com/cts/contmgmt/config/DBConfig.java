package com.cts.contmgmt.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "contmgmt_em", transactionManagerRef = "contmgmt_txnMgr", basePackages = "com.cts.contmgmt.customsvc.persistence.repository")
@PropertySource(ignoreResourceNotFound = true, value = {
		"classpath:/${spring.profiles.active}/contmgmt-application.properties" })
@PropertySource(ignoreResourceNotFound = true, value = { "classpath:/contmgmt-application.properties" })
public class DBConfig {

	@Bean(name = "contmgmt_ds")
	@ConfigurationProperties(prefix = "contmgmt.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("contmgmt_em")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("contmgmt_ds") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.cts.contmgmt.customsvc.persistence")
				.persistenceUnit("contmgmt_punit").build();
	}

	@Bean(name = "contmgmt_txnMgr")
	public PlatformTransactionManager transactionManager(
			@Qualifier("contmgmt_em") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}

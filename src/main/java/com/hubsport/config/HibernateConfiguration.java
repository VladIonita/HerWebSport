package com.hubsport.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.hubsport.config" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	// Ultimul save

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.hubsport.domain" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(System.getenv("CLEARDB_DATABASE_DRIVER"));
		dataSource.setUrl(System.getenv("CLEARDB_DATABASE_URL"));
		dataSource.setUsername(System.getenv("CLEARDB_DATABASE_USERNAME"));
		dataSource.setPassword(System.getenv("CLEARDB_DATABASE_PASSWORD"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("javax.persistence.validation.group.pre-persist",
				environment.getRequiredProperty("javax.persistence.validation.group.pre-persist"));
		properties.put("javax.persistence.validation.group.pre-update",
				environment.getRequiredProperty("javax.persistence.validation.group.pre-update"));
		properties.put("javax.persistence.validation.group.pre-remove",
				environment.getRequiredProperty("javax.persistence.validation.group.pre-remove"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}

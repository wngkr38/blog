package com.estsoft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@PropertySource("classpath:/application.properties")
@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		String[] beanList = context.getBeanDefinitionNames();

		Arrays.stream(beanList).forEach(System.out::println);
		SpringApplication.run(DemoApplication.class, args);
	}

}

package com.innova.creditscoreservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@EnableMongoRepositories
@Log4j2
public class CreditScoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditScoreServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplateBuild(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
}

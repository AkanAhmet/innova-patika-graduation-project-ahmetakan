package com.innova.loanapplicationservice.bean;


import com.innova.loanapplicationservice.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorAware")
public class AuditorAwareBean {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }
}
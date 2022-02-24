package com.example.springpracticecalendar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.example.springpracticecalendar.core"})
@EnableJpaRepositories(basePackages = {"com.example.springpracticecalendar.core"})
@SpringBootApplication(scanBasePackages = "com.example.springpracticecalendar")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

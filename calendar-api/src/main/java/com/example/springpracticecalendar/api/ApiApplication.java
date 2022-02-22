package com.example.springpracticecalendar.api;

import com.example.springpracticecalendar.core.SimpleEntity;
import com.example.springpracticecalendar.core.SimpleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EntityScan("com.example.springpracticecalendar.core")
@EnableJpaRepositories("com.example.springpracticecalendar.core")
@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class ApiApplication {
    private final SimpleEntityRepository simpleEntityRepository;

    @GetMapping
    public List<SimpleEntity> findAll(){
        return simpleEntityRepository.findAll();
    }

    @PostMapping("/save")
    public SimpleEntity saveOne(){
        return simpleEntityRepository.save(SimpleEntity.builder()
                .name("hello")
                .build());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

package com.dislike.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DislikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DislikeApplication.class, args);
    }

}

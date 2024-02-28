package com.example.mission3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mission3Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission3Application.class, args);
    }

}

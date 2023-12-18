package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
@EnableScheduling
public class SifatliTalimBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SifatliTalimBotApplication.class, args);
    }

}

package com.example.formconstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
// Явно указываем Spring, где искать репозитории для каждой базы
@EnableJpaRepositories(basePackages = "com.example.formconstructor.repository.jpa")
@EnableMongoRepositories(basePackages = "com.example.formconstructor.repository.mongodb")
public class FormConstructorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormConstructorApplication.class, args);
        System.out.println("🚀 Микросервис конструктора ТЗ запущен и готов к работе!");
    }
}

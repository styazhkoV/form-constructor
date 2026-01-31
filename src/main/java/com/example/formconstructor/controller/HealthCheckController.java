package com.example.formconstructor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String index() {
        return "Welcome to Form Constructor! Use /hello to check health.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Form Constructor! Server is running.";
    }
}

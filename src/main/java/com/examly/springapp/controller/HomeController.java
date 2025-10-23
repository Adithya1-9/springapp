package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(originPatterns = "*")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<h1>Backend is Running!</h1><p>Spring Boot application is successfully running on port 8080</p>";
    }
    
    @GetMapping("/test")
    public String test() {
        return "Test endpoint working!";
    }
}
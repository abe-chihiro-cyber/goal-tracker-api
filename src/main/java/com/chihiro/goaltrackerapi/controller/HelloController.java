package com.chihiro.goaltrackerapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihiro.goaltrackerapi.User;

import jakarta.validation.Valid;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "GoodBye Spring Boot";
    }

    @GetMapping("/user")
    public User user() {
        return new User("ちひろ", 30);
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "こんにちわ" + name;
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user) {
        return user;
    }
}

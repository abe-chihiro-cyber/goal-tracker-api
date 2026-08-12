package com.chihiro.goaltrackerapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihiro.goaltrackerapi.dto.request.CreateUserRequest;
import com.chihiro.goaltrackerapi.dto.request.UpdateUserRequest;
import com.chihiro.goaltrackerapi.dto.response.UserResponse;
import com.chihiro.goaltrackerapi.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return service.createUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PutMapping("/user/{id}")
    public UserResponse put(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return service.putUser(id, request);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}

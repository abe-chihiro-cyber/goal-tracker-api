package com.chihiro.goaltrackerapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chihiro.goaltrackerapi.dto.request.CreateUserRequest;
import com.chihiro.goaltrackerapi.dto.request.UpdateUserRequest;
import com.chihiro.goaltrackerapi.dto.response.UserResponse;
import com.chihiro.goaltrackerapi.entity.User;
import com.chihiro.goaltrackerapi.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAvatarPath(request.getAvatarPath());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setAvatarPath(savedUser.getAvatarPath());

        return response;
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            UserResponse response = new UserResponse();

            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setAvatarPath(user.getAvatarPath());

            responses.add(response);
        }

        return responses;

    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setAvatarPath(user.getAvatarPath());

        return response;
    }

    public UserResponse putUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAvatarPath(request.getAvatarPath());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setAvatarPath(savedUser.getAvatarPath());
        
        return response;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

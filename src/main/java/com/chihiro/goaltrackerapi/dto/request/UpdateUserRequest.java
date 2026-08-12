package com.chihiro.goaltrackerapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String avatarPath;

    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

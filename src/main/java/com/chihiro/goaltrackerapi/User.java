package com.chihiro.goaltrackerapi;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class User {

    @NotBlank
    private String name;

    @Min(0)
    @Max(120)
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age; 
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
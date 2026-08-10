package com.chihiro.goaltrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chihiro.goaltrackerapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
package com.chihiro.goaltrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chihiro.goaltrackerapi.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long>{

}

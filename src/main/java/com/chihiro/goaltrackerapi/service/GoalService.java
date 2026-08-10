package com.chihiro.goaltrackerapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chihiro.goaltrackerapi.dto.request.CreateGoalRequest;
import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.entity.Goal;
import com.chihiro.goaltrackerapi.repository.GoalRepository;

@Service
public class GoalService {

    private GoalRepository repository;

    public GoalService(GoalRepository repository) {
        this.repository = repository;
    }

    public GoalResponse createGoal(CreateGoalRequest request) {
        Goal goal = new Goal();

        goal.setTitle(request.getTitle());
        goal.setTarget(request.getTarget());
        goal.setCurrent(request.getCurrent());
        goal.setDeadline(request.getDeadline());

        Goal savedGoal = repository.save(goal);

        GoalResponse response = new GoalResponse();

        response.setId(savedGoal.getId());
        response.setTitle(savedGoal.getTitle());
        response.setTarget(savedGoal.getTarget());
        response.setCurrent(savedGoal.getCurrent());
        response.setDeadline(savedGoal.getDeadline());

        return response;
    }

    public List<Goal> getGoals() {
        return repository.findAll();
    }

    public Goal getGoal(Long id) {
    return repository.findById(id).orElseThrow();// ここのorElseThrowの設定を行って適当なエラーを出す
    }

    public Goal putGoal(Long id, Goal goal) {
        goal.setId(id);

        return repository.save(goal);
    }

    public void deleteGoal(Long id) {
        repository.deleteById(id);
    }
}

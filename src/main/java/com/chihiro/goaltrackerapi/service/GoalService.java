package com.chihiro.goaltrackerapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chihiro.goaltrackerapi.entity.User;
import com.chihiro.goaltrackerapi.repository.UserRepository;
import com.chihiro.goaltrackerapi.dto.request.CreateGoalRequest;
import com.chihiro.goaltrackerapi.dto.request.UpdateGoalRequest;
import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.entity.Goal;
import com.chihiro.goaltrackerapi.repository.GoalRepository;

@Service
public class GoalService {

    private GoalRepository repository;
    private UserRepository userRepository;

    public GoalService(GoalRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public GoalResponse createGoal(CreateGoalRequest request) {
        Goal goal = new Goal();

        goal.setTitle(request.getTitle());
        goal.setMemo(request.getMemo());
        goal.setTarget(request.getTarget());
        goal.setUnit(request.getUnit());
        goal.setCurrent(request.getCurrent());
        goal.setDeadline(request.getDeadline());
        
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        goal.setUser(user);

        Goal savedGoal = repository.save(goal);

        GoalResponse response = new GoalResponse();

        response.setId(savedGoal.getId());
        response.setTitle(savedGoal.getTitle());
        response.setMemo(savedGoal.getMemo());
        response.setTarget(savedGoal.getTarget());
        response.setUnit(savedGoal.getUnit());
        response.setCurrent(savedGoal.getCurrent());
        response.setDeadline(savedGoal.getDeadline());

        return response;
    }

    public List<GoalResponse> getGoals() {
        return repository.findAll()
                .stream()
                .map(goal -> {
                    GoalResponse response = new GoalResponse();

                    response.setId(goal.getId());
                    response.setTitle(goal.getTitle());
                    response.setMemo(goal.getMemo());
                    response.setTarget((goal.getTarget()));
                    response.setUnit(goal.getUnit());
                    response.setCurrent(goal.getCurrent());
                    response.setDeadline(goal.getDeadline());

                    return response;
                })
                .toList();
    }

    public GoalResponse getGoal(Long id) {
        Goal goal = repository
                        .findById(id)
                        .orElseThrow();// ここのorElseThrowの設定を行って適当なエラーを出す

        GoalResponse response = new GoalResponse();

        response.setId(goal.getId());
        response.setTitle(goal.getTitle());
        response.setMemo(goal.getMemo());
        response.setTarget(goal.getTarget());
        response.setUnit(goal.getUnit());
        response.setCurrent(goal.getCurrent());
        response.setDeadline(goal.getDeadline());

        return response;
    }

    // putのレスポンス
    public GoalResponse putGoal(Long id, UpdateGoalRequest goal) {
        Goal existingGoal = repository.findById(id)
                .orElseThrow();
        
        existingGoal.setTitle(goal.getTitle());
        existingGoal.setMemo(goal.getMemo());
        existingGoal.setTarget(goal.getTarget());
        existingGoal.setUnit(goal.getUnit());
        existingGoal.setCurrent(goal.getCurrent());
        existingGoal.setDeadline(goal.getDeadline());

        Goal savedGoal = repository.save(existingGoal);

        GoalResponse response = new GoalResponse();

        response.setId(savedGoal.getId());
        response.setTitle(savedGoal.getTitle());
        response.setMemo(savedGoal.getMemo());
        response.setTarget(savedGoal.getTarget());
        response.setUnit(savedGoal.getUnit());
        response.setCurrent(savedGoal.getCurrent());
        response.setDeadline(savedGoal.getDeadline());

        return response;
    }

    public void deleteGoal(Long id) {
        repository.deleteById(id);
    }
}

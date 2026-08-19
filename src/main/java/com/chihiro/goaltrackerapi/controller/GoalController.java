package com.chihiro.goaltrackerapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihiro.goaltrackerapi.dto.request.CreateGoalRequest;
import com.chihiro.goaltrackerapi.dto.request.UpdateGoalRequest;
import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.service.GoalService;

import jakarta.validation.Valid;

@RestController
public class GoalController {

    private GoalService service;

    public GoalController(GoalService service) {
        this.service = service;
    }

    // 作成
    @PostMapping("/goal")
    public GoalResponse create(@Valid @RequestBody CreateGoalRequest request) {
        return service.createGoal(request);
    }

    // 全件Get
    @GetMapping("/goal")
    public List<GoalResponse> getGoals() {
        return service.getGoals();
    }

    // 1件Get
    @GetMapping("/goal/{id}")
    public GoalResponse getGoal(@PathVariable("id") Long id) {
        return service.getGoal(id);
    }

    // 編集
    @PutMapping("/goal/{id}")
    public GoalResponse put(@PathVariable("id") Long id, @Valid @RequestBody UpdateGoalRequest goal) {
        return service.putGoal(id, goal);
    }

    // １件削除
    @DeleteMapping("/goal/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteGoal(id);
    }
}

package com.chihiro.goaltrackerapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.entity.Goal;
import com.chihiro.goaltrackerapi.repository.GoalRepository;
import com.chihiro.goaltrackerapi.repository.UserRepository;

public class GoalServiceTest {

    private GoalRepository repository;
    private UserRepository userRepository;

    @Test
    void getGoalsTest() {
        Goal goal1 = new Goal();
        goal1.setId(1L);
        goal1.setTitle("Java");
        goal1.setMemo("goal1のメモです");
        goal1.setTarget(100);
        goal1.setCurrent(30);
        goal1.setUnit("ページ");
        goal1.setDeadline(LocalDate.of(2026, 8, 31));

        Goal goal2 = new Goal();
        goal2.setId(2L);
        goal2.setTitle("筋トレ");
        goal2.setMemo("goal2のメモです");
        goal2.setTarget(50);
        goal2.setCurrent(20);
        goal2.setUnit("回");
        goal2.setDeadline(LocalDate.of(2026, 9, 30));

        Goal goal3 = new Goal();
        goal3.setId(3L);
        goal3.setTitle("読書");
        goal3.setMemo("goal3のメモです");
        goal3.setTarget(10);
        goal3.setCurrent(5);
        goal3.setUnit("冊");
        goal3.setDeadline(LocalDate.of(2026, 10, 31));

        List<Goal> goals = List.of(goal1, goal2, goal3);

        repository = mock(GoalRepository.class);

        when(repository.findAll()).thenReturn(goals);

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        List<GoalResponse> result = service.getGoals();

        assertEquals(3, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("Java", result.get(0).getTitle());
        assertEquals("goal1のメモです", result.get(0).getMemo());
        assertEquals(100, result.get(0).getTarget());
        assertEquals(30, result.get(0).getCurrent());
        assertEquals("ページ", result.get(0).getUnit());
        assertEquals(LocalDate.of(2026, 8, 31), result.get(0).getDeadline());
    }

    @Test
    void getGoalsEmptyTest() {
        repository = mock(GoalRepository.class);

        when(repository.findAll()).thenReturn(List.of());

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        List<GoalResponse> result = service.getGoals();

        assertEquals(0, result.size());
    }
}

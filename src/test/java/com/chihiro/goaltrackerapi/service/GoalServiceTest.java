package com.chihiro.goaltrackerapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.chihiro.goaltrackerapi.dto.request.CreateGoalRequest;
import com.chihiro.goaltrackerapi.dto.request.UpdateGoalRequest;
import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.entity.Goal;
import com.chihiro.goaltrackerapi.entity.User;
import com.chihiro.goaltrackerapi.exception.GoalNotFoundException;
import com.chihiro.goaltrackerapi.exception.UserNotFoundException;
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

    @Test
    void getGoalTest() {
        Goal goal = new Goal();
        goal.setId(1L);
        goal.setTitle("Java");
        goal.setMemo("goalのメモです");
        goal.setTarget(100);
        goal.setCurrent(30);
        goal.setUnit("ページ");
        goal.setDeadline(LocalDate.of(2026, 8, 31));

        repository = mock(GoalRepository.class);

        when(repository.findById(1L)).thenReturn(Optional.of(goal));

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        GoalResponse result = service.getGoal(1L);

        assertEquals(1L, result.getId());
        assertEquals("Java", result.getTitle());
        assertEquals("goalのメモです", result.getMemo());
        assertEquals(100, result.getTarget());
        assertEquals(30, result.getCurrent());
        assertEquals("ページ", result.getUnit());
        assertEquals(LocalDate.of(2026, 8, 31), result.getDeadline());
    }

    @Test
    void getGoalThrowExceptionTest() {
        repository = mock(GoalRepository.class);

        when(repository.findById(999L)).thenReturn(Optional.empty());

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        assertThrows(GoalNotFoundException.class, () -> service.getGoal(999L));
    }
    
    @Test
    void createGoalTest() {
        CreateGoalRequest request = new CreateGoalRequest();

        request.setTitle("Java");
        request.setMemo("goalのメモです");
        request.setTarget(100);
        request.setCurrent(30);
        request.setUnit("ページ");
        request.setDeadline(LocalDate.of(2026, 8, 31));
        request.setUserId(1L);

        repository = mock(GoalRepository.class);

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        User user = new User();

        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Goal goal = new Goal();
        goal.setId(1L);
        goal.setTitle("Java");
        goal.setMemo("goalのメモです");
        goal.setTarget(100);
        goal.setCurrent(30);
        goal.setUnit("ページ");
        goal.setDeadline(LocalDate.of(2026, 8, 31));

        when(repository.save(any(Goal.class))).thenReturn(goal);

        GoalResponse response = service.createGoal(request);
        
        assertEquals(1L, response.getId());
        assertEquals("Java", response.getTitle());
        assertEquals("goalのメモです", response.getMemo());
        assertEquals(100, response.getTarget());
        assertEquals(30, response.getCurrent());
        assertEquals("ページ", response.getUnit());
        assertEquals(LocalDate.of(2026, 8, 31), response.getDeadline());
        
    }

    @Test
    void createGoalThrowExceptionTest() {
        CreateGoalRequest request = new CreateGoalRequest();
        request.setTitle("Java");
        request.setMemo("goalのメモです");
        request.setTarget(100);
        request.setCurrent(30);
        request.setUnit("ページ");
        request.setDeadline(LocalDate.of(2026, 8, 31));
        request.setUserId(1L);

        userRepository = mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        repository = mock(GoalRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        assertThrows(UserNotFoundException.class, () -> service.createGoal(request));
    }

    @Test
    void putGoalTest() {
        UpdateGoalRequest request = new UpdateGoalRequest();
        request.setTitle("Java");
        request.setMemo("goalのメモです");
        request.setTarget(100);
        request.setCurrent(30);
        request.setUnit("ページ");
        request.setDeadline(LocalDate.of(2026, 8, 31));
        request.setUserId(1L);

        repository = mock(GoalRepository.class);

        Goal goal = new Goal();

        goal.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(goal));

        when(repository.save(any(Goal.class))).thenReturn(goal);

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        GoalResponse result = service.putGoal(1L, request);

        assertEquals(1L, result.getId());
        assertEquals("Java", result.getTitle());
        assertEquals("goalのメモです", result.getMemo());
        assertEquals(100, result.getTarget());
        assertEquals(30, result.getCurrent());
        assertEquals("ページ", result.getUnit());
        assertEquals(LocalDate.of(2026, 8, 31), result.getDeadline());
    }

    @Test
    void putGoalThrowExceptionTest() {
        UpdateGoalRequest request = new UpdateGoalRequest();
        request.setTitle("Java");
        request.setMemo("goalのメモです");
        request.setTarget(100);
        request.setCurrent(30);
        request.setUnit("ページ");
        request.setDeadline(LocalDate.of(2026, 8, 31));
        request.setUserId(1L);

        repository = mock(GoalRepository.class);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        assertThrows(GoalNotFoundException.class, () -> service.putGoal(1L, request));
    }

    @Test
    void deleteGoalTest() {
        // 1, 準備

        Goal goal = new Goal();
        goal.setId(1L);

        repository = mock(GoalRepository.class);

        when(repository.findById(1L)).thenReturn(Optional.of(goal));

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        // 2, 実行

        service.deleteGoal(1L);

        // 3, 確認

        verify(repository).delete(goal);
    }

    @Test
    void deleteGoalThrowExceptionTest() {
        repository = mock(GoalRepository.class);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        userRepository = mock(UserRepository.class);

        GoalService service = new GoalService(repository, userRepository);

        assertThrows(GoalNotFoundException.class, () -> service.deleteGoal(1L));
    }
}

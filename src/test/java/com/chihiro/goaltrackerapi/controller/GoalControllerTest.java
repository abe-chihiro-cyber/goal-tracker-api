package com.chihiro.goaltrackerapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.chihiro.goaltrackerapi.dto.request.CreateGoalRequest;
import com.chihiro.goaltrackerapi.dto.response.GoalResponse;
import com.chihiro.goaltrackerapi.service.GoalService;

public class GoalControllerTest {
    private MockMvc mockMvc;

    private GoalController controller;

    private GoalService service;

    @Test
    void createTest() throws Exception {
        service = mock(GoalService.class);

        controller = new GoalController(service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        GoalResponse response = new GoalResponse();
        response.setId(1L);
        response.setTitle("Javaの勉強");
        response.setMemo("Controllerのテスト");
        response.setTarget(100);
        response.setUnit("ページ");
        response.setCurrent(0);
        response.setDeadline(LocalDate.of(2026, 8, 31));

        when(service.createGoal(any(CreateGoalRequest.class))).thenReturn(response);

        mockMvc.perform(
            post("/goal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "userId": 1,
                        "title": "Javaの勉強",
                        "memo": "Controllerのテスト",
                        "target": 100,
                        "unit": "ページ",
                        "current": 0,
                        "deadline": "2026-08-31"
                    } 
                """)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.id").value(1))
         .andExpect(jsonPath("$.title").value("Javaの勉強"))
         .andExpect(jsonPath("$.memo").value("Controllerのテスト"))
         .andExpect(jsonPath("$.target").value(100))
         .andExpect(jsonPath("$.unit").value("ページ"))
         .andExpect(jsonPath("$.current").value(0))
         .andExpect(jsonPath("$.deadline").value("2026-08-31"));
    }

    @Test
    void getGoalTest() throws Exception{
        service = mock(GoalService.class);

        GoalController controller = new GoalController(service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        GoalResponse response = new GoalResponse();
        response.setId(1L);
        response.setTitle("Javaの勉強");
        response.setMemo("Controllerのテスト");
        response.setTarget(100);
        response.setUnit("ページ");
        response.setCurrent(0);
        response.setDeadline(LocalDate.of(2026, 8, 31));

        when(service.getGoal(1L)).thenReturn(response);

        mockMvc.perform(
            get("/goal/1")
        )
        .andExpect(status().isOk());
    }
}

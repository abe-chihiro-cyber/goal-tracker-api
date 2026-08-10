package com.chihiro.goaltrackerapi.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateGoalRequest {

    @NotBlank
    private String title;

    @Min(1)
    private int target;
    private int current;

    @NotNull
    private LocalDate deadline;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public int getTarget() {
        return target;
    }

    public int getCurrent() {
        return current;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public CreateGoalRequest() {
    }
}

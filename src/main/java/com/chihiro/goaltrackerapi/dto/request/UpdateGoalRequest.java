package com.chihiro.goaltrackerapi.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateGoalRequest {
    @NotBlank
    private String title;

    private String memo;

    @Min(1)
    private int target;

    @NotBlank
    private String unit;

    private int current;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private Long userId;

    public String getTitle() {
        return title;
    }

    public String getMemo() {
        return memo;
    }

    public int getTarget() {
        return target;
    }

    public String getUnit() {
        return unit;
    }

    public int getCurrent() {
        return current;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

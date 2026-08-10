package com.chihiro.goaltrackerapi.dto.response;

import java.time.LocalDate;

public class GoalResponse {

    private Long id;
    private String title;
    private String memo;
    private int target;
    private String unit;
    private int current;
    private LocalDate deadline;

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getId() {
        return id;
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

    public String getMemo() {
        return memo;
    }

    public String getUnit() {
        return unit;
    }

    public GoalResponse() {
    }
    
}

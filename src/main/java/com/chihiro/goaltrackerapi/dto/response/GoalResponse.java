package com.chihiro.goaltrackerapi.dto.response;

import java.time.LocalDate;

public class GoalResponse {

    private Long id;
    private String title;
    private int target;
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

    public GoalResponse() {
    }
    
}

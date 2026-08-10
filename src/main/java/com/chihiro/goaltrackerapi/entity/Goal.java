package com.chihiro.goaltrackerapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String memo;
    private int target;
    private String unit;
    private int current;
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Goal() {
    }

    public Goal(Long id, String title, String memo, int target, String unit, int current, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.target = target;
        this.unit = unit;
        this.current = current;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

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

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }
}

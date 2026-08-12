package com.chihiro.goaltrackerapi.exception;

public class GoalNotFoundException extends RuntimeException{

    public GoalNotFoundException(Long id) {
        super("Goal not found:" + id);
    }
}

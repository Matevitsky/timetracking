package com.matevitsky.entity;

public class ActivityRequest {

    private final Integer id;
    private final Integer userId;

    public ActivityRequest(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }


}

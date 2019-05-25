package com.matevitsky.entity;

public abstract class Request {

    private final Integer id;
    private final Integer userId;

    public Request(Integer id, Integer userId) {
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

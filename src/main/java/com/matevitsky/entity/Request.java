package com.matevitsky.entity;

public class Request {
    private final int userId;

    public Request(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}

package com.matevitsky.entity;

public class Request {
    private final int id;
    private final int userId;

    public Request(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }
    
    public int getUserId() {
        return userId;
    }
}

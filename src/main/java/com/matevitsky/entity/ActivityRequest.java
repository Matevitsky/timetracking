package com.matevitsky.entity;

public class ActivityRequest {
    private final int id;
    private final int userId;

    public ActivityRequest(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }
    
    public int getUserId() {
        return userId;
    }
}

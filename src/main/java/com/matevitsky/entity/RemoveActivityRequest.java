package com.matevitsky.entity;

public class RemoveActivityRequest extends Request {

    private final Integer activityId;

    public RemoveActivityRequest(Integer id, Integer userId, Integer activityId) {
        super(id, userId);
        this.activityId = activityId;
    }

    public Integer getActivityId() {
        return activityId;
    }
}

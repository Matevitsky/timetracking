package com.matevitsky.entity;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivityRequest)) {
            return false;
        }
        ActivityRequest that = (ActivityRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}

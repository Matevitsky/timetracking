package com.matevitsky.entity;


import java.util.ArrayList;
import java.util.List;

public class User extends BaseUser {

    private final List<Activity> activityList;

    public User(Integer id, String name, String email, String password, Role role) {
        super(id, name, email, password, role);
        this.activityList = new ArrayList<>();
    }

    public User(String name, String email, String password, Role role) {
        super(name, email, password, role);
        this.activityList = new ArrayList<>();
    }

    public User() {
        this.activityList = null;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    @Override
    public String toString() {
        return "User{" +
                "activityList=" + activityList +
                "} " + super.toString();
    }


}

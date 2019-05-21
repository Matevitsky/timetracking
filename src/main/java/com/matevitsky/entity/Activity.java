package com.matevitsky.entity;

import java.util.Objects;

public class Activity {
    private final String title;
    private final String content;
    private final Integer duration;
    private final Integer userId;
    private Integer activityId;


    public Activity(Integer activityId, String title, String content, Integer duration, Integer userId) {
        this.activityId = activityId;
        this.title = title;
        this.content = content;
        this.duration = duration;
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }


//TODO: creation constructors for duration and userId is required


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getDuration() {
        return duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) o;
        return Objects.equals(title, activity.title) &&
                Objects.equals(content, activity.content) &&
                Objects.equals(duration, activity.duration) &&
                Objects.equals(userId, activity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, duration, userId);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", userId=" + userId +
                '}';
    }
}

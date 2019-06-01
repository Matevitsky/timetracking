package com.matevitsky.entity;

import java.util.Objects;

public class Activity {

    private final Integer id;
    private final String title;
    private final String description;
    private final Integer duration;
    private final Integer userId;
    private final Status status;

    public Activity(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.duration = builder.duration;
        this.userId = builder.userId;
        this.status = builder.status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDuration() {
        return duration;
    }

    public Status getStatus() {
        return status;
    }


    public enum Status {
        NEW,
        ACTIVE,
        DONE
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
                Objects.equals(description, activity.description) &&
                Objects.equals(duration, activity.duration) &&
                Objects.equals(userId, activity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, duration, userId);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", userId=" + userId +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String title;
        private String description;
        private Integer duration;
        private Integer userId;
        private Status status;

        private Builder() {

        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}

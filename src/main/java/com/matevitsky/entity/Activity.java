package com.matevitsky.entity;

import java.util.Objects;

public class Activity {

    private String description;

    private Integer id;
    private Status status;
    private String title;
    private Integer duration;
    private Integer userId;

    public String getDescription() {
        return description;
    }


    private Activity() {

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status.name();
    }

    public Integer getDuration() {
        return duration;
    }

    public static Builder newBuilder() {
        return new Activity().new Builder();
    }

    public Integer getUserId() {
        return userId;
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

    public enum Status {
        NEW,
        ACTIVE,
        DONE
    }

    public class Builder {
        private Builder() {

        }

        public Builder withId(Integer id) {
            Activity.this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            Activity.this.description = description;
            return this;
        }

        public Builder withTittle(String title) {
            Activity.this.title = title;
            return this;
        }

        public Builder withDuration(Integer duration) {
            Activity.this.duration = duration;
            return this;
        }

        public Builder withUserId(Integer userId) {
            Activity.this.userId = userId;
            return this;
        }

        public Builder withStatus(Status status) {
            Activity.this.status = status;
            return this;
        }

        public Activity build() {
            return Activity.this;
        }
    }
}

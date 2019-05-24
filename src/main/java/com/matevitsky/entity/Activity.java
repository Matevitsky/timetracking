package com.matevitsky.entity;

import java.util.Objects;

public class Activity {
    private Integer id;
    private String content; //TODO: description
    private String title;

    private Integer duration;
    private Integer userId;


    private Activity() {

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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

    public class Builder {
        private Builder() {

        }

        public Builder withId(Integer id) {
            Activity.this.id = id;
            return this;
        }

        public Builder withContent(String content) {
            Activity.this.content = content;
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

        public Activity build() {
            return Activity.this;
        }
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", userId=" + userId +
                '}';
    }
}

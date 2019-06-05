package com.matevitsky.entity.dto;

import com.matevitsky.entity.Activity;

import java.util.Objects;

/**
 * Class used for admin finished activity page
 */
public class FinishedActivity extends Activity {
    private final String userName;


    public FinishedActivity(String userName, Integer id, String title, String description,
                            Integer duration, Integer userId, Status status) {
        super(newBuilder()
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withDuration(duration)
                .withUserId(userId)
                .withStatus(status));

        this.userName = userName;

    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinishedActivity)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FinishedActivity that = (FinishedActivity) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName);
    }
}

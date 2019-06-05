package com.matevitsky.repository.impl;

import com.matevitsky.entity.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityRepositoryImplTest {

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl();


    @Test
    public void mapToObject() throws SQLException {
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("Title")).thenReturn("Title");
        when(resultSet.getString("Description")).thenReturn("Description");
        when(resultSet.getInt("Duration")).thenReturn(1);
        when(resultSet.getInt("UserId")).thenReturn(1);
        when(resultSet.getString("Status")).thenReturn(String.valueOf(Activity.Status.NEW));

        Activity expected = Activity.newBuilder()
                .withId(1)
                .withTitle("Title")
                .withDescription("Description")
                .withDuration(1)
                .withUserId(1)
                .withStatus(Activity.Status.NEW)
                .build();

        Activity actual = activityRepository.mapToObject(resultSet);

        assertEquals(expected, actual);

    }


}

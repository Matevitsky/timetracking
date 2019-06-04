package com.matevitsky.repository.impl;

import com.matevitsky.entity.ActivityRequest;
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
public class ActivityRequestRepositoryImplTest {

    @InjectMocks
    ActivityRequestRepositoryImpl activityRequestRepository = new ActivityRequestRepositoryImpl();
    @Mock
    private ResultSet resultset;

    @Test
    public void mapToObject() throws SQLException {
        when(resultset.getInt("ID")).thenReturn(1);
        when(resultset.getInt("UserId")).thenReturn(1);

        ActivityRequest expected = new ActivityRequest(1, 1);

        ActivityRequest actual = activityRequestRepository.mapToObject(resultset);
        assertEquals(expected, actual);
    }
}
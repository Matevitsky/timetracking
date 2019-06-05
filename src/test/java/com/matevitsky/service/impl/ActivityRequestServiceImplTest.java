package com.matevitsky.service.impl;

import com.matevitsky.entity.Activity;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.ActivityRequestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityRequestServiceImplTest {

    @Mock
    private ActivityRequestRepository activityRequestRepository;

    @InjectMocks
    private ActivityRequestServiceImpl activityRequestService;

    private Activity activity = Activity.newBuilder().build();


    @Test
    public void createActivityRequest() throws ErrorException {
        when(activityRequestService.createActivityRequest(1)).thenReturn(true);
        boolean actual = activityRequestService.createActivityRequest(1);
        assertTrue(actual);
    }
}
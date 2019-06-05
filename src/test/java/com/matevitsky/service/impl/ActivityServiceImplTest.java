package com.matevitsky.service.impl;

import com.matevitsky.entity.Activity;
import com.matevitsky.repository.impl.ActivityRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepositoryImpl activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;
    private Activity activity = Activity.newBuilder().build();

    @Test
    public void getAllActivityByStatus() {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity);
        when(activityService.getAllActivityByStatus(Activity.Status.NEW.name())).thenReturn(activityList);
        List<Activity> actual = activityService.getAllActivityByStatus("NEW");
        List<Activity> expected = new ArrayList<>();
        expected.add(activity);
        assertEquals(expected, actual);
    }

    @Test
    public void changeActivityStatus() {
        when(activityService.changeActivityStatus(1, "NEW")).thenReturn(true);
        boolean actual = activityService.changeActivityStatus(1, "NEW");
        assertTrue(actual);
    }

    @Test
    public void getAssignedActivityList() {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity);
        when(activityService.getAssignedActivityList(1)).thenReturn(activityList);
        List<Activity> actual = activityService.getAssignedActivityList(1);
        List<Activity> expected = new ArrayList<>();
        expected.add(activity);
        assertEquals(expected, actual);
    }
}
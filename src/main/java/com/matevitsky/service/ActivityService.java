package com.matevitsky.service;


import com.matevitsky.entity.Activity;
import com.matevitsky.exception.ErrorException;

import java.util.List;
import java.util.Optional;

public interface ActivityService {


    boolean createActivity(Activity activity) throws ErrorException;

    boolean deleteActivity(Integer activityId);

    Activity updateActivity(Activity activity);

    Optional<Activity> getActivity(Integer id);

    List<Activity> getAll();

    List<Activity> getActivityListByUserId(Integer userId);

    List<Activity> getAllActivityByStatus(String status);

    boolean changeActivityStatus(Integer id, String status);

    List<Activity> getAssignedActivityList(Integer userId);

    boolean assignActivity(Activity activityForAssign);
}

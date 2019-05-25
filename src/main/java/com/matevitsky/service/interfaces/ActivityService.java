package com.matevitsky.service.interfaces;


import com.matevitsky.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {


    Activity insertActivity(Activity activity);

    boolean deleteActivity(Integer activityId);

    Activity updateActivity(Activity activity);

    Optional<Activity> getActivity(Integer id);

    List<Activity> getAll();

    List<Activity> getActivityListByUserId(Integer userId);

    List<Activity> getUnAssignedActivityList();

}
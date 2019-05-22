package com.matevitsky.service;


import com.matevitsky.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivitiesService {

    Activity insertActivity(Activity activity);

    Activity deleteActivity(Activity activity);

    Activity updateActivity(Activity activity);

    Optional<Activity> getActivity(Integer id);

    List<Activity> getAll();

    List<Activity> getActivityListByUserId(Integer userId);

}

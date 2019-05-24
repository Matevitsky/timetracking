package com.matevitsky.service;


import com.matevitsky.entity.Activity;
import com.matevitsky.repository.ActivityRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ActivityServiceImpl implements ActivityService {

    private ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl();

    @Override
    public Activity insertActivity(Activity activity) {
        activityRepository.create(activity);
        return activity;
    }

    @Override
    public boolean deleteActivity(Integer activityId) {
        return activityRepository.delete(activityId);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return activityRepository.update(activity);
    }

    @Override
    public Optional<Activity> getActivity(Integer id) {

        return activityRepository.getById(id);
    }

    @Override
    public List<Activity> getAll() {

        return activityRepository.getAll();
    }


    public List<Activity> getActivityListByUserId(Integer userId) {

        return activityRepository.getActivityListByUserId(userId);
    }

    @Override
    public List<Activity> getUnAssignedActivityList() {
        return activityRepository.getUnAssignedActivityList();

    }
}

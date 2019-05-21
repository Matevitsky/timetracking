package com.matevitsky.service;


import com.matevitsky.entity.Activity;
import com.matevitsky.repository.db.ActivityRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ActivityServiceImpl implements ActivitiesService {

    private ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl();

    @Override
    public Activity insertActivity(Activity activity) {
        activityRepository.insertEntity(activity);
        return activity;
    }

    @Override
    public Activity deleteActivity(Activity activity) {
        return activityRepository.deleteEntity(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return activityRepository.updateEntity(activity);
    }

    @Override
    public Optional<Activity> getActivity(Integer id) {

        return activityRepository.getEntity(id);
    }

    @Override
    public List<Activity> getAll() {

        return activityRepository.getAll();
    }
}

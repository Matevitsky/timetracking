package com.matevitsky.service.impl;


import com.matevitsky.entity.Activity;
import com.matevitsky.repository.impl.ActivityRepositoryImpl;
import com.matevitsky.repository.interfaces.ActivityRepository;
import com.matevitsky.service.ActivityService;

import java.util.List;
import java.util.Optional;

public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository = new ActivityRepositoryImpl();

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
    public List<Activity> getAllActivityByStatus(String status) {
        //TODO: проверить существует ли такой статус до вызова репозитория

        return activityRepository.getGetAllActivityByStatus(status);
    }

    @Override
    public boolean changeActivityStatus(Integer id, String status) {

        return activityRepository.changeActivityStatus(id, status);
    }

    @Override
    public List<Activity> getAssignedActivityList(Integer userId) {
        return activityRepository.getAssignedActivityList(userId);
    }


}

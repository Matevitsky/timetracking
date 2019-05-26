package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Activity;

import java.util.List;

public interface ActivityRepository extends GenericRepository<Activity> {

    List<Activity> getActivityListByUserId(Integer userId);

    List<Activity> getGetAllActivityByStatus(String status);
}

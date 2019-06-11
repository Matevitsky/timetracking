package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Activity;

import java.sql.SQLException;
import java.util.List;

public interface ActivityRepository extends GenericRepository<Activity> {

    /**
     * Get All activities for specific user by id
     *
     * @param userId
     * @return List of Activities
     */
    List<Activity> getActivityListByUserId(Integer userId);

    /**
     * Get all activities with specific status
     *
     * @param status
     * @return List of Activities
     */
    List<Activity> getGetAllActivityByStatus(String status);

    /**
     * Change activity status
     *
     * @param id
     * @param status
     * @return true/false
     */
    boolean changeActivityStatus(Integer id, String status);

    /**
     * Get activities that assigned to specific userId
     *
     * @param userId
     * @return List of Activities
     */
    List<Activity> getAssignedActivityList(Integer userId);

    /**
     * Assign activity to User and remove activityRequest from ActivityRequest Table
     *
     * @param activityForAssign
     * @return true or false
     */
    boolean assignActivity(Activity activityForAssign) throws SQLException;
}

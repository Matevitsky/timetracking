package com.matevitsky.repository.impl;


import com.matevitsky.entity.Activity;
import com.matevitsky.repository.interfaces.ActivityRepository;
import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl extends AbstractGenericRepository<Activity> implements ActivityRepository {

    private static final String INSERT_ACTIVITY_SQL = "INSERT INTO activities" + "  (Title, Description, Duration, UserId,Status) VALUES  ('%s', '%s', '%d', '%d','%s)";
    private static final String INSERT_ACTIVITY_WITHOUT_USER_ID_SQL = "INSERT INTO activities" + "  (Title, Description, Duration, Status) VALUES  ('%s', '%s', '%d','%s')";
    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM activities WHERE ID=%d;";
    private static final String UPDATE_ACTIVITY_SQL = "UPDATE activities set Title='%s',Description='%s',Duration=%d, UserId=%d, Status='%s' WHERE ID=%d";
    private static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activities WHERE ID=%d";
    private static final String SELECT_ALL_ACTIVITY = "SELECT * FROM activities";
    private static final String SELECT_ACTIVITY_BY_USER_ID = "SELECT * FROM activities WHERE UserId=%d";
    private static final String SELECT_ALL_UNASSIGNED_ACTIVITY = "SELECT * FROM activities WHERE Status='NEW'";

    private static Logger LOGGER = Logger.getLogger(ActivityRepositoryImpl.class);

    @Override
    public Activity create(Activity activity) {
        LOGGER.debug("Method create started, for Activity with Title " + activity.getTitle());
        String query = "";
        if (activity.getUserId() == null) {
            query = String.format(INSERT_ACTIVITY_WITHOUT_USER_ID_SQL, activity.getTitle(), activity.getDescription(), activity.getDuration(), Activity.Status.NEW);

        } else {

            query = String.format(INSERT_ACTIVITY_SQL, activity.getTitle(), activity.getDescription(), activity.getDuration(), activity.getUserId(), Activity.Status.ACTIVE);
        }
        return createEntity(activity, query);

    }

    @Override
    public boolean delete(Integer activityId) {
        LOGGER.debug("Method delete started, for Activity with Title " + activityId);
        String query = String.format(DELETE_ACTIVITY_SQL, activityId);


        return deleteEntity(query);


    }

    @Override
    public Activity update(Activity activity) {
        LOGGER.debug("Method update started, for Activity with Title " + activity.getTitle());
        String query = String.format(UPDATE_ACTIVITY_SQL, activity.getTitle(), activity.getDescription(), activity.getDuration(), activity.getUserId(), activity.getStatus(), activity.getId());

        return updateEntity(activity, query);

    }

    @Override
    public Optional<Activity> getById(Integer id) {
        LOGGER.debug("Method getById started, for Activity with ID " + id);

        String query = String.format(SELECT_ACTIVITY_BY_ID, id);
        Activity activity = null;

        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet allUsersList = entity.isPresent() ? entity.get() : null;
        try {
            while (allUsersList.next()) {
                Integer activityId = allUsersList.getInt("ID");
                String tittle = allUsersList.getString("Title");
                String description = allUsersList.getString("Description");
                Integer duration = allUsersList.getInt("Duration");
                Integer userId = allUsersList.getInt("UserId");
                String status = allUsersList.getString("Status");
                activity = Activity.newBuilder().withId(activityId)
                        .withTittle(tittle)
                        .withDescription(description)
                        .withDuration(duration)
                        .withUserId(userId)
                        .withStatus(Activity.Status.valueOf(status)).build();
            }

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return Optional.of(activity);
    }

    @Override
    public List<Activity> getAll() {
        LOGGER.debug("Method getAllActivities started ");
        List<Activity> activityList = new ArrayList<>();
        CachedRowSet allActivities = getAll(SELECT_ALL_ACTIVITY);
        try {
            while (allActivities.next()) {
                Integer activityId = allActivities.getInt("ID");
                String tittle = allActivities.getString("Title");
                String description = allActivities.getString("Description");
                Integer duration = allActivities.getInt("Duration");
                Integer userId = allActivities.getInt("UserId");
                String status = allActivities.getString("Status");

                Activity activity = Activity.newBuilder().withId(activityId)
                        .withTittle(tittle)
                        .withDescription(description)
                        .withDuration(duration)
                        .withUserId(userId)
                        .withStatus(Activity.Status.valueOf(status)).build();
                activityList.add(activity);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty cachedRowSet");
        }
        return activityList;
    }

    public List<Activity> getActivityListByUserId(Integer userId) {
        LOGGER.debug("Method getActivityListByUserId started ");
        String query = String.format(SELECT_ACTIVITY_BY_USER_ID, userId);
        List<Activity> activityList = new ArrayList<>();
        try (Connection connection = connectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer activityId = resultSet.getInt("ID");
                String tittle = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                Integer duration = resultSet.getInt("Duration");
                String status = resultSet.getString("Status");
                Activity activity = Activity.newBuilder().withId(activityId)
                        .withTittle(tittle)
                        .withDescription(description)
                        .withDuration(duration)
                        .withUserId(userId)
                        .withStatus(Activity.Status.valueOf(status)).build();
                activityList.add(activity);
            }

        } catch (SQLException e) {
            LOGGER.warn("Field to get activity List byUserId " + userId + " " + e.getMessage());
        }
        return activityList;
    }

    public List<Activity> getUnAssignedActivityList() {
        LOGGER.debug("Method getUnAssignedActivityList started ");
        List<Activity> unAssignedActivityList = new ArrayList<>();
        try (Connection connection = connectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_UNASSIGNED_ACTIVITY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer activityId = resultSet.getInt("ID");
                String tittle = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                Integer duration = resultSet.getInt("Duration");
                String status = resultSet.getString("Status");
                Activity activity = Activity.newBuilder().withId(activityId)
                        .withTittle(tittle)
                        .withDescription(description)
                        .withDuration(duration)
                        .withUserId(0)
                        .withStatus(Activity.Status.valueOf(status)).build();
                unAssignedActivityList.add(activity);
            }
        } catch (SQLException e) {
            LOGGER.warn("Filed to get UnAssigned Activity " + e.getMessage());
        }
        return unAssignedActivityList;
    }
}
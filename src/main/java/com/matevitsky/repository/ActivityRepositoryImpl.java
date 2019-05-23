package com.matevitsky.repository;


import com.matevitsky.entity.Activity;
import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl extends AbstractGenericRepository<Activity> implements GenericRepository<Activity> {

    private static final String INSERT_ACTIVITY_SQL = "INSERT INTO activities" + "  (Title, Content, Duration, UserId) VALUES  ('%s', '%s', '%d', '%d')";
    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM activities WHERE ID=%d;";
    private static final String UPDATE_ACTIVITY_SQL = "UPDATE activities set Title='%s',Content='%s',Duration=%d WHERE ID=%d";
    private static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activities WHERE ID=%d";
    private static final String SELECT_ALL_ACTIVITY = "SELECT * FROM activities";
    private static final String SELECT_ACTIVITY_BY_USER_ID = "SELECT * FROM activities WHERE UserId=%d";
    private static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public Activity create(Activity activity) {
        LOGGER.debug("Method create started, for Activity with Title " + activity.getTitle());

        String query = String.format(INSERT_ACTIVITY_SQL, activity.getTitle(), activity.getContent(), activity.getDuration(), activity.getId());

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

        String query = String.format(UPDATE_ACTIVITY_SQL, activity.getTitle(), activity.getContent(), activity.getDuration(), activity.getId());
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
                Integer activityId = allUsersList.getInt(1);
                String tittle = allUsersList.getString(2);
                String content = allUsersList.getString(3);
                Integer duration = allUsersList.getInt(4);
                Integer userId = allUsersList.getInt(5);
                activity = new Activity(activityId, tittle, content, duration, userId);
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
                Integer activityId = allActivities.getInt(1);
                String tittle = allActivities.getString(2);
                String content = allActivities.getString(3);
                Integer duration = allActivities.getInt(4);
                Integer userId = allActivities.getInt(5);

                Activity activity = new Activity(activityId, tittle, content, duration, userId);
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
                Integer activityId = resultSet.getInt(1);
                String tittle = resultSet.getString(2);
                String content = resultSet.getString(3);
                Integer duration = resultSet.getInt(4);

                Activity activity = new Activity(activityId, tittle, content, duration, userId);
                activityList.add(activity);
            }

        } catch (SQLException e) {
            LOGGER.warn("Field to get activity List byUserId " + userId + " " + e.getMessage());
        }
        return activityList;
    }
}

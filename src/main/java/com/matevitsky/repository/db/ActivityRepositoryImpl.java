package com.matevitsky.repository.db;


import com.matevitsky.entity.Activity;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl extends AbstractGenericRepository<Activity> implements GenericRepository<Activity> {

    private static final String INSERT_ACTIVITY_SQL = "INSERT INTO Activities" + "  (Title, Content, Duration, UserId) VALUES  ('%s', '%s', '%d', '%d')";
    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM Activities WHERE ID=%d;";
    private static final String UPDATE_ACTIVITY_SQL = "UPDATE Activities set Title='%s',Content='%s',Duration=%d WHERE ID=%d";
    private static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM Activities WHERE ID=%d";
    private static final String SELECT_ALL_ACTIVITY = "SELECT * FROM Activities";
    // private static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public Activity insertEntity(Activity activity) {
        //  LOGGER.debug("Method insertEntity started, for Activity with Title " + activity.getTitle());

        String query = String.format(INSERT_ACTIVITY_SQL, activity.getTitle(), activity.getContent(), activity.getDuration(), activity.getActivityId());

        return createEntity(activity, query);

    }

    @Override
    public Activity deleteEntity(Activity activity) {
        // LOGGER.debug("Method deleteEntity started, for Activity with Title " + activity.getTitle());
        String query = String.format(DELETE_ACTIVITY_SQL, activity.getActivityId());


        return deleteEntity(activity, query);

        //  LOGGER.info("Failed to delete activity with ID " + activity.getActivityId() + " and Tittle " + activity.getTitle());

    }

    @Override
    public Activity updateEntity(Activity activity) {
        // LOGGER.debug("Method updateEntity started, for Activity with Title " + activity.getTitle());

        String query = String.format(UPDATE_ACTIVITY_SQL, activity.getTitle(), activity.getContent(), activity.getDuration(), activity.getActivityId());
        return updateEntity(activity, query);

    }

    @Override
    public Optional<Activity> getEntity(Integer id) {
        //  LOGGER.debug("Method getEntity started, for Activity with ID " + id);

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
            // LOGGER.warn(e.fillInStackTrace().getMessage());
        }
        return Optional.of(activity);
    }

    @Override
    public List<Activity> getAll() {
        // LOGGER.debug("Method getAllActivities started ");
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
            // LOGGER.warn("GetAll method return empty cachedRowSet");
        }
        return activityList;
    }

}


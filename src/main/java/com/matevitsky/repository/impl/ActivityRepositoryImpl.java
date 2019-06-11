package com.matevitsky.repository.impl;


import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Activity;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.ActivityRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl extends AbstractGenericRepository<Activity> implements ActivityRepository {

    private static final String INSERT_ACTIVITY_SQL = "INSERT INTO activities" + "  (Title, Description, Duration, UserId,Status) VALUES  ('%s', '%s', '%d', '%d','%s)";
    private static final String INSERT_ACTIVITY_WITHOUT_USER_ID_SQL = "INSERT INTO activities" + "  (Title, Description, Status) VALUES  ('%s', '%s','%s')";
    private static final String DELETE_ACTIVITY_SQL = "DELETE FROM activities WHERE ID=%d;";
    private static final String UPDATE_ACTIVITY_SQL = "UPDATE activities set Title='%s',Description='%s',Duration=%d, UserId=%d, Status='%s' WHERE ID=%d";
    private static final String SELECT_ACTIVITY_BY_ID = "SELECT * FROM activities WHERE ID=%d";
    private static final String SELECT_ALL_ACTIVITY = "SELECT * FROM activities";
    private static final String SELECT_ACTIVITY_BY_USER_ID = "SELECT * FROM activities WHERE UserId=%d";
    private static final String SELECT_ALL_ACTIVITIES_BY_STATUS = "SELECT * FROM activities WHERE Status='%s'";
    private static final String UPDATE_ACTIVITY_STATUS = "UPDATE activities set Status='%s' WHERE ID=%d";
    private static final String SELECT_ASSIGNED_ACTIVITY = "SELECT * FROM activities WHERE Status='ACTIVE' AND UserId=%d";

    private static final String DELETE_REQUEST_SQL = "DELETE FROM activityRequests WHERE Id=%d";

    private static final Logger LOGGER = Logger.getLogger(ActivityRepositoryImpl.class);

    @Override
    public boolean create(Activity activity) throws ErrorException {
        LOGGER.debug("Method create started, for Activity with Title " + activity.getTitle());
        String query;
        if (activity.getUserId() == null) {
            query = String.format(INSERT_ACTIVITY_WITHOUT_USER_ID_SQL, activity.getTitle(), activity.getDescription(), Activity.Status.NEW);

        } else {

            query = String.format(INSERT_ACTIVITY_SQL, activity.getTitle(), activity.getDescription(), activity.getDuration(), activity.getUserId(), Activity.Status.ACTIVE);
        }
        return createEntity(activity, query);

    }

    @Override
    public boolean deleteById(Integer activityId) {
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

        return getById(id, query);

    }

    @Override
    public List<Activity> getAll() {
        LOGGER.debug("Method getAllActivities started ");

        Optional<List<Activity>> activityList = getAll(SELECT_ALL_ACTIVITY);

        if (activityList.isPresent()) {
            return activityList.get();
        }
        LOGGER.debug("GetAll activities returned empty list");
        return new ArrayList<>();
    }

    public List<Activity> getActivityListByUserId(Integer userId) {
        LOGGER.debug("Method getActivityListByUserId started ");
        String query = String.format(SELECT_ACTIVITY_BY_USER_ID, userId);
        List<Activity> activityList = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            activityList = mapToList(resultSet);

        } catch (SQLException e) {
            LOGGER.warn("Field to get activity List byUserId " + userId + " " + e.getMessage());
        }
        return activityList;
    }

    @Override
    public List<Activity> getGetAllActivityByStatus(String status) {
        LOGGER.debug("Method getAllActivityByStatus started ");

        List<Activity> activityList = new ArrayList<>();

        if (Arrays.stream(Activity.Status.values()).noneMatch(t -> t.name().equals(status))) {
            LOGGER.debug("Status " + status + " not exist");
            return activityList;
        }
        String query = String.format(SELECT_ALL_ACTIVITIES_BY_STATUS, status);

        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            activityList = mapToList(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("Filed to get UnAssigned Activity " + e.getMessage());
        }
        return activityList;
    }

    @Override
    public boolean changeActivityStatus(Integer id, String status) {
        LOGGER.debug("Method changeActivityStatus started ");
        boolean result = false;
        String query = String.format(UPDATE_ACTIVITY_STATUS, status, id);
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                result = true;
            }

        } catch (SQLException e) {
            LOGGER.warn("Filed to change status to activity with Id " + id);
        }
        return result;
    }

    @Override
    public List<Activity> getAssignedActivityList(Integer userId) {
        LOGGER.debug("Method getAssignedActivityList started");
        String query = String.format(SELECT_ASSIGNED_ACTIVITY, userId);
        List<Activity> assignedActivityList = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            assignedActivityList = mapToList(resultSet);

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return assignedActivityList;
    }

    @Override
    public boolean assignActivity(Activity activityForAssign) throws SQLException {
        LOGGER.debug("Method assignActivity started");
        Connection connection = null;
        PreparedStatement assignActivityStatement = null;
        PreparedStatement removeRequestStatement = null;

        String assignQuery = String.format(UPDATE_ACTIVITY_SQL
                , activityForAssign.getTitle()
                , activityForAssign.getDescription()
                , activityForAssign.getDuration()
                , activityForAssign.getUserId()
                , activityForAssign.getStatus()
                , activityForAssign.getId());

        String removerRequestQuery = String.format(DELETE_REQUEST_SQL, activityForAssign.getId());
        try {
            connection = ConnectorDB.getConnection();
            connection.setAutoCommit(false);
            assignActivityStatement = connection.prepareStatement(assignQuery);
            removeRequestStatement = connection.prepareStatement(removerRequestQuery);
            assignActivityStatement.execute();
            removeRequestStatement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Transaction failed " + e.getMessage());
            connection.rollback();
        } finally {
            if (assignActivityStatement != null) {
                assignActivityStatement.close();
            }
            if (removeRequestStatement != null) {
                removeRequestStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
        return false;

    }


    @Override
    protected List<Activity> mapToList(ResultSet resultSet) throws SQLException {
        List<Activity> activityList = new ArrayList<>();
        while (resultSet.next()) {
            Activity activity = getActivity(resultSet);
            activityList.add(activity);
        }
        return activityList;
    }

    @Override
    protected Activity mapToObject(ResultSet resultSet) throws SQLException {

        resultSet.next();

        return getActivity(resultSet);
    }

    private Activity getActivity(ResultSet resultSet) throws SQLException {
        Activity activity;
        Integer activityId = resultSet.getInt("ID");
        String tittle = resultSet.getString("Title");
        String description = resultSet.getString("Description");
        Integer duration = resultSet.getInt("Duration");
        Integer userId = resultSet.getInt("UserId");
        String status = resultSet.getString("Status");
        activity = Activity.newBuilder().withId(activityId)
                .withTitle(tittle)
                .withDescription(description)
                .withDuration(duration)
                .withUserId(userId)
                .withStatus(Activity.Status.valueOf(status)).build();
        return activity;
    }
}
package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.ActivityRequestRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRequestRepositoryImpl extends AbstractGenericRepository<ActivityRequest> implements ActivityRequestRepository {

    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM activityRequests";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO activityRequests" + "  (UserId) VALUES  ('%d')";
    private static final String DELETE_REQUEST_SQL = "DELETE FROM activityRequests WHERE Id=%d";
    private static final String SELECT_REQUEST_ID = "SELECT * FROM activityRequests WHERE UserId=%d";
    private static final Logger LOGGER = Logger.getLogger(ActivityRequestRepositoryImpl.class);

    @Override
    public boolean create(ActivityRequest activityRequest) throws ErrorException {
        LOGGER.debug("Method create activityRequest with UserId " + activityRequest.getUserId());
        String query = String.format(INSERT_REQUEST_SQL, activityRequest.getUserId());

        return createEntity(activityRequest, query);
    }

    @Override
    public boolean deleteById(Integer id) {
        LOGGER.debug("Method delete request with UserId " + id);
        String query = String.format(DELETE_REQUEST_SQL, id);
        return deleteEntity(query);
    }

    @Override
    public ActivityRequest update(ActivityRequest entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ActivityRequest> getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    public List<ActivityRequest> getByUserId(Integer id) {
        LOGGER.debug("Method  getRequestById " + id + " started");

        List<ActivityRequest> activityRequestList = new ArrayList<>();
        String query = String.format(SELECT_REQUEST_ID, id);

        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            activityRequestList = mapToList(resultSet);

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity from database " + e.getMessage());
        }


        return activityRequestList;
    }

    @Override
    public List<ActivityRequest> getAll() {
        LOGGER.debug("Method getAll Activities requests started ");

        Optional<List<ActivityRequest>> all = getAll(SELECT_ALL_REQUESTS);
        if (all.isPresent()) {
            return all.get();
        }
        LOGGER.debug("Method getAll returned empty list ");
        return new ArrayList<>();
    }

    @Override
    protected List<ActivityRequest> mapToList(ResultSet resultSet) throws SQLException {
        List<ActivityRequest> activityRequestsList = new ArrayList<>();
        while (resultSet.next()) {
            ActivityRequest activityRequest = mapToObject(resultSet);
            activityRequestsList.add(activityRequest);
        }
        return activityRequestsList;
    }

    @Override
    protected ActivityRequest mapToObject(ResultSet resultSet) throws SQLException {

        Integer requestId = resultSet.getInt("ID");
        Integer userId = resultSet.getInt("UserId");
        return new ActivityRequest(requestId, userId);
    }
}

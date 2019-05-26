package com.matevitsky.repository.impl;

import com.matevitsky.entity.RemoveActivityRequest;
import com.matevitsky.repository.interfaces.RemoveActivityRequestRepository;
import org.apache.log4j.Logger;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveActivityRequestRepositoryImpl extends AbstractGenericRepository<RemoveActivityRequest> implements RemoveActivityRequestRepository {

    private static final String INSERT_REQUEST_FOR_REMOVE_SQL = "INSERT INTO removeActivityRequests" + "  (UserId,ActivityId) VALUES  ('%d','%d')";
    private static final String DELETE_REQUEST_FOR_REMOVE_SQL = "DELETE FROM removeActivityRequests WHERE Id=%d";
    private static final String SELECT_REQUEST_FOR_REMOVE_ID = "SELECT * FROM removeActivityRequests WHERE UserId=%d";
    private static final String SELECT_ALL_REQUESTS_FOR_REMOVE = "SELECT * FROM removeActivityRequests";

    private static Logger LOGGER = Logger.getLogger(RemoveActivityRequestRepositoryImpl.class);

    @Override
    public boolean create(RemoveActivityRequest removeRequest) {
        LOGGER.debug("Method create started for remove request with id " + removeRequest.getId());
        String query = String.format(INSERT_REQUEST_FOR_REMOVE_SQL, removeRequest.getUserId(), removeRequest.getActivityId());
        return createEntity(removeRequest, query);

    }

    @Override
    public boolean delete(Integer id) {
        LOGGER.debug("Method delete started  with id " + id);
        String query = String.format(DELETE_REQUEST_FOR_REMOVE_SQL, id);
        return deleteEntity(query);

    }

    @Override
    public RemoveActivityRequest update(RemoveActivityRequest removeRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<RemoveActivityRequest> getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    public List<RemoveActivityRequest> getByUserId(Integer id) {

        LOGGER.debug("Method  getRequestById " + id + " started");
        List<RemoveActivityRequest> removeActivityRequestList = new ArrayList<>();
        String query = String.format(SELECT_REQUEST_FOR_REMOVE_ID, id);
        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet resultSet = entity.isPresent() ? entity.get() : null;
        try {
            createRemoveRequestList(removeActivityRequestList, resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e.fillInStackTrace().getMessage());
        }


        return removeActivityRequestList;
    }

    @Override
    public List<RemoveActivityRequest> getAll() {
        List<RemoveActivityRequest> removeActivityRequestList = new ArrayList<>();

        LOGGER.debug("Method getAll remove request started ");

        RowSet rows = null;

        try (Connection connection = connectorDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS_FOR_REMOVE)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            createRemoveRequestList(removeActivityRequestList, resultSet);
        } catch (SQLException e) {
            LOGGER.warn("Failed to get Requests error:" + e.getMessage());
        }
        return removeActivityRequestList;
    }


    private void createRemoveRequestList(List<RemoveActivityRequest> removeActivityRequestList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Integer requestId = resultSet.getInt("ID");
            Integer userId = resultSet.getInt("UserId");
            Integer activityId = resultSet.getInt("activityId");

            RemoveActivityRequest removeActivityRequest = new RemoveActivityRequest(requestId, userId, activityId);
            removeActivityRequestList.add(removeActivityRequest);

        }
    }
}

package com.matevitsky.repository;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Request;
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

public class ActivityRequestRepositoryImpl extends AbstractGenericRepository<Request> implements ActivityRequestRepository {

    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM activityRequests";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO activityRequests" + "  (UserId) VALUES  ('%d')";
    private static final String DELETE_REQUEST_SQL = "DELETE FROM activityRequests WHERE UserId=%d";
    private static final String SELECT_REQUEST_ID = "SELECT * FROM activityRequests WHERE UserId=%d";

    private static Logger LOGGER = Logger.getLogger(ActivityRequestRepositoryImpl.class);
    ConnectorDB connectorDB = new ConnectorDB();


    @Override
    public Request create(Request request) {
        LOGGER.debug("Method create request with UserId " + request.getUserId());
        String query = String.format(INSERT_REQUEST_SQL, request.getUserId());

        Request entity = createEntity(request, query);

        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        LOGGER.debug("Method delete request with UserId " + id);
        String query = String.format(DELETE_REQUEST_SQL, id);
        return deleteEntity(query);


    }

    @Override
    public Request update(Request entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Request> getById(Integer id) {
        throw new UnsupportedOperationException();
    }

    public List<Request> getByUserId(Integer id) {
        LOGGER.debug("Method  getRequestById " + id + " started");
        List<Request> requestList = new ArrayList<>();
        String query = String.format(SELECT_REQUEST_ID, id);
        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet resultSet = entity.isPresent() ? entity.get() : null;
        try {
            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");
                Request request = new Request(requestId, userId);
                requestList.add(request);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.fillInStackTrace().getMessage());
        }


        return requestList;

    }

    @Override
    public List<Request> getAll() {
        List<Request> activityRequestList = new ArrayList<>();

        LOGGER.debug("Method getAll activities started ");

        RowSet rows = null;

        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");

                Request request = new Request(requestId, userId);
                activityRequestList.add(request);

            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to get Requests error:" + e.getMessage());
        }
        return activityRequestList;
    }
}

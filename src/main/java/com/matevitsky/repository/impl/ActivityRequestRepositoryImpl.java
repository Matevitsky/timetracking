package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.ActivityRequest;
import com.matevitsky.repository.interfaces.ActivityRequestRepository;
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

public class ActivityRequestRepositoryImpl extends AbstractGenericRepository<ActivityRequest> implements ActivityRequestRepository {

    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM activityRequests";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO activityRequests" + "  (UserId) VALUES  ('%d')";
    private static final String DELETE_REQUEST_SQL = "DELETE FROM activityRequests WHERE Id=%d";
    private static final String SELECT_REQUEST_ID = "SELECT * FROM activityRequests WHERE UserId=%d";

    private static Logger LOGGER = Logger.getLogger(ActivityRequestRepositoryImpl.class);



    @Override
    public boolean create(ActivityRequest activityRequest) {
        LOGGER.debug("Method create activityRequest with UserId " + activityRequest.getUserId());
        String query = String.format(INSERT_REQUEST_SQL, activityRequest.getUserId());

        return createEntity(activityRequest, query);


    }

    @Override
    public boolean delete(Integer id) {
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


    //TODO: подумать как назвать метод. лучше воспользоваться готовым
    public List<ActivityRequest> getByUserId(Integer id) {
        LOGGER.debug("Method  getRequestById " + id + " started");
        List<ActivityRequest> activityRequestList = new ArrayList<>();
        String query = String.format(SELECT_REQUEST_ID, id);
        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet resultSet = entity.isPresent() ? entity.get() : null;
        try {
            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");
                ActivityRequest activityRequest = new ActivityRequest(requestId, userId);
                activityRequestList.add(activityRequest);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.fillInStackTrace().getMessage());
        }


        return activityRequestList;

    }

    @Override
    public List<ActivityRequest> getAll() {
        List<ActivityRequest> activityRequestsList = new ArrayList<>();

        LOGGER.debug("Method getAll Activities requests started ");

        RowSet rows = null;

        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS)) {

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");

                ActivityRequest activityRequest = new ActivityRequest(requestId, userId);
                activityRequestsList.add(activityRequest);

            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to get Requests error:" + e.getMessage());
        }
        return activityRequestsList;
    }
}

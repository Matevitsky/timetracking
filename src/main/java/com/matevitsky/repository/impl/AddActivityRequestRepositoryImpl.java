package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.AddActivityRequest;
import com.matevitsky.repository.interfaces.AddActivityRequestRepository;
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

public class AddActivityRequestRepositoryImpl extends AbstractGenericRepository<AddActivityRequest> implements AddActivityRequestRepository {

    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM activityRequests";
    private static final String INSERT_REQUEST_SQL = "INSERT INTO activityRequests" + "  (UserId) VALUES  ('%d')";
    private static final String DELETE_REQUEST_SQL = "DELETE FROM activityRequests WHERE Id=%d";
    private static final String SELECT_REQUEST_ID = "SELECT * FROM activityRequests WHERE UserId=%d";

    private static Logger LOGGER = Logger.getLogger(AddActivityRequestRepositoryImpl.class);
    ConnectorDB connectorDB = new ConnectorDB();


    @Override
    public AddActivityRequest create(AddActivityRequest addActivityRequest) {
        LOGGER.debug("Method create addActivityRequest with UserId " + addActivityRequest.getUserId());
        String query = String.format(INSERT_REQUEST_SQL, addActivityRequest.getUserId());

        AddActivityRequest entity = createEntity(addActivityRequest, query);

        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        LOGGER.debug("Method delete request with UserId " + id);
        String query = String.format(DELETE_REQUEST_SQL, id);
        return deleteEntity(query);


    }

    @Override
    public AddActivityRequest update(AddActivityRequest entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AddActivityRequest> getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    //TODO: подумать как назвать метод. лучше воспользоваться готовым
    public List<AddActivityRequest> getByUserId(Integer id) {
        LOGGER.debug("Method  getRequestById " + id + " started");
        List<AddActivityRequest> addActivityRequestList = new ArrayList<>();
        String query = String.format(SELECT_REQUEST_ID, id);
        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet resultSet = entity.isPresent() ? entity.get() : null;
        try {
            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");
                AddActivityRequest addActivityRequest = new AddActivityRequest(requestId, userId);
                addActivityRequestList.add(addActivityRequest);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.fillInStackTrace().getMessage());
        }


        return addActivityRequestList;

    }

    @Override
    public List<AddActivityRequest> getAll() {
        List<AddActivityRequest> activityAddActivityRequestList = new ArrayList<>();

        LOGGER.debug("Method getAll addActivities requests started ");

        RowSet rows = null;

        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            while (resultSet.next()) {
                Integer requestId = resultSet.getInt("ID");
                Integer userId = resultSet.getInt("UserId");

                AddActivityRequest addActivityRequest = new AddActivityRequest(requestId, userId);
                activityAddActivityRequestList.add(addActivityRequest);

            }
        } catch (SQLException e) {
            LOGGER.warn("Failed to get Requests error:" + e.getMessage());
        }
        return activityAddActivityRequestList;
    }
}

package com.matevitsky.repository.db;

import com.matevitsky.db.ConnectorDB;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractGenericRepository<E> implements GenericRepository<E> {

//    private static Logger LOGGER = Logger.getLogger(AbstractGenericRepository.class);

    ConnectorDB connectorDB = new ConnectorDB();

    protected E createEntity(E entity, String query) {
        //   LOGGER.debug("getEntity" + entity.toString() + " Query = " + query);
        boolean resultOfCreation = false;
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                // LOGGER.warn("Creating entity failed, no rows affected.");

            } else {
                resultOfCreation = true;
            }

        } catch (SQLException e) {
            //  LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return entity;
    }

    protected Optional<CachedRowSet> getEntity(Integer id, String query) {
        // LOGGER.debug("getEntity with id " + id + " Query = " + query);

        //TODO: заменить casherowset

        CachedRowSet rows = null;
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery(query);
            rows = new CachedRowSetImpl();
            rows.populate(resultSet);

        } catch (SQLException e) {
            // LOGGER.error("Failed to get entity from database " + e.getMessage());
        }
        return Optional.of(rows);
    }

    protected E deleteEntity(E entity, String query) {

        //  LOGGER.debug("deleteEntity with id " + entity.toString() + " Query = " + query);
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (preparedStatement.executeUpdate(query) == 0) {
                // LOGGER.warn("DeleteEntity " + entity.toString() + " failed ");
            }
        } catch (SQLException e) {
            //  LOGGER.error("DeleteEntity failed " + e.getMessage());
        }

        return entity;
    }


    protected E updateEntity(E entity, String query) {
        //  LOGGER.debug("updateEntity with id " + entity.toString() + " Query = " + query);
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (preparedStatement.executeUpdate(query) > 0) {
                return entity;
            } else {
                //  LOGGER.warn("Update entity " + entity.toString() + " filed");
            }

        } catch (SQLException e) {
            // LOGGER.error("updateEntity" + e.getMessage());
        }
        return entity;
    }


    protected CachedRowSet getAll(String query) {
        // LOGGER.debug("getAllEntity " + " Query = " + query);
        CachedRowSet allEntity = null;
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            allEntity = new CachedRowSetImpl();
            allEntity.populate(resultSet);
        } catch (SQLException e) {
            // LOGGER.error("updateEntity" + e.getMessage());
        }
        return allEntity;
    }

}



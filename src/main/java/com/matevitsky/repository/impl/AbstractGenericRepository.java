package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.repository.interfaces.GenericRepository;
import com.sun.rowset.CachedRowSetImpl;
import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractGenericRepository<E> implements GenericRepository<E> {

    private static Logger LOGGER = Logger.getLogger(AbstractGenericRepository.class);



    boolean createEntity(E entity, String query) {
        LOGGER.debug("createEntity" + entity.toString() + " Query = " + query);
        boolean resultOfCreation = false;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.warn("Creating entity failed, no rows affected.");

            } else {
                resultOfCreation = true;
            }

        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        //TODO: подумать что вернуть в случае null почитать про статус код и перезти юзера на нужную странучку сдеалть заглушку на все случаи жизни
        return resultOfCreation;
    }

    protected Optional<CachedRowSet> getEntity(Integer id, String query) {
        LOGGER.debug("getById with id " + id + " Query = " + query);

        //TODO: заменить casherowset

        CachedRowSet rows = null;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery(query);
            rows = new CachedRowSetImpl();
            rows.populate(resultSet);

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity from database " + e.getMessage());
        }
        return Optional.ofNullable(rows);
    }

    protected boolean deleteEntity(String query) {
        boolean result = true;
        LOGGER.debug("delete " + " Query = " + query);
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.warn("DeleteEntity " + " failed ");
                result = false;
            }
        } catch (SQLException e) {
            LOGGER.error("DeleteEntity failed " + e.getMessage());
        }

        return result;
    }


    protected E updateEntity(E entity, String query) {
        LOGGER.debug("update with id " + entity.toString() + " Query = " + query);
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (preparedStatement.executeUpdate() > 0) {
                return entity;
            } else {
                LOGGER.warn("Update entity " + entity.toString() + " filed");
            }

        } catch (SQLException e) {
            LOGGER.error("update" + e.getMessage());
        }
        return entity;
    }


    protected CachedRowSet getAll(String query) {
        LOGGER.debug("getAllEntity " + " Query = " + query);
        CachedRowSet allEntity = null;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            allEntity = new CachedRowSetImpl();
            allEntity.populate(resultSet);
        } catch (SQLException e) {
            LOGGER.error("update" + e.getMessage());
        }
        return allEntity;
    }

}



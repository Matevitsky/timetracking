package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.GenericRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Abstract Generic repository with Crud realization
 *
 * @param <E> Entity
 */
public abstract class AbstractGenericRepository<E> implements GenericRepository<E> {

    private static final String QUERY = " Query = ";
    private static final Logger LOGGER = Logger.getLogger(AbstractGenericRepository.class);

    /**
     * Create entity with query
     *
     * @param entity
     * @param query
     * @return boolean
     * @throws ErrorException
     */
    boolean createEntity(E entity, String query) throws ErrorException {
        LOGGER.debug("createEntity" + entity.toString() + QUERY + query);
        boolean resultOfCreation;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.warn("Creating entity failed, no rows affected.");
                resultOfCreation = false;
            } else {
                resultOfCreation = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());

            throw new ErrorException("");

        }
        return resultOfCreation;
    }


    /**
     * get entity by id with Query
     *
     * @param id
     * @param query
     * @return Optional<E>
     */
    protected Optional<E> getById(Integer id, String query) {
        LOGGER.debug("getById with id " + id + QUERY + query);

        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity from database " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Delete Entity with query
     *
     * @param query
     * @return
     */
    protected boolean deleteEntity(String query) {
        boolean result = true;
        LOGGER.debug("delete " + QUERY + query);
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


    /**
     * Update entity with query
     *
     * @param entity
     * @param query
     * @return
     */
    protected E updateEntity(E entity, String query) {
        LOGGER.debug("update with id " + entity.toString() + QUERY + query);
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


    /**
     *
     * Get all entity with query
     * @param query
     * @return
     */
    public Optional<List<E>> getAll(String query) {
        LOGGER.debug("getAllEntity " + QUERY + query);
        List<E> listOfObjects = null;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            listOfObjects = mapToList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("update" + e.getMessage());
        }
        return Optional.ofNullable(listOfObjects);
    }

    protected abstract List<E> mapToList(ResultSet rs) throws SQLException;

    protected abstract E mapToObject(ResultSet rs) throws SQLException;

}



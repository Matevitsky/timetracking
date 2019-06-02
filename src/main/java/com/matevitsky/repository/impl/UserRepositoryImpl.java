package com.matevitsky.repository.impl;


import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl extends AbstractGenericRepository<User> implements UserRepository {


    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (Name, Email, Password, Role) VALUES  ('%s', '%s', '%s', '%s')";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE ID=%d";
    private static final String UPDATE_USERS_SQL = "UPDATE users set Name='%s',Email='%s',Password=%s,Role='%s' where ID=%d";
    private static final String SELECT_USER_BY_ID = "SELECT  * FROM role LEFT JOIN users USING (ID) WHERE ID=%d";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users INNER JOIN role ON users.Role = role.ID where users.Email='%s'";



    private static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public boolean create(User user) {
        LOGGER.debug("Method create started, for user with Email " + user.getEmail());
        if (user == null) {
            return false;
        }
        String query = String.format(INSERT_USERS_SQL, user.getName(), user.getEmail(), user.getPassword(), user.getRole().getId());
        return createEntity(user, query);

    }

    @Override
    public Optional<User> getById(Integer id) {
        // LOGGER.debug("Method getById started, for id " + id);

        User user = null;
        String query = String.format(SELECT_USER_BY_ID, id);
        Optional<User> userById = getById(id, query);
        if (userById.isPresent()) {
            user = userById.get();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public boolean deleteById(Integer userId) {
        boolean result = false;
        String query = String.format(DELETE_USERS_SQL, userId);
        if (deleteEntity(query)) {
            result = true;
        }
        return result;
    }

    @Override
    public User update(User user) {
        String query = String.format(UPDATE_USERS_SQL, user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getId());
        return updateEntity(user, query);
    }

    @Override
    public List<User> getAll() {

        Optional<List<User>> userList = getAll(SELECT_ALL_USERS);

        if (userList.isPresent()) {
            return userList.get();
        } else {
            LOGGER.warn("GetAll method return empty List");
        }

        return null;
    }

    public Optional<User> findUserByEmail(String email) throws ErrorException {
        LOGGER.debug("findUserByEmail with loginEmail " + email);
        String query = String.format(SELECT_USER_BY_EMAIL, email);
        User user;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            user = mapToObject(resultSet);

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity from database " + e.getMessage());
            throw new ErrorException("email " + email + "not exist, please signup first");
        }
        return Optional.ofNullable(user);

    }

    @Override
    protected List<User> mapToList(ResultSet resultSet) throws SQLException {
        List<User> allUserList = new ArrayList<>();
        User user;
        try {
            while (resultSet.next()) {
                user = mapToUser(resultSet);
                allUserList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty cachedRowSet");
        }
        return allUserList;
    }


    @Override
    protected User mapToObject(ResultSet resultSet) throws SQLException {
        User user = null;
        try {
            resultSet.next();
            user = mapToUser(resultSet);

        } catch (SQLException e) {
            LOGGER.warn("Failed to map User entity to Object " + e.getMessage());
        }
        return user;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        User user;
        int id = resultSet.getInt("ID");
        String userName = resultSet.getString("Name");
        String userEmail = resultSet.getString("Email");
        String userPassword = resultSet.getString("Password");
        String roleName = resultSet.getString("RoleName");




        user = User.newBuilder()
                .withId(id)
                .withName(userName)
                .withEmail(userEmail)
                .withPassword(userPassword)
                .withRole(new Role(0, roleName))
                .build();
        return user;
    }
}


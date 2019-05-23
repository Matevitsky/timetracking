package com.matevitsky.repository;


import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import org.apache.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
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
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE ID=%d";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE Email='%s'";

    ConnectorDB connectorDB = new ConnectorDB();
    RoleRepository roleRepository = new RoleRepositoryImpl();

    private static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public User create(User user) {
        LOGGER.debug("Method create started, for user with Email " + user.getEmail());

        String query = String.format(INSERT_USERS_SQL, user.getName(), user.getEmail(), user.getPassword(), user.getRole());

        return createEntity(user, query);

    }

    @Override
    public Optional<User> getById(Integer id) {
        // LOGGER.debug("Method getById started, for id " + id);
        User user = null;
        String query = String.format(SELECT_USER_BY_ID, id);
        Optional<CachedRowSet> entity = getEntity(id, query);
        CachedRowSet cachedRowSet = entity.isPresent() ? entity.get() : null;
        try {
            while (cachedRowSet.next()) {
                Integer userId = cachedRowSet.getInt(1);
                String userName = cachedRowSet.getString(2);
                String userEmail = cachedRowSet.getString(3);
                String userPassword = cachedRowSet.getString(4);
                String role = cachedRowSet.getString(5);

                user = new User(userId, userName, userEmail, userPassword, new Role(role));
            }

        } catch (SQLException e) {
            LOGGER.warn(e.fillInStackTrace().getMessage());
        }
        return Optional.ofNullable(user);
    }


    @Override
    public boolean delete(Integer userId) {
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
        List<User> allUserList = new ArrayList<>();
        CachedRowSet allUsers = getAll(SELECT_ALL_USERS);
        try {
            while (allUsers.next()) {
                int id = allUsers.getInt("ID");
                String name = allUsers.getString("Name");
                String email = allUsers.getString("Email");
                String password = allUsers.getString("Password");
                String role = allUsers.getString("Role");

                User user = new User(id, name, email, password, new Role(role));
                allUserList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty cachedRowSet");
        }
        return allUserList;
    }

    public Optional<User> findUserByEmail(String email) {
        LOGGER.debug("findUserByEmail with email " + email);
        String query = String.format(SELECT_USER_BY_EMAIL, email);
        User user = null;
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery(query);
            resultSet.next();

            //TODO: делать запрос по имени колонки
            Integer userId = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String userEmail = resultSet.getString(3);
            String userPassword = resultSet.getString(4);
            String roleId = resultSet.getString(5);
            Role role = roleRepository.findRoleById(Integer.parseInt(roleId));
            user = new User(userId, userName, userEmail, userPassword, role);


        } catch (SQLException e) {
            LOGGER.error("Failed to get entity from database " + e.getMessage());

            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);

    }
}


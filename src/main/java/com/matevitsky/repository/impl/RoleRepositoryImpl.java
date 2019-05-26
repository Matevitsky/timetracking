package com.matevitsky.repository.impl;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Role;
import com.matevitsky.repository.interfaces.RoleRepository;
import org.apache.log4j.Logger;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepositoryImpl implements RoleRepository {

    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE ID=%d";
    private static Logger LOGGER = Logger.getLogger(RoleRepositoryImpl.class);
    ConnectorDB connectorDB = new ConnectorDB();

    @Override
    public Role findRoleById(Integer id) {

        LOGGER.debug("Method findRoleById started, with id " + id);
        Role role = null;
        RowSet rows = null;
        String query = String.format(SELECT_ROLE_BY_ID, id);
        try (Connection connection = connectorDB.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String roleName = resultSet.getString("Name");
            role = new Role(id, roleName);

        } catch (SQLException e) {
            LOGGER.error("Failed to get Role with id " + id + " error:" + e.getMessage());
        }

        return role;
    }
}

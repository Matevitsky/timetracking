package com.matevitsky.db;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class ConnectorDB {

    private BasicDataSource ds = new BasicDataSource();


    public Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String URL = resourceBundle.getString("db.url");
        String USER = resourceBundle.getString("db.user");
        String PASSWORD = resourceBundle.getString("db.password");
        String driver = resourceBundle.getString("db.driver");
        int poolSize = Integer.parseInt(resourceBundle.getString("db.poolsize"));
        ds.setDriverClassName(driver);
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(poolSize);
        //TODO:сделать конструктор с параметром чтобы имя пропертей с которых считывать данные

        return ds.getConnection();
    }


}
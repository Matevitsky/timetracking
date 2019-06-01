package com.matevitsky.db;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class ConnectorDB {

    private BasicDataSource ds = new BasicDataSource();


    public Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String url = resourceBundle.getString("db.url");
        String user = resourceBundle.getString("db.user");
        String password = resourceBundle.getString("db.password");
        String driver = resourceBundle.getString("db.driver");
        int poolSize = Integer.parseInt(resourceBundle.getString("db.poolsize"));
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(poolSize);
        //TODO:сделать конструктор с параметром чтобы имя пропертей с которых считывать данные
        //TODO: в константы переменные

        return ds.getConnection();
    }


}
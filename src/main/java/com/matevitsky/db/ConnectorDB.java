package com.matevitsky.db;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectorDB {

    public static final String DATABASE_PROPERTIES = "/Users/Sergey/Time-tracking/src/main/resources/database.properties";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_POOLSIZE = "db.poolsize";
    private final static BasicDataSource ds = new BasicDataSource();
    private final static Logger LOGGER = Logger.getLogger(ConnectorDB.class);

    public static Connection getConnection() throws SQLException {
        FileInputStream fis = null;
        Properties p = new Properties();
        try {
            fis = new FileInputStream(DATABASE_PROPERTIES);
        } catch (FileNotFoundException e) {
            LOGGER.error("File with dataBase properties not found");

        }

        try {
            p.load(fis);
        } catch (IOException e) {
            LOGGER.error("Create Connection error " + e.getMessage());
        }

        String url = p.getProperty(DB_URL);
        String user = p.getProperty(DB_USER);
        String password = p.getProperty(DB_PASSWORD);
        String driver = p.getProperty(DB_DRIVER);
        int poolSize = Integer.parseInt(p.getProperty(DB_POOLSIZE));
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(poolSize);


        return ds.getConnection();
    }


}
package com.matevitsky;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.db.DbInitScriptRunner;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.impl.UserRepositoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        File initialFile = new File("../Time-tracking/src/main/resources/initDB.sql");
        InputStream targetStream = new FileInputStream(initialFile);
        DbInitScriptRunner.executeScript(ConnectorDB.getConnection(), targetStream);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        try {
            System.out.println("Database successfully created");
            System.out.println(userRepository.findUserByEmail("user@gmail.com").get());
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }

    //TODO: добавить поле юзер в законченные активности
}


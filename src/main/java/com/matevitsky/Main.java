package com.matevitsky;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.db.DbInitScriptRunner;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.impl.UserRepositoryImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
        File initialFile = new File("/Users/Sergey/Time-tracking/src/main/resources/initDB.sql");
        InputStream targetStream = new FileInputStream(initialFile);
        ConnectorDB coonector = new ConnectorDB();
        DbInitScriptRunner.executeScript(coonector.getConnection(), targetStream);

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        try {
            System.out.println(userRepository.findUserByEmail("user@gmail.com"));
        } catch (ErrorException e) {
            e.printStackTrace();
        }


        //TODO: сделать поле для времени
        //TODO: продумать логику на случай попытки регистрации с существующим loginEmail
        //TODO: валидация данных на входе
        //TODO: написать тесты
        //TODO: отображение времени на страницах
    }

}


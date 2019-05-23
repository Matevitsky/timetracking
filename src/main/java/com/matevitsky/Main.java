package com.matevitsky;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.db.DbInitScriptRunner;
import com.matevitsky.entity.Activity;
import com.matevitsky.repository.UserRepositoryImpl;
import com.matevitsky.service.ActivitiesService;
import com.matevitsky.service.ActivityServiceImpl;

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


        // System.out.println( userRepositoryImplDataBase.updateUser(new User(5,"Sergey1","0000")));

        //  UserService userService = new UserServiceImpl();
        //userService.insertUser(new User("SergeyM", "Sergey.m23@gmail.com", "11111", new Role("Regular")));
        //User user = new User(5, "SergeyM", "Sergey.m23@gmail.com", "11111", new Role("Regular"));
        //  System.out.println(userService.updateUser(new User(5, "Matevitsky123", "Sergey.m23@gmail.com", "11111", Role.ADMIN)));
        //System.out.println(userService.getAll());

        ActivitiesService activityService = new ActivityServiceImpl();
        Activity activity1 = new Activity(5, "Title2", "Hello World", 1, 0);
        Activity activity2 = new Activity(5, "Title3", "Hello World3", 1, 0);
        activityService.insertActivity(activity1);
        activityService.insertActivity(activity2);

        //   activityService.deleteActivity(new Activity(2, "Title2", "Hello World", 1, 0));

        //     activityService.updateActivity(new Activity(2, "Title3", "Hello World", 1, 0));
        //   System.out.println(activityService.getAll());

        //   Activity activity1 = activityService.getActivity(2).get();

        //   System.out.println(activity1);

        // System.out.println(userRepositoryImplDataBase.selectUser(3));
        // System.out.println( userRepositoryImplDataBase.deleteUser(new User(1,"Sergey","12345")));
        // System.out.println(userRepositoryImplDataBase.getAllUsers());
    }

}


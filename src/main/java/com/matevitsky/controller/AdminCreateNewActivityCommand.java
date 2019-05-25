package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminCreateNewActivityCommand implements Command {

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = (String) request.getParameter("title");
        String description = (String) request.getParameter("description");
        Activity activity = Activity.newBuilder().withTittle(title)
                .withContent(description)
                .withDuration(0).build();
        activityService.insertActivity(activity);


        return ADMIN_PAGE;
    }
}

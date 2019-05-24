package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_ACTIVITY_REQUESTS_PAGE;

public class AdminAssignActivityCommand implements Command {
    ActivityService activityService = new ActivityServiceImpl();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String activity = request.getParameter("selectedRecord");
        String userId = request.getParameter("userId");

        String substring = activity.substring(activity.lastIndexOf("id=") + 3, activity.indexOf(","));
        Integer activityId = Integer.parseInt(substring);

        Activity activityForAssign = activityService.getActivity(activityId).get();

        Activity assignedActivity = Activity.newBuilder().withId(activityForAssign.getId())
                .withContent(activityForAssign.getContent())
                .withDuration(activityForAssign.getDuration())
                .withTittle(activityForAssign.getTitle())
                .withUserId(activityForAssign.getUserId()).build();
        activityService.updateActivity(assignedActivity);


        return ADMIN_ACTIVITY_REQUESTS_PAGE;
    }
}

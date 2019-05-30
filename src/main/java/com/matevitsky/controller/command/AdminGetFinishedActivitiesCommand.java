package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_GET_FINISHED_ACTIVITIES;

public class AdminGetFinishedActivitiesCommand implements Command {
    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.DONE.name());


        //  Integer userId2 = (Integer) request.getSession().getAttribute("userId");
        //   request.getSession().setAttribute("userId", userId2);
        request.setAttribute("activityList", finishedActivityList);

        return ADMIN_GET_FINISHED_ACTIVITIES;

    }
}

package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.impl.ActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_GET_FINISHED_ACTIVITIES;

public class AdminRemoveActivityCommand implements Command {


    //TODO:  вставить логер

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String activityId = request.getParameter("id");

        activityService.deleteActivity(Integer.parseInt(activityId));


        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.DONE.name());

        Integer userId2 = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userId", userId2);
        request.setAttribute("activityList", finishedActivityList);


        return ADMIN_GET_FINISHED_ACTIVITIES;
    }
}

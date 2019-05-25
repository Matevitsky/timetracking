package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserActivityRemoveRequestCommand implements Command {

    //TODO: вставить логгер во всех контроллерах
    // RemoveActivityRequestService removeActivityRequestService = new RemoveActivityRequestServiceImpl();

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer activityId = Integer.parseInt(request.getParameter("id"));
        Object userIdObject = request.getSession().getAttribute("userId");
        Integer userId = Integer.parseInt(String.valueOf(userIdObject));
        Integer duration = Integer.parseInt(request.getParameter("duration"));

        //TODO: разобраться с Optional
        Activity activity = activityService.getActivity(activityId).get();
        Activity doneActivity = Activity.newBuilder()
                .withId(activityId)
                .withStatus(Activity.Status.DONE)
                .withDescription(activity.getDescription())
                .withUserId(1)
                .withDuration(duration)
                .withTittle(activity.getTitle()).build();

        activityService.updateActivity(doneActivity);

        List<Activity> activityListByUserId = activityService.getActivityListByUserId(userId);
        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", activityListByUserId);
        request.setAttribute("userId", userId);


        return USER_PAGE;

    }
}

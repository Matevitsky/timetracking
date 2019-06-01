package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminMainPageCommand implements Command {

    private final ActivityService activityService;

    public AdminMainPageCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());

        request.setAttribute("activityList", finishedActivityList);

        return ADMIN_PAGE;

    }
}

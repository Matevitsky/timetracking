package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_GET_FINISHED_ACTIVITIES;

public class AdminGetFinishedActivitiesCommand implements Command {

    private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(AdminGetFinishedActivitiesCommand.class);

    public AdminGetFinishedActivitiesCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.DONE.name());

        request.setAttribute("activityList", finishedActivityList);

        return ADMIN_GET_FINISHED_ACTIVITIES;

    }
}

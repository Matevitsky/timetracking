package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserRequestActivityCommand implements Command {

    private final ActivityService activityService;
    private final ActivityRequestService activityRequestService;
    private static final Logger LOGGER = Logger.getLogger(UserRequestActivityCommand.class);

    public UserRequestActivityCommand(ActivityService activityService, ActivityRequestService activityRequestService) {
        this.activityService = activityService;
        this.activityRequestService = activityRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        try {
            activityRequestService.createActivityRequest(userId);
            request.setAttribute("alert", "activity successfully added");
        } catch (ErrorException e) {
            LOGGER.warn(e.getMessage());
        }

        List<Activity> activityListByUserId = activityService.getAssignedActivityList(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", activityListByUserId);
        request.setAttribute("userId", userId);

        return USER_PAGE;
    }
}

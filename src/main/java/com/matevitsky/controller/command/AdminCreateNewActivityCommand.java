package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.ActivityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminCreateNewActivityCommand implements Command {

    private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(AdminCreateNewActivityCommand.class);

    public AdminCreateNewActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String title = request.getParameter("title");
        String description = request.getParameter("description");


        Activity activity = Activity.newBuilder().withTitle(title)
                .withDescription(description)
                .withDuration(0).build();
        try {
            activityService.createActivity(activity);
        } catch (ErrorException e) {
            request.setAttribute("error", new ErrorException("Failed create activity"));
        }
        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
        request.setAttribute("activityList", unAssignedActivityList);

        return ADMIN_PAGE;


    }
}



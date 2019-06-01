package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminCreateNewActivityCommand implements Command {

    private final ActivityService activityService;

    public AdminCreateNewActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String title = (String) request.getParameter("title");
        String description = (String) request.getParameter("description");
        Activity activity = Activity.newBuilder().withTitle(title)
                .withDescription(description)
                .withDuration(0).build();
        activityService.insertActivity(activity);

        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
        request.setAttribute("activityList", unAssignedActivityList);

        return ADMIN_PAGE;
    }
}

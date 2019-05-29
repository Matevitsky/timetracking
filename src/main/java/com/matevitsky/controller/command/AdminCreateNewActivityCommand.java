package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminCreateNewActivityCommand implements Command {

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = (String) request.getParameter("title");
        String description = (String) request.getParameter("description");
        Activity activity = Activity.newBuilder().withTittle(title)
                .withDescription(description)
                .withDuration(0).build();
        activityService.insertActivity(activity);

        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
        request.setAttribute("activityList", unAssignedActivityList);

        return ADMIN_PAGE;
    }
}

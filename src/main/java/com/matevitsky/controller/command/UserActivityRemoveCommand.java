package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.util.TimeParser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserActivityRemoveCommand implements Command {

    private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(UserActivityRemoveCommand.class);

    public UserActivityRemoveCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String activityId = request.getParameter("id");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String time = request.getParameter("duration");


        Optional<Activity> activity = activityService.getActivity(Integer.parseInt(activityId));

        if (activity.isPresent()) {
            Activity activityWithDuration = Activity.newBuilder()
                    .withId(activity.get().getId())
                    .withTitle(activity.get().getTitle())
                    .withDescription(activity.get().getDescription())
                    .withDuration(TimeParser.parseStringTimeToInteger(time))
                    .withUserId(userId)
                    .withStatus(Activity.Status.DONE)
                    .build();
            activityService.updateActivity(activityWithDuration);
        }


        List<Activity> assignedActivityList = activityService.getAssignedActivityList(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", assignedActivityList);
        request.setAttribute("userId", userId);
        return USER_PAGE;
    }
}

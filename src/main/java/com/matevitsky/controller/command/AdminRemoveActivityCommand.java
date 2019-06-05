package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
import com.matevitsky.entity.dto.FinishedActivity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_GET_FINISHED_ACTIVITIES;

public class AdminRemoveActivityCommand implements Command {

    private final ActivityService activityService;
    private final UserService userService;
    private static final Logger LOGGER = Logger.getLogger(AdminRemoveActivityCommand.class);

    public AdminRemoveActivityCommand(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String activityId = request.getParameter("id");

        activityService.deleteActivity(Integer.parseInt(activityId));

        List<Activity> activityByStatusDone = activityService.getAllActivityByStatus(Activity.Status.DONE.name());
        List<FinishedActivity> finishedActivityList = new ArrayList<>();
        FinishedActivity finishedActivity;

        for (Activity a : activityByStatusDone) {
            Optional<User> userById = userService.getUserById(a.getUserId());
            String userName = userById.isPresent() ? userById.get().getName() : "";
            finishedActivity =
                    new FinishedActivity(userName,
                            a.getId()
                            , a.getTitle()
                            , a.getDescription()
                            , a.getDuration()
                            , a.getUserId()
                            , a.getStatus());
            finishedActivityList.add(finishedActivity);
        }

        request.setAttribute("activityList", finishedActivityList);

        Integer userId2 = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("userId", userId2);
        request.setAttribute("activityList", finishedActivityList);

        return ADMIN_GET_FINISHED_ACTIVITIES;
    }
}

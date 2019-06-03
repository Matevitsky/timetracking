package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminMainPageCommand implements Command {

    private final ActivityService activityService;
    private final static Logger LOGGER = Logger.getLogger(AdminMainPageCommand.class);


    public AdminMainPageCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());

        request.setAttribute("activityList", finishedActivityList);

        return ADMIN_PAGE;

    }
}

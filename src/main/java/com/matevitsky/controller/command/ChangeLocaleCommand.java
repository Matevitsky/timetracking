package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class ChangeLocaleCommand implements Command {

    private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    public ChangeLocaleCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);

        String role = (String) request.getSession().getAttribute("role");
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (role.equals("Admin")) {
            return new AdminMainPageCommand(activityService).execute(request, response);
        } else {
            List<Activity> assignedActivityList = activityService.getAssignedActivityList(userId);
            request.setAttribute("activityList", assignedActivityList);
            return USER_PAGE;
        }
    }
}

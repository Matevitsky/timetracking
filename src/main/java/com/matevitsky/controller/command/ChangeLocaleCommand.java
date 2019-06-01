package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    private final ActivityService activityService;

    public ChangeLocaleCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);
        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());

        request.setAttribute("activityList", finishedActivityList);


        return request.getParameter("uri");
    }
}

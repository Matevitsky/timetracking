package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ResourceManager;
import com.matevitsky.service.impl.ActivityServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);

    ActivityService activityService = new ActivityServiceImpl();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        LOGGER.info("Locale: " + locale);
        List<Activity> finishedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());


        //  Integer userId2 = (Integer) request.getSession().getAttribute("userId");
        //   request.getSession().setAttribute("userId", userId2);
        request.setAttribute("activityList", finishedActivityList);


        return request.getParameter("uri");
    }
}

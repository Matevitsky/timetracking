package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivitiyService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserActivityRemoveCommand implements Command {

    ActivitiyService activitiyService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityId = request.getParameter("id");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        activitiyService.deleteActivity(Integer.parseInt(activityId));

        List<Activity> activityListByUserId = activitiyService.getActivityListByUserId(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", activityListByUserId);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("jsp/userPage.jsp").forward(request, response);
        return USER_PAGE;
    }
}

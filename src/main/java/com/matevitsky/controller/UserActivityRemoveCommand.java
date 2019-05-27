package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserActivityRemoveCommand implements Command {

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityId = request.getParameter("id");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        activityService.changeActivityStatus(Integer.parseInt(activityId), Activity.Status.DONE.name());

        List<Activity> assignedActivityList = activityService.getAssignedActivityList(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", assignedActivityList);
        request.setAttribute("userId", userId);
        //   request.getRequestDispatcher("jsp/userPage.jsp").forward(request, response);
        return USER_PAGE;
    }
}

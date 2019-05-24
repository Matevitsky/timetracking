package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityRequestServiceImpl;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserRequestActivityCommand implements Command {


    ActivityRequestService activityRequestService = new ActivityRequestServiceImpl();

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer userId = (Integer) request.getSession().getAttribute("userId");
        activityRequestService.createRequest(userId);

        List<Activity> activityListByUserId = activityService.getActivityListByUserId(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", activityListByUserId);
        request.setAttribute("userId", userId);
        //   request.getRequestDispatcher("jsp/userPage.jsp").forward(request, response);

        return USER_PAGE;
    }
}

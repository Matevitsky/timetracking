package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.AddActivityRequestServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;
import com.matevitsky.service.interfaces.AddActivityRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class UserRequestActivityCommand implements Command {


    AddActivityRequestService addActivityRequestService = new AddActivityRequestServiceImpl();

    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer userId = (Integer) request.getSession().getAttribute("userId");
        addActivityRequestService.createAddActivityRequest(userId);

        List<Activity> activityListByUserId = activityService.getAssignedActivityList(userId);

        request.getSession().setAttribute("userId", userId);
        request.setAttribute("activityList", activityListByUserId);
        request.setAttribute("userId", userId);
        //   request.getRequestDispatcher("jsp/userPage.jsp").forward(request, response);

        return USER_PAGE;
    }
}

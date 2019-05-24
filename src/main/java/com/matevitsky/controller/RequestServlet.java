package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivitiyService;
import com.matevitsky.service.ActivityRequestService;
import com.matevitsky.service.ActivityRequestServiceImpl;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/requestServlet")
public class RequestServlet extends HttpServlet {

    ActivityRequestService activityRequestService = new ActivityRequestServiceImpl();

    ActivitiyService activitiyService = new ActivityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = (Integer) req.getSession().getAttribute("userId");
        activityRequestService.createRequest(userId);

        List<Activity> activityListByUserId = activitiyService.getActivityListByUserId(userId);

        req.getSession().setAttribute("userId", userId);
        req.setAttribute("activityList", activityListByUserId);
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("jsp/userPage.jsp").forward(req, resp);


    }
}

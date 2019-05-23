package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.service.ActivitiesService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    ActivitiesService activitiesService = new ActivityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activityId = req.getParameter("id");
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        activitiesService.deleteActivity(Integer.parseInt(activityId));

        List<Activity> activityListByUserId = activitiesService.getActivityListByUserId(userId);

        req.getSession().setAttribute("userId", userId);
        req.setAttribute("activityList", activityListByUserId);
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("jsp/userPage.jsp").forward(req, resp);

    }
}

package com.matevitsky.controller;

import com.matevitsky.service.ActivitiesService;
import com.matevitsky.service.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    ActivitiesService activitiesService = new ActivityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activityId = req.getParameter("id");
        activitiesService.deleteActivity(Integer.parseInt(activityId));
    }
}

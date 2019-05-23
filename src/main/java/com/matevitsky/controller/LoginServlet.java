package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
import com.matevitsky.service.ActivitiesService;
import com.matevitsky.service.ActivityServiceImpl;
import com.matevitsky.service.UserService;
import com.matevitsky.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("")
public class LoginServlet extends HttpServlet {


    private static Logger LOGGER = Logger.getLogger(LoginServlet.class);
    UserService userService = new UserServiceImpl();
    ActivitiesService activitiesService = new ActivityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");

        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> userByEmail = userService.findUserByEmail(email);

        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            if (user.getRole().getName().equals("Admin")) {
                req.getSession().setAttribute("userId", user.getId());
                req.setAttribute("userId", user.getId());
                req.getRequestDispatcher("jsp/adminPage.jsp").forward(req, resp);

            } else {

                List<Activity> activityListByUserId = activitiesService.getActivityListByUserId(user.getId());
                req.getSession().setAttribute("userId", user.getId());
                req.setAttribute("activityList", activityListByUserId);
                req.setAttribute("userId", user.getId());
                req.getRequestDispatcher("jsp/userPage.jsp").forward(req, resp);
            }


        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
            requestDispatcher.forward(req, resp);

            // resp.sendRedirect("jsp/login.jsp");
        }

        //:TODO сделать страницу
    }
}
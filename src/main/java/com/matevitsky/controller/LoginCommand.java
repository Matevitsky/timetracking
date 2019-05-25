package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.ActivityServiceImpl;
import com.matevitsky.service.UserService;
import com.matevitsky.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.*;

public class LoginCommand implements Command {
    UserService userService = new UserServiceImpl();
    ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> userByEmail = userService.findUserByEmail(email);

        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            if (user.getRole().getName().equals("Admin")) {
                request.getSession().setAttribute("userId", user.getId());
                request.setAttribute("userId", user.getId());
                // request.getRequestDispatcher("jsp/adminPage.jsp").forward(request, response);
                return ADMIN_PAGE;

            } else {
                List<Activity> activityListByUserId = activityService.getActivityListByUserId(user.getId());
                request.getSession().setAttribute("userId", user.getId());
                request.setAttribute("activityList", activityListByUserId);
                request.setAttribute("userId", user.getId());
                return USER_PAGE;
            }
        }
        return LOGIN_PAGE;

    }
}

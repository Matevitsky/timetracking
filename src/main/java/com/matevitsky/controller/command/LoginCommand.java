package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import com.matevitsky.util.MD5Util;
import com.matevitsky.util.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.*;

public class LoginCommand implements Command {

    private final UserService userService;
    private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    public LoginCommand(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Method execute started");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        if (!Validation.emailValidation(email) || password.isEmpty()) {
            request.setAttribute("error", "password is empty");
            LOGGER.info("Login Validation Failed");
            return LOGIN_PAGE;
        }
        try {
            user = userService.findUserByEmail(email);
            String encryptedPassword = MD5Util.encryptPassword(password);
            if (!user.getPassword().equals(encryptedPassword)) {
                LOGGER.info("Wrong password");
                request.setAttribute("error", "wrong password");
                return LOGIN_PAGE;
            }

        } catch (ErrorException e) {
            LOGGER.info("User Not Exist");
            request.setAttribute("error", e.getMessage());
            return LOGIN_PAGE;
        }


        return user.getRole().getName().equals("Admin") ? adminPage(request, user) : userPage(request, user);
    }


    private String adminPage(HttpServletRequest request, User user) {
        LOGGER.debug("Method adminPage started");

        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
        request.setAttribute("activityList", unAssignedActivityList);
        request.getSession().setAttribute("role", user.getRole().getName());
        request.getSession().setAttribute("userId", user.getId());
        request.setAttribute("userId", user.getId());
        return ADMIN_PAGE;
    }

    private String userPage(HttpServletRequest request, User user) {
        LOGGER.debug("Method userPage started");

        List<Activity> assignedActivityList = activityService.getAssignedActivityList(user.getId());
        request.getSession().setAttribute("userId", user.getId());
        request.setAttribute("activityList", assignedActivityList);
        request.getSession().setAttribute("role", user.getRole().getName());
        request.setAttribute("userId", user.getId());
        return USER_PAGE;
    }
}



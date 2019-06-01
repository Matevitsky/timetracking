package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
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
    private Logger LOGGER = Logger.getLogger(LoginCommand.class);

    public LoginCommand(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User userByEmail = userService.findUserByEmail(email);

        if (Validation.emailValidation(email) && password != null) {
            if (userByEmail != null) {
                User user = userByEmail;
                String encryptedPassword = MD5Util.encryptPassword(password);
                if (encryptedPassword.equals(user.getPassword())) {
                    if (user.getRole().getName().equals("Admin")) {
                        return adminPage(request, user);

                    } else {
                        return userPage(request, user);
                    }
                } else {
                    LOGGER.info("The password not match to user " + user.getId());

                }
            }
            return LOGIN_PAGE;
        } else {
            return LOGIN_PAGE;
        }

    }


    private String adminPage(HttpServletRequest request, User user) {

        List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
        request.setAttribute("activityList", unAssignedActivityList);
        request.getSession().setAttribute("role", user.getRole().getName());
        request.getSession().setAttribute("userId", user.getId());
        request.setAttribute("userId", user.getId());
        return ADMIN_PAGE;
    }

    private String userPage(HttpServletRequest request, User user) {
        List<Activity> assignedActivityList = activityService.getAssignedActivityList(user.getId());
        request.getSession().setAttribute("userId", user.getId());
        request.setAttribute("activityList", assignedActivityList);
        request.getSession().setAttribute("role", user.getRole().getName());
        request.setAttribute("userId", user.getId());
        return USER_PAGE;
    }
}



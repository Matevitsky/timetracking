package com.matevitsky.controller.command;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.User;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.UserServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;
import com.matevitsky.service.interfaces.UserService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

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
    private Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> userByEmail = userService.findUserByEmail(email);
// TODO: проверять что данные не пустые а валидитровать

        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            String encryptedPassword = MD5Util.encryptPassword(password);
            if (encryptedPassword.equals(user.getPassword())) {
                if (user.getRole().getName().equals("Admin")) {
                    List<Activity> unAssignedActivityList = activityService.getAllActivityByStatus(Activity.Status.NEW.name());
                    request.setAttribute("activityList", unAssignedActivityList);
                    request.getSession().setAttribute("role", user.getRole().getName());
                    request.getSession().setAttribute("userId", user.getId());
                    request.setAttribute("userId", user.getId());
                    return ADMIN_PAGE;

                } else {
                    List<Activity> assignedActivityList = activityService.getAssignedActivityList(user.getId());
                    request.getSession().setAttribute("userId", user.getId());
                    request.setAttribute("activityList", assignedActivityList);
                    request.getSession().setAttribute("role", user.getRole().getName());
                    request.setAttribute("userId", user.getId());
                    return USER_PAGE;
                }
            } else {
                LOGGER.info("The password not match to user " + user.getId());
            }
        }
        return LOGIN_PAGE;

    }
}

package com.matevitsky.controller;

import com.matevitsky.entity.Activity;
import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import com.matevitsky.service.impl.ActivityServiceImpl;
import com.matevitsky.service.impl.UserServiceImpl;
import com.matevitsky.service.interfaces.ActivityService;
import com.matevitsky.service.interfaces.UserService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;
import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class RegisterCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private UserService userService = new UserServiceImpl();
    private ActivityService activityService = new ActivityServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Registration Started");
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        if (!password.equals(confirmPassword)) {
            LOGGER.debug("Confirmation password failed");
            //TODO: create exception and throw it
            return LOGIN_PAGE;

        } else {

            String encryptedPass = MD5Util.encryptPassword(password);

            User user = User.newBuilder()
                    .withName(name)
                    .withEmail(email)
                    .withRole(new Role(1))
                    .withPassword(encryptedPass).build();
            if (userService.insertUser(user)) {
                Optional<User> userByEmail = userService.findUserByEmail(email);
                if (userByEmail.isPresent()) {
                    List<Activity> activityListByUserId = activityService.getActivityListByUserId(user.getId());
                    Integer userId = userByEmail.get().getId();
                    request.getSession().setAttribute("userId", userId);
                    request.setAttribute("activityList", activityListByUserId);
                    request.setAttribute("userId", userId);
                    return USER_PAGE;
                }
                //TODO: сделать страницу для ошибки
                throw new UnsupportedOperationException();
            } else {
                return LOGIN_PAGE;

            }
        }
    }
}
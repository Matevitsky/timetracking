package com.matevitsky.controller.command;

import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import com.matevitsky.service.UserService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;
import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class RegisterCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Registration Started");
        String name = request.getParameter("username");
        String email = request.getParameter("emailRegistration");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        //TODO: провреять что все поля заполнены

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
                User userByEmail = userService.findUserByEmail(email);

                if (userByEmail != null) {

                    Integer userId = userByEmail.getId();
                    request.getSession().setAttribute("userId", userId);

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
package com.matevitsky.controller.command;

import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.UserService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;
import static com.matevitsky.controller.constant.PageConstant.USER_PAGE;

public class RegisterCommand implements Command {

    private final UserService userService;

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

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

        if (fieldsAreNotFilled(name, email, password, confirmPassword)) {
            ErrorException exception = new ErrorException("Fields are not filled correctly");
            return registrationFailed(request, exception);

        }

        String encryptedPass = MD5Util.encryptPassword(password);
        User user = User.newBuilder()
                .withName(name)
                .withEmail(email)
                .withRole(new Role(1))
                .withPassword(encryptedPass).build();

        try {

            if (userService.createUser(user)) {

                try {

                    User userByEmail = userService.findUserByEmail(email);
                    Integer userId = userByEmail.getId();
                        request.getSession().setAttribute("userId", userId);
                        request.getSession().setAttribute("role", "User");
                        request.setAttribute("userId", userId);
                        return USER_PAGE;

                } catch (ErrorException e) {
                    LOGGER.error("User Not found after registration");

                }
            }
        } catch (ErrorException e) {
            registrationFailed(request, new ErrorException("This email is already registered"));
        }
        return LOGIN_PAGE;
    }

    private boolean fieldsAreNotFilled(String name, String email, String password, String confirmPassword) {
        return (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                || !password.equals(confirmPassword));
    }

    private String registrationFailed(HttpServletRequest request, ErrorException e) {
        LOGGER.debug("The passwords are not the same");
        request.setAttribute("error", e.getMessage());
        return LOGIN_PAGE;
    }
}

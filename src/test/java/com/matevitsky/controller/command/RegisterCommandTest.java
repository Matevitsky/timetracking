package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @Mock
    private User user;


    @Test
    public void shouldReturnUserPage() throws ErrorException {

        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("emailRegistration")).thenReturn("user@gmail.com");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getParameter("confirm-password")).thenReturn("user");

        when(request.getSession()).thenReturn(session);
        when(userService.createUser(any())).thenReturn(true);
        when(userService.findUserByEmail("user@gmail.com")).thenReturn(user);


        RegisterCommand registerCommand = new RegisterCommand(userService);

        String actual = registerCommand.execute(request, response);

        assertEquals(PageConstant.USER_PAGE, actual);


    }

    @Test
    public void shouldReturnLoginPage() throws ErrorException {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("emailRegistration")).thenReturn("admin@gmail.com");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getParameter("confirm-password")).thenReturn("admin");

        when(request.getSession()).thenReturn(session);
        when(userService.createUser(any())).thenReturn(false);
        RegisterCommand registerCommand = new RegisterCommand(userService);

        String actual = registerCommand.execute(request, response);
        assertEquals(PageConstant.LOGIN_PAGE, actual);

    }
}

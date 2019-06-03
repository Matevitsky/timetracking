package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class RegisterCommandTest {

    @Mock
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private User user;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);
        user = mock(User.class);
    }

    @Test
    public void shouldReturnUserPage() throws ErrorException {

        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("emailRegistration")).thenReturn("user@gmail.com");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getParameter("confirm-password")).thenReturn("user");

        when(request.getSession()).thenReturn(session);
        when(userService.insertUser(any())).thenReturn(true);
        when(userService.findUserByEmail("user@gmail.com")).thenReturn(Optional.ofNullable(user));


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
        when(userService.insertUser(any())).thenReturn(false);
        RegisterCommand registerCommand = new RegisterCommand(userService);

        String actual = registerCommand.execute(request, response);
        assertEquals(PageConstant.LOGIN_PAGE, actual);

    }
}

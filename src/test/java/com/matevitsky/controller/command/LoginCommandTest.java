package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.entity.User;
import com.matevitsky.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(JUnit4.class)
public class LoginCommandTest {

    @Mock
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;


    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(UserService.class);


    }

    @Test
    public void shouldReturnLoginPage() {

        when(request.getParameter("email")).thenReturn("");

        LoginCommand loginCommand = new LoginCommand();
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.LOGIN_PAGE;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAdminPage() {
        when(request.getParameter("email")).thenReturn("admin@gmail.com");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        User user = User.newBuilder().withEmail("admin@gmail.com").build();
        when(userService.findUserByEmail("admin@gmail.com")).thenReturn(user);


        LoginCommand loginCommand = new LoginCommand();
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.ADMIN_PAGE;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnUserPage() {
        when(request.getParameter("email")).thenReturn("user@gmail.com");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getSession()).thenReturn(session);
        User user = User.newBuilder().withEmail("user@gmail.com").build();
        when(userService.findUserByEmail("user@gmail.com")).thenReturn(user);

        LoginCommand loginCommand = new LoginCommand();
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.USER_PAGE;
        assertEquals(expected, actual);
    }
}
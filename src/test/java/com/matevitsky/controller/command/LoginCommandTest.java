package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.service.ActivityService;
import com.matevitsky.service.UserService;
import com.matevitsky.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MD5Util.class)
public class LoginCommandTest {

    @Mock
    private UserService userService;
    @Mock
    private ActivityService activityService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;



    @Test
    public void shouldReturnLoginPage() {

        when(request.getParameter("email")).thenReturn("");
        LoginCommand loginCommand = new LoginCommand(userService, activityService);
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.LOGIN_PAGE;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAdminPage() throws ErrorException {
        PowerMockito.mockStatic(MD5Util.class);
        when(request.getParameter("email")).thenReturn("admin@gmail.com");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        User user = User.newBuilder().withEmail("admin@gmail.com").withPassword("admin").withRole(new Role(1, "Admin")).build();
        when(userService.findUserByEmail("admin@gmail.com")).thenReturn(user);
        PowerMockito.when(MD5Util.encryptPassword("admin")).thenReturn("admin");

        LoginCommand loginCommand = new LoginCommand(userService, activityService);
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.ADMIN_PAGE;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnUserPage() throws ErrorException {
        PowerMockito.mockStatic(MD5Util.class);
        when(request.getParameter("email")).thenReturn("user@gmail.com");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getSession()).thenReturn(session);
        User user = User.newBuilder().withEmail("user@gmail.com").withPassword("user").withRole(new Role(2, "user")).build();
        when(userService.findUserByEmail("user@gmail.com")).thenReturn(user);
        PowerMockito.when(MD5Util.encryptPassword("user")).thenReturn("user");

        LoginCommand loginCommand = new LoginCommand(userService, activityService);
        String actual = loginCommand.execute(request, response);

        String expected = PageConstant.USER_PAGE;
        assertEquals(expected, actual);
    }
}

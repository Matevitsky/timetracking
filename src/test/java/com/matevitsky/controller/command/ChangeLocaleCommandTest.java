package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLocaleCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ActivityService activityService;



    @Test
    public void shouldReturnUserPage() {

        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("role")).thenReturn("User");
        when(request.getParameter("locale")).thenReturn("en");

        String actual = new ChangeLocaleCommand(activityService).execute(request, response);

        assertEquals(PageConstant.USER_PAGE, actual);

    }

    @Test
    public void shouldReturnAdminPage() {

        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("role")).thenReturn("Admin");
        when(request.getParameter("locale")).thenReturn("en");

        String actual = new ChangeLocaleCommand(activityService).execute(request, response);

        assertEquals(PageConstant.ADMIN_PAGE, actual);

    }
}

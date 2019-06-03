package com.matevitsky.controller.command;

import com.matevitsky.controller.constant.PageConstant;
import com.matevitsky.service.ActivityService;
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
public class ChangeLocaleCommandTest {

    @Mock
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ActivityService activityService;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        activityService = mock(ActivityService.class);


    }

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

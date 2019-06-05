package com.matevitsky.controller.filter;

import com.matevitsky.controller.constant.PageConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleFilterTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnLoginPage() throws IOException, ServletException {
        when(request.getParameter("command")).thenReturn("test");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("role")).thenReturn("User");
        RoleFilter roleFilter = new RoleFilter();
        roleFilter.doFilter(request, response, chain);
        verify(response).sendRedirect(PageConstant.LOGIN_PAGE);

    }

    @Test
    public void adminRoleFilter() throws IOException, ServletException {
        when(request.getParameter("command")).thenReturn("adminActivityRequest");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("role")).thenReturn("User");
        RoleFilter roleFilter = new RoleFilter();
        roleFilter.doFilter(request, response, chain);
        verify(response).sendRedirect(PageConstant.LOGIN_PAGE);

    }
}
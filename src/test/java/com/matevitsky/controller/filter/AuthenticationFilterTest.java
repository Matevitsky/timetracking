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
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;


    @Test
    public void shouldReturnLoginPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(request, response,
                filterChain);
        verify(response).sendRedirect(PageConstant.LOGIN_PAGE);
    }

    @Test
    public void shouldReturnErrorPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/asdf");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(request, response,
                filterChain);
        verify(response).sendRedirect(PageConstant.ERROR_PAGE);
    }

}




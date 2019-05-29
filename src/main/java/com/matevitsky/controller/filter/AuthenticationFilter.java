package com.matevitsky.controller.filter;


import com.matevitsky.controller.constant.PageConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = "/")
public class AuthenticationFilter implements Filter {

    private final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/", "jsp/login.jsp", "jsp/logout.jsp", "jsp/register.jsp")));

    private final Set<String> ALLOWED_COMMAND = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("LOGOUT", "LOGIN", "REGISTER")));


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        if (request.getAttribute("role") == null) {

            response.sendRedirect(PageConstant.LOGIN_PAGE);
        } else {
            filterChain.doFilter(request, response);
        }

    }

}


//TODO:
//encrypt password можно и валидировать здесь
// посмотреть последовательность отработки фильтров через анотации






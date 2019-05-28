package com.matevitsky.controller.filter;


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
        urlPatterns = {"/",
                "/jsp/adminActivityRequestPage.jsp",
                "/jsp/adminActivityFinishedPage.jsp",
                "/jsp/adminHeader.jsp",
                "/jsp/adminPage.jsp",
                "/jsp/error.jsp",
                "/jsp/userHeader.jsp",
                "/jsp/userPage.jsp",
                "/jsp/loginPage.jsp"})
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


        filterChain.doFilter(request, response);


    }

}





      /*  if (userId == 0 && !uri.endsWith("/jsp/login.jsp") && !uri.endsWith("/jsp/register.jsp")) {

            request.getRequestDispatcher(PageConstant.LOGIN_PAGE).forward(request,response);
        } else {
            if (userId != 0 && (uri.endsWith("/jsp/loginPage.jsp"))) {
                response.sendRedirect(PageConstant.USER_ACTIVITY);
            }

        filterChain.doFilter(request, response);*/


//TODO:
//encrypt password можно и валидировать здесь
// посмотреть последовательность отработки фильтров через анотации






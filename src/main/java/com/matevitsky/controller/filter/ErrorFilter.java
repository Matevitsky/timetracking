package com.matevitsky.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


//@WebFilter(filterName = "ErrorFilter")
public class ErrorFilter implements Filter {

    private Logger LOGGER = Logger.getLogger(ErrorFilter.class);
    private final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
                    "/app"
                    /*"/jsp/loginPage.jsp",
                    "/css/error.css",
                    "/jsp/loginPage.jsp",
                    "/js/loginEmailValidation.js",
                    "/js/registrationEmailValidation.js",
                    "/jsp/error.jsp",
                    "/js",
                    "/css"*/)));


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        LOGGER.debug(uri);

       /* if (uri.equals("/")) {
            response.sendRedirect(PageConstant.LOGIN_PAGE);
        } else if(!ALLOWED_PATHS.contains(uri)){
            response.sendRedirect(PageConstant.ERROR_PAGE);
        }*/
    }
}


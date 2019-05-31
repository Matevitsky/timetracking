package com.matevitsky.controller.filter;


import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);
    private final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
                    "/app")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        LOGGER.debug(uri);


        if (uri.equals("/")) {
            response.sendRedirect(PageConstant.LOGIN_PAGE);

        } else {
            if (ALLOWED_PATHS.contains(uri)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(PageConstant.ERROR_PAGE);
            }
        }

    }
}


//TODO:
//encrypt password можно и валидировать здесь
// посмотреть последовательность отработки фильтров через анотации






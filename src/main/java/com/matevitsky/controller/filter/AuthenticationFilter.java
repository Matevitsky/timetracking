package com.matevitsky.controller.filter;


import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = "/")
public class AuthenticationFilter implements Filter {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        LOGGER.debug(uri);


        if (request.getAttribute("role") == null) {
            if (request.getAttribute("command") == "error") {
                response.sendRedirect(PageConstant.ERROR_PAGE);
            } else {
                response.sendRedirect(PageConstant.LOGIN_PAGE);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}


//TODO:
//encrypt password можно и валидировать здесь
// посмотреть последовательность отработки фильтров через анотации






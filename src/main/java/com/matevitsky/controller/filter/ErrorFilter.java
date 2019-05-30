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
import java.util.stream.Stream;


@WebFilter(filterName = "ErrorFilter", urlPatterns = "/*")
public class ErrorFilter implements Filter {
    private final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
                    "/",
                    "/app",
                    "/jsp/loginPage.jsp",
                    "/css/error.css",
                    "/jsp/loginPage.jsp",
                    "/js/loginEmailValidation.js",
                    "/js/registrationEmailValidation.js",
                    "/jsp/error.jsp",
                    "/js",
                    "/css")));
    private Logger LOGGER = Logger.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        LOGGER.debug(uri);

        String[] allowPath = {"/jsp", "/css", "/js"};

        if (uri.equals("/") || uri.equals("/app")) {
            chain.doFilter(request, response);
        } else {
            if (Stream.of(allowPath)
                    .anyMatch(uri::startsWith)) {
                chain.doFilter(request, response);
            } else {
                //request.setAttribute("command", "error");
                //request.getRequestDispatcher("/app").forward(request, response);
                response.sendRedirect(PageConstant.ERROR_PAGE);
            }
        }
    }
}

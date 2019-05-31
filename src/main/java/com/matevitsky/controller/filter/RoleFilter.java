package com.matevitsky.controller.filter;

import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "RoleFilter")
public class RoleFilter implements Filter {

    private final static Logger LOGGER = Logger.getLogger(RoleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter("command");
        String uri = req.getRequestURI();
        LOGGER.debug(uri);


        String role = (String) req.getSession().getAttribute("role");

        if (!command.equals("login")) {
            if (role.equals("User")) {
                if (command.contains("user") || command.contains("change_locale") || command.contains("logout")) {
                    chain.doFilter(req, response);
                } else {
                    resp.sendRedirect(PageConstant.LOGIN_PAGE);
                }

            } else if (role.equals("Admin")) {
                if (command.contains("admin") || command.contains("logout") || command.contains("change_locale")) {
                    chain.doFilter(req, response);
                } else {
                    resp.sendRedirect(PageConstant.LOGIN_PAGE);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}


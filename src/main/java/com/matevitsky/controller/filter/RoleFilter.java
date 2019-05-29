package com.matevitsky.controller.filter;

import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/app")
public class RoleFilter implements Filter {

    private Logger LOGGER = Logger.getLogger(RoleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter("command");
        LOGGER.info("Requested resource:: " + command);


        String role = (String) req.getSession().getAttribute("role");

        if (role != null) {
            if (role.equals("User")) {
                if (command.contains("user")) {
                    chain.doFilter(req, response);
                } else {
                    resp.sendRedirect(PageConstant.LOGIN_PAGE);
                }

            } else if (role.equals("Admin")) {
                if (command.contains("admin") || command.contains("logout")) {
                    chain.doFilter(req, response);
                } else {
                    resp.sendRedirect(PageConstant.LOGIN_PAGE);
                }
            }
        } else {
            if (command.contains("register") || command.contains("login")) {
                chain.doFilter(req, response);
            } else {
                resp.sendRedirect(PageConstant.LOGIN_PAGE);
            }
        }
    }
}

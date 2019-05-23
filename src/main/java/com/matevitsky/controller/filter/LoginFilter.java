package com.matevitsky.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter("")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String email = servletRequest.getParameter("email");

        if (email != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("jsp/login.jsp");

            requestDispatcher.forward(servletRequest, servletResponse);

        }
        //encrypt password можно и валидировать здесь
        // посмотреть последовательность отработки фильтров через анотации


    }
}

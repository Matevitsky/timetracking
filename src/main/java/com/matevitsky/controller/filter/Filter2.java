package com.matevitsky.controller.filter;

/*
public class Filter2 implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();

        if ((Boolean) servletRequest.getAttribute("skipUserFilter")) {
            filterChain.doFilter(request, response);
        } else if ((!(Objects.equals(url, "http://localhost:8080/admin") ||
                Objects.equals(url, "http://localhost:8080/food/all")))) {
            filterChain.doFilter(request, response);
        }
    }
}
}
*/

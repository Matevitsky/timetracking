package com.matevitsky.controller.filter;

/*
@WebFilter
public class Filter  implements Filter {

    private static final Integer ADMIN_ROLE = 1;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object accessAble = request.getSession().getAttribute("accessAble");
        Object roleId = request.getSession().getAttribute("roleId");
        servletRequest.setAttribute("skipUserFilter", false);
        String url = request.getRequestURL().toString();
        if (!((accessAble == null
                || Objects.equals(request.getSession().getAttribute("accessAble").toString(), "denied"))
                &
                (Objects.equals(url, "http://localhost:8080/main") ||
                        Objects.equals(url, "http://localhost:8080/food/add") ||
                        Objects.equals(url, "http://localhost:8080/admin") ||
                        Objects.equals(url, "http://localhost:8080/food/all")))) {
            if (roleId == ADMIN_ROLE) {
                servletRequest.setAttribute("skipUserFilter", true);
            }
            filterChain.doFilter(request, response);
        }
    }
}
*/

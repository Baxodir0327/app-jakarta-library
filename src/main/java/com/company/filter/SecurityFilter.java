package com.company.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter implements Filter {
    private static final List<String> WHITE_LIST = List.of(
            "/",
            "/book/list",
            "/auth/login",
            "/auth/register",
            "/activation.*" /*,
            "/storage/show",
            "/file/download"*/
    );
    private static final Predicate<String> isOpen = WHITE_LIST::contains;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        if (!isOpen.test(requestURI)) {
            response.sendRedirect("/auth/login");
//            response.sendError(401,"Unauthorized");
        } else {
            filterChain.doFilter(request,response);
        }
    }
}

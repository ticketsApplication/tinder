package org.example.flt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

    @Override
    default void init(FilterConfig filterConfig) {}

    default boolean isHttp(ServletRequest request0, ServletResponse response0) {
        return request0 instanceof HttpServletRequest && response0 instanceof HttpServletResponse;
    }

    void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    default void doFilter(ServletRequest request0, ServletResponse response0, FilterChain chain) throws IOException, ServletException {
        if (isHttp(request0, response0)) {

            HttpServletRequest request = (HttpServletRequest) request0;
            HttpServletResponse response = (HttpServletResponse) response0;

            doHttpFilter(request, response, chain);

//      response.addCookie(...); // 1. correct
//      chain.doFilter(request0, response0); // 2. ok, forward
//      response.sendRedirect("/login"); // 3. wrong - reject
//      response.setStatus(500); // 4. immediately return status
        } else {
            chain.doFilter(request0, response0);
        }
    }

    @Override
    default void destroy() {}
}
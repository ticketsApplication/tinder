package org.example;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class CheckCookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request0, ServletResponse response0, FilterChain chain) throws IOException, ServletException {
        if (request0 instanceof HttpServletRequest && response0 instanceof ServletResponse) {
            HttpServletRequest request = (HttpServletRequest) request0;
            HttpServletResponse response = (HttpServletResponse) response0;
            Cookie[] cs = request.getCookies();
            Optional<Cookie> cookieId = Optional.ofNullable(cs).flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst());
            if (cookieId.isPresent()) chain.doFilter(request, response);
            else
                response.addCookie(new Cookie("id", UUID.randomUUID().toString()));
        } else chain.doFilter(request0, response0);
    }

    @Override
    public void destroy() {
    }
}

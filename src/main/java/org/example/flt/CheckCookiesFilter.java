package org.example.flt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CheckCookiesFilter implements HttpFilter {
    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Cookie[] cs = req.getCookies();
        Optional<Cookie> cookieId = Optional.ofNullable(cs)
                .flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst());
        System.out.println("in filter");
        if (cookieId.isPresent()) {
            System.out.println("continue");
            chain.doFilter(req, resp);
        }
        else  {System.out.println("in filter redirect");
            resp.sendRedirect("/login");}
    }
}
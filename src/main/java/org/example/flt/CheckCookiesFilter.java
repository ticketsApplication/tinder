package org.example.flt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class CheckCookiesFilter implements HttpFilter {
    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        Cookie[] cs = req.getCookies();
        Optional<Cookie> cookieId = Optional.ofNullable(cs)
                .flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst());

        if (cookieId.isPresent()) {
            chain.doFilter(req, resp);
        }
        else  resp.sendRedirect("/login");

    }
}

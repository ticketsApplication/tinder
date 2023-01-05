package org.example.svt;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class RemoveCookieServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        Cookie c = new Cookie("id", "");
        c.setMaxAge(0); //60*60*24
        resp.addCookie(c);
        resp.sendRedirect("/login");
    }


}

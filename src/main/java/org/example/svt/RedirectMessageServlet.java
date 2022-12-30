package org.example.svt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectMessageServlet implements HttpFilter{
    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String userId = req.getParameter("id");
        System.out.println(userId);

//        chain.doFilter(req,resp);
        resp.sendRedirect(req.getContextPath() + "/messages/" + userId);
    }
}

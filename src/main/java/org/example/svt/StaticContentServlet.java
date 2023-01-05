package org.example.svt;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticContentServlet extends HttpServlet {

    private final String osPrefix;
    public StaticContentServlet(String osPrefix) {
        this.osPrefix = osPrefix;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        String pathInfo = req.getPathInfo();

        if (pathInfo.startsWith("/")) pathInfo = pathInfo.substring(1);


        Path file = Path.of(osPrefix, pathInfo);

        try (ServletOutputStream os = resp.getOutputStream()) {
            Files.copy(file, os);
        }
    }
}

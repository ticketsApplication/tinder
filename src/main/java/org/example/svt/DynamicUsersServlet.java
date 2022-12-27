package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DynamicUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        conf.setDirectoryForTemplateLoading(new File("static-content"));
        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Jim");

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("like-page4.html").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

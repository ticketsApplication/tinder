package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class SignupServlet extends HttpServlet {
    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;

    public SignupServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
    }

    HashMap<String, Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        data.put("name", "");
        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("signup.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String file = req.getParameter("file");
        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            collectionTinderDao.signUpUser(name, username, password, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            resp.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printWriter.close();
    }

}

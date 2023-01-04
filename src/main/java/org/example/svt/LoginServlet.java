package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class LoginServlet extends HttpServlet {

    private int currentUserId;
    private String cookieId;
    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;

    public LoginServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
    }

    HashMap<String, Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        data.put("name", "");
        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("login.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ArrayList<Integer> id1 = collectionTinderDao.checkUser(username, password);
            if (id1.isEmpty()) {

                data.put("name", "Username or password is incorrect, please try again");
                try (PrintWriter w = resp.getWriter()) {
                    conf.getTemplate("login.ftl").process(data, w);
                } catch (TemplateException e) {
                    throw new RuntimeException(e);
                }

            } else {
                currentUserId = id1.get(0);
                cookieId = String.valueOf(currentUserId);
                System.out.println(cookieId);
                resp.addCookie(new Cookie("id", UUID.randomUUID().toString()));
                resp.sendRedirect("/users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        printWriter.close();
    }
}
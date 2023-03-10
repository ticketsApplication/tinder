package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.tinderDAO.CollectionTinderDao;
import org.example.tinderDAO.ControllerTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class LoginServlet extends HttpServlet {

    private int currentUserId;
    private String cookieId;
    private final ControllerTinderDao controllerTinderDao;
    private final Configuration conf;

    public LoginServlet(ControllerTinderDao controllerTinderDao, Configuration conf) {
        this.controllerTinderDao = controllerTinderDao;
        this.conf = conf;
    }

    HashMap<String, Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        data.put("name", "");
        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("login.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        String username = req.getParameter("username");
        String password = req.getParameter("password");

       try {
            cookieId = controllerTinderDao.checkUser(username, password);
            if (cookieId == null) {

                data.put("name", "Username or password is incorrect, please try again");
                try (PrintWriter w = resp.getWriter()) {
                    conf.getTemplate("login.ftl").process(data, w);
                } catch (TemplateException e) {
                    throw new RuntimeException(e);
                }

            } else {
               
                resp.addCookie(new Cookie("id", cookieId));
                resp.sendRedirect("/users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
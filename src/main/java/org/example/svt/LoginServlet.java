package org.example.svt;

import freemarker.template.Configuration;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class LoginServlet extends HttpServlet {

    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;

    public LoginServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> fileGet = null;
        try {
            fileGet = Files.readAllLines(Paths.get("static-content/html/login.html"));
            PrintWriter w = resp.getWriter();
            fileGet.forEach(x -> w.println(x));
        } catch (IOException e) {
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
            Optional<ArrayList<Integer>> userId = Optional.ofNullable(collectionTinderDao.checkUser(username, password));
            if (userId.isEmpty()) {
                resp.sendRedirect("/signup");
            }
            else {
                resp.sendRedirect("/users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        printWriter.close();
    }
}

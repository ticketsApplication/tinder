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

public class SignupServlet extends HttpServlet {
    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;

    public SignupServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> fileGet = null;
        try {
            fileGet = Files.readAllLines(Paths.get("static-content/html/signup.html"));
            PrintWriter w = resp.getWriter();
            fileGet.forEach(x -> w.println(x));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

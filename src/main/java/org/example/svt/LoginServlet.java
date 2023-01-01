package org.example.svt;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final CollectionTinderDao collectionTinderDao;

    public LoginServlet(CollectionTinderDao collectionTinderDao) {
        this.collectionTinderDao = collectionTinderDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie c = Optional.ofNullable(req.getCookies())
                .flatMap(cc -> Arrays.stream(cc)
                        .filter(c1 -> c1.getName().equals("id")).findFirst()).get();
        String userId = c.getValue();

        List<String> fileGet = Files.readAllLines(Paths.get("static-content/html/login.html"));

        try (PrintWriter w = resp.getWriter()) {
            fileGet.forEach(x -> w.println(x));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

    }
}

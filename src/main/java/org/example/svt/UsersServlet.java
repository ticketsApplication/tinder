package org.example.svt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> fileGet = Files.readAllLines(Paths.get("static-content/html/like-page3.html"));

        try (PrintWriter w = resp.getWriter()) {
            fileGet.forEach(x -> w.println(x));
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        List<String> fileGet = Files.readAllLines(Paths.get("static-content/html/like-page3.html"));

        try (PrintWriter w = resp.getWriter()) {
            fileGet.forEach(x -> w.println(x));
        }



    }





}

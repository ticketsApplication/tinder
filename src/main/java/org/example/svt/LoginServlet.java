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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final CollectionTinderDao collectionTinderDao;

    public LoginServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
    }

    private Optional<String> safeGet(HttpServletRequest req, String paramName) {
        return Optional.ofNullable(req.getParameter(paramName));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next = req.getContextPath() + req.getRequestURI();
        System.out.println(next);
        Cookie[] cs = req.getCookies();
        Optional.ofNullable(cs)
                .flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst())
                .ifPresentOrElse(
                        c -> {
                            String userId = c.getValue();
                            List<String> fileGet = null;
                            try {
                                fileGet = Files.readAllLines(Paths.get("static-content/html/login.html"));
                                PrintWriter w = resp.getWriter();
                                fileGet.forEach(x -> w.println(x));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {
                            System.out.println("CalcServlet.no cookie");

                            try {
                                resp.sendRedirect("/setcookie");  // + "?next =" + next
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long serialVersionUID = 1L;

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String file = req.getParameter("file");
        String userId = req.getParameter("id");
        PrintWriter printWriter = resp.getWriter();

        try {
            collectionTinderDao.signUpUser(name, login, password, file, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        printWriter.close();

    }
}

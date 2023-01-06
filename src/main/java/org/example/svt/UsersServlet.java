package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.User;
import org.example.tinderDAO.CollectionTinderDao;
import org.example.tinderDAO.ControllerTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UsersServlet extends HttpServlet {
    int calc = 0;
    private final ControllerTinderDao controllerTinderDao;

    private final Configuration conf;
    public UsersServlet(ControllerTinderDao controllerTinderDao, Configuration conf) {
        this.controllerTinderDao = controllerTinderDao;
        try {
            userList = controllerTinderDao.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.conf = conf;
        data = new HashMap<>();
    }

    private final HashMap <String,Object> data;
    private final List<User> userList;

    private List<User> userListWithoutCurrentUser;

    private String userIdMain;

    private int getCurrentUserIdFromCookie (HttpServletRequest req) {
        Cookie[] cs = req.getCookies();
        String cookieName = "id";
        Cookie cookie = null;
        if(cs !=null) {
            for(Cookie c: cs) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }

        return Integer.parseInt(cookie.getValue());
    }
    
    private List<User> getUserListWithoutCurrentUser (int currentUserId, HttpServletRequest req) {
        return userList.stream()
                .filter(user -> user.getId() != getCurrentUserIdFromCookie(req))
                .collect(Collectors.toList());
    }
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        userListWithoutCurrentUser = getUserListWithoutCurrentUser(getCurrentUserIdFromCookie(req), req);
        data.put("user", userListWithoutCurrentUser.get(0));

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("like-page-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        userListWithoutCurrentUser = getUserListWithoutCurrentUser(getCurrentUserIdFromCookie(req), req);
        String isLikedOrNot = req.getParameter("like_status");
        String[] tokens = isLikedOrNot.split("[.]");
        int userId = Integer.parseInt(tokens[0]); // User id who should be liked
        if (tokens[1].equals("like")) {
            try {
                controllerTinderDao.doLike(getCurrentUserIdFromCookie(req), userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (tokens[1].equals("dislike")) {
            try {
                controllerTinderDao.doDisLike(getCurrentUserIdFromCookie(req),userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        if (calc < userListWithoutCurrentUser.size()) {
            data.put("user", userListWithoutCurrentUser.get(calc++));
        } else {
            calc = 0;
            resp.sendRedirect("/liked");
        }

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("like-page-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }

    }
}

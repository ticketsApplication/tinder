package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.User;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PeopleListServlet extends HttpServlet {

    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;
    public PeopleListServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
    }

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String,Object> data = new HashMap<>();
        List<User> userListLiked = null;
        try {
            userListLiked = collectionTinderDao.getLiked(getCurrentUserIdFromCookie(req));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        data.put("user",userListLiked);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("people-list-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        List<User> userListLiked = null;
        try {
            userListLiked = collectionTinderDao.getLiked(getCurrentUserIdFromCookie(req));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        data.put("user",userListLiked);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("people-list-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }

    }
}

package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.User;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;
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

public class UsersServlet extends HttpServlet {
    int calc = 0;
    private final CollectionTinderDao collectionTinderDao;

    private final Configuration conf;
    public UsersServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
        this.conf = conf;
        try {
            userList = collectionTinderDao.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        data = new HashMap<>();
    }

    private final HashMap <String,Object> data;
    private final List<User> userList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        data.put("user",userList.get(0));

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("like-page-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String isLikedOrNot = req.getParameter("like_status");
        String[] tokens = isLikedOrNot.split("[.]");
        int userId = Integer.parseInt(tokens[0]); // User id who should be liked
        if (tokens[1].equals("like")) {
            // doLike (userId)
        } else if (tokens[1].equals("dislike")) {
            //doDislike (userId)
        }

        System.out.printf("id: %s status: %s", tokens[0], tokens [1]);

        if (calc < userList.size() - 1) {
            data.put("user",userList.get(++calc));
        } else {
            calc = 0;
            data.put("user",userList.get(calc));
        }

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("like-page-Andrii.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }

    }
}

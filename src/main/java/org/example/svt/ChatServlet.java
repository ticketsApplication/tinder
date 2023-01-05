package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.ChatMessage;
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

public class ChatServlet extends HttpServlet {

    private final ControllerTinderDao controllerTinderDao;
    private final Configuration conf;
    public ChatServlet(ControllerTinderDao controllerTinderDao, Configuration conf) {
        this.controllerTinderDao = controllerTinderDao;
        this.conf = conf;
        data = new HashMap<>();
    }

    private final String pathInfo = "chat.ftl";
    private static String userId;
    private int userIdMain;
    private HashMap<String,Object> data;

    private static String message;

    private String mainUserName;

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
           resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userId = req.getParameter("id");

        message = req.getParameter("message");

        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        if (message != null) {
            try {
                controllerTinderDao.setMessage (getCurrentUserIdFromCookie(req), Integer.parseInt(userId), message);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        final List<ChatMessage> messageList;

        try {
            messageList = controllerTinderDao.getChatList(getCurrentUserIdFromCookie(req), Integer.parseInt(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            mainUserName = controllerTinderDao.getUserById(getCurrentUserIdFromCookie(req)).getName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String currentUserPhotoLink;

        try {
            currentUserPhotoLink = controllerTinderDao.getUserById(getCurrentUserIdFromCookie(req)).getPhotoLink();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        data.put("mainUserName", mainUserName);
        data.put("currentUserPhotoLink", currentUserPhotoLink);
        data.put("messageList", messageList);
        data.put("userId", userId);
        data.put("userMainId", userIdMain);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate(pathInfo).process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }
}

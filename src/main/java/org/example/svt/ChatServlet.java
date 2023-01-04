package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.ChatMessage;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ChatServlet extends HttpServlet {

    private final CollectionTinderDao collectionTinderDao;
    private final Configuration conf;
    public ChatServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
        this.collectionTinderDao = collectionTinderDao;
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
        System.out.println(cookie.getValue());
        return Integer.parseInt(cookie.getValue());
    }

       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userId = req.getParameter("id");
        System.out.println(userId + "doPost");
        message = req.getParameter("message");

        if (message != null) {
            try {
                collectionTinderDao.setMessage (getCurrentUserIdFromCookie(req), Integer.parseInt(userId), message);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message);
        } else{
            System.out.println(message);
        }

        final List<ChatMessage> messageList;

        try {
            messageList = collectionTinderDao.getChatList(getCurrentUserIdFromCookie(req), Integer.parseInt(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userId + "doGet");

        try {
            mainUserName = collectionTinderDao.getUserById(getCurrentUserIdFromCookie(req)).getName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String currentUserPhotoLink;

        try {
            currentUserPhotoLink = collectionTinderDao.getUserById(getCurrentUserIdFromCookie(req)).getPhotoLink();
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

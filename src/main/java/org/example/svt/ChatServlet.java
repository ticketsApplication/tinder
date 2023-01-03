package org.example.svt;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.example.ChatMessage;
import org.example.Message;
import org.example.tinderDAO.CollectionTinderDao;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private int userIdMain = 1;
    private HashMap<String,Object> data;

    private static String message;

    private String mainUserName;

       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final List<ChatMessage> messageList;

        try {
            messageList = collectionTinderDao.getChatList(userIdMain, Integer.parseInt(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userId + "doGet");

           try {
               mainUserName = collectionTinderDao.getUserById(userIdMain).getName();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }


           String mainUserPhotoLink;
           try {
               mainUserPhotoLink = collectionTinderDao.getUserById(userIdMain).getPhotoLink();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }


//        List <ChatMessage> messageList = new ArrayList<>();
//        messageList.add(new ChatMessage("Ann","Hi"));
//        messageList.add(new ChatMessage("","How are you?"));
//        messageList.add(new ChatMessage("Kate","Hi"));
//        messageList.add(new ChatMessage("","I am Ok!"));
//        messageList.add(new ChatMessage("","And you?"));


        data.put("mainUserName", mainUserName);
        data.put("mainUserPhotoLink", mainUserPhotoLink);
        data.put("messageList", messageList);
        data.put("userId", userId);
        data.put("userMainId", userIdMain);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate(pathInfo).process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userId = req.getParameter("id");
        System.out.println(userId + "doPost");
        message = req.getParameter("message");

        if (message != null) {
            try {
                collectionTinderDao.setMessage (userIdMain, Integer.parseInt(userId), message);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message);
//            resp.sendRedirect(req.getContextPath() + "/messages/" + userId);

        } else{
            System.out.println(message);
//            resp.sendRedirect(req.getContextPath() + "/messages/" + userId);
        }

        resp.sendRedirect(req.getContextPath() + "/messages/" + userId);


    }
}

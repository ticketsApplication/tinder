package org.example;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.svt.*;

import org.example.tinderDAO.CollectionTinderDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ServerApp {


    //  http://localhost:8080/users
    //  http://localhost:8080/liked
    //  http://localhost:8080/login
    //  http://localhost:8080/chat
    public static void main(String[] args) throws Exception {


        CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

        HashMap<Integer, User> users = collectionTinderDao.getUsers();

        System.out.println("----------");

        users.forEach((key, value) -> System.out.println(value.getName()));

        System.out.println("----------");
        System.out.println((users.get(1)).getName());
        System.out.println("----------");
        collectionTinderDao.getLiked(1)
                .forEach(((key, value)
                        -> System.out.println(value.toString())));

        System.out.println("----------");
        List<Message>messageList = collectionTinderDao.getMessageList(1,2);
        System.out.println(messageList);
        System.out.println("----------");


        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(UsersServlet.class, "/users");
        handler.addServlet(PeopleListServlet.class, "/liked");
        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(ChatServlet.class, "/chat");

        handler.addServlet(new ServletHolder(new StaticContentServlet("static-content")), "/static/*");

        server.setHandler(handler);

        server.start();
        server.join();

    }
}
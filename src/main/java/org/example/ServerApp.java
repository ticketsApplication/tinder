package org.example;

import org.example.tinderDAO.CollectionTinderDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ServerApp {
    public static void main(String[] args) throws SQLException {

        CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

        //  Server server = new Server(8080);
        //  ServletContextHandler handler = new ServletContextHandler();

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
        List<Message>messageList = collectionTinderDao.getMessageList(2,1);
        System.out.println(messageList);
        System.out.println("----------");

    }
}
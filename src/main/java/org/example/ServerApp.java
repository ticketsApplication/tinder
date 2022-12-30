package org.example;


import freemarker.template.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.svt.*;

import org.example.tinderDAO.CollectionTinderDao;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;


public class ServerApp {


    //  http://localhost:8080/users
    //  http://localhost:8080/liked
    //  http://localhost:8080/login
    //  http://localhost:8080/setcookie
    //  http://localhost:8080/removecookie
    //  http://localhost:8080/signin
    //  http://localhost:8080/chat
    //  http://localhost:8080/dynamicusers
    public static void main(String[] args) throws Exception {

        CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        conf.setDirectoryForTemplateLoading(new File("static-content/html"));

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        LoginServlet loginServlet = new LoginServlet(collectionTinderDao, conf);   ///////////////////////////
        LoginHistoryServlet loginHistoryServlet = new LoginHistoryServlet(collectionTinderDao, conf);   /////////////////

        handler.addServlet(new ServletHolder(new UsersServlet(collectionTinderDao, conf)), "/users");
        handler.addServlet(new ServletHolder(new PeopleListServlet(collectionTinderDao, conf)), "/liked");
        handler.addServlet(new ServletHolder(loginServlet), "/login");   /////////
        handler.addServlet(new ServletHolder(loginHistoryServlet), "/loginHistory");   ////////////
        handler.addServlet(SignInServlet.class, "/signin");
        handler.addServlet(SetCookieServlet.class, "/setcookie");
        handler.addServlet(RemoveCookieServlet.class, "/removecookie");

        handler.addServlet(ChatServlet.class, "/messages/{id}");


        handler.addServlet(new ServletHolder(new StaticContentServlet("static-content")), "/static/*");

        server.setHandler(handler);

        server.start();
        server.join();

    }
}
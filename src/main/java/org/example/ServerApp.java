package org.example;


import freemarker.template.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.flt.CheckCookiesFilter;
import org.example.svt.*;

import org.example.tinderDAO.CollectionTinderDao;


import javax.servlet.DispatcherType;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;


public class ServerApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    //  http://localhost:8080/users
    //  http://localhost:8080/liked
    //  http://localhost:8080/signup
    //  http://localhost:8080/setcookie
    //  http://localhost:8080/logout
    //  http://localhost:8080/login

    //  http://localhost:8080//messages/{id}
    //  http://localhost:8080/dynamicusers
    public static void main(String[] args) throws Exception {


        CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        conf.setDirectoryForTemplateLoading(new File("static-content/html"));

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(collectionTinderDao, conf)), "/users");
        handler.addServlet(new ServletHolder(new PeopleListServlet(collectionTinderDao, conf)), "/liked");
        handler.addServlet(new ServletHolder(new ChatServlet(collectionTinderDao, conf)), "/messages/*");
        handler.addServlet(new ServletHolder(new SignupServlet(collectionTinderDao, conf)), "/signup");   /////////
        handler.addServlet(new ServletHolder(new LoginServlet(collectionTinderDao, conf)), "/login");
        handler.addServlet(RemoveCookieServlet.class, "/logout");
        handler.addFilter(CheckCookiesFilter.class, "/users", ft);
        handler.addFilter(CheckCookiesFilter.class, "/liked", ft);
        handler.addServlet(new ServletHolder(new StaticContentServlet("static-content")), "/static/*");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
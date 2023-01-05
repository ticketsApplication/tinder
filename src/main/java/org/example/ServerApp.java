package org.example;


import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.flt.CheckCookiesFilter;
import org.example.svt.*;

import org.example.tinderDAO.ControllerTinderDao;


import javax.servlet.DispatcherType;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;


public class ServerApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    //  http://localhost:8080/users
    //  http://localhost:8080/liked
    //  http://localhost:8080/signup
    //  http://localhost:8080/logout
    //  http://localhost:8080/login

    public static void main(String[] args) throws Exception {

        ControllerTinderDao controllerTinderDao = new ControllerTinderDao();

        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        TemplateLoader ldr = new ClassTemplateLoader(ServerApp.class, "/html");
        conf.setTemplateLoader(ldr);

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(controllerTinderDao, conf)), "/users");
        handler.addServlet(new ServletHolder(new PeopleListServlet(controllerTinderDao, conf)), "/liked");
        handler.addServlet(new ServletHolder(new ChatServlet(controllerTinderDao, conf)), "/messages/*");
        handler.addServlet(new ServletHolder(new SignupServlet(controllerTinderDao, conf)), "/signup");   /////////
        handler.addServlet(new ServletHolder(new LoginServlet(controllerTinderDao, conf)), "/login");
        handler.addServlet(RemoveCookieServlet.class, "/logout");
        handler.addFilter(CheckCookiesFilter.class, "/users", ft);
        handler.addFilter(CheckCookiesFilter.class, "/liked", ft);
        handler.addServlet(new ServletHolder(new StaticContentServlet("static-content")), "/static/*");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
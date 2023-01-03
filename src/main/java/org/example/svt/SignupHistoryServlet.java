//package org.example.svt;
//
//import freemarker.template.Configuration;
//import org.example.tinderDAO.CollectionTinderDao;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class SignupHistoryServlet extends HttpServlet {
//
//    private final CollectionTinderDao collectionTinderDao;
//    private final Configuration configuration;
//
//    public SignupHistoryServlet(CollectionTinderDao collectionTinderDao, Configuration conf) {
//        this.collectionTinderDao = collectionTinderDao;
//        this.configuration = conf;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (PrintWriter printWriter = resp.getWriter()) {
//            collectionTinderDao.getAll().forEach(s -> printWriter.println(s));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

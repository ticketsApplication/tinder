package org.example;

import org.example.tinderDAO.CollectionTinderDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) throws SQLException {
        CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

        System.out.println("----------");
       // System.out.println(collectionTinderDao.getMessageList(2,1));

        System.out.println(collectionTinderDao.getLiked(3));

        System.out.println("----------");

       // System.out.println(collectionTinderDao.getUserById(1));

        //collectionTinderDao.setMessage(1,2,"сообщение от первого второму ещё раз");
        collectionTinderDao.doLike(3,2);
        collectionTinderDao.doDisLike(3,2);

    }
}

package org.example.tinderDAO;

import org.example.Message;
import org.example.User;

import java.sql.SQLException;
import java.util.List;

public class ServiceTinderDao {

    CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

    public List<User> getUsers() throws SQLException {
        return collectionTinderDao.getUsers();
    }

    public List <User> getLiked(int user_id) throws SQLException {
        return collectionTinderDao.getLiked(user_id);
    }

    public List<Message> getMessageList(int userOne, int userTwo) throws SQLException {
        return collectionTinderDao.getMessageList(userOne,userTwo);
    }
    public void setMessage(int userFrom, int userTo, String message) throws SQLException {
        collectionTinderDao.setMessage(userFrom,userTo,message);
    }
}

package org.example.tinderDAO;

import org.example.ChatMessage;
import org.example.User;

import java.sql.SQLException;
import java.util.List;

public class ServiceTinderDao {

    private final CollectionTinderDao collectionTinderDao = new CollectionTinderDao();

    public List<User> getUsers() throws SQLException {
        return collectionTinderDao.getUsers();
    }

    public List <User> getLiked(int user_id) throws SQLException {
        return collectionTinderDao.getLiked(user_id);
    }


    public void setMessage(int userFrom, int userTo, String message) throws SQLException {
        collectionTinderDao.setMessage(userFrom,userTo,message);
    }
    public List<ChatMessage> getChatList(int userOne, int userTwo) throws SQLException {
        return collectionTinderDao.getChatList(userOne,userTwo);
    }

    public User getUserById(int userId) throws SQLException {
        return collectionTinderDao.getUserById(userId);
    }

    public String checkUser(String username, String password) throws SQLException {
        return collectionTinderDao.checkUser(username,password);
    }

    public void signUpUser(String name, String username, String password, String file) throws Exception {
        collectionTinderDao.signUpUser(name,username,password,file);
    }

    public void doLike(int userId, int whoLikedId) throws SQLException {
        collectionTinderDao.doLike(userId,whoLikedId);
    }

    public void doDisLike(int userId, int whoDisLikedId) throws SQLException {
        collectionTinderDao.doDisLike(userId,whoDisLikedId);
    }
}

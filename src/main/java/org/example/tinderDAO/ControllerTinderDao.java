package org.example.tinderDAO;

import org.example.ChatMessage;
import org.example.User;

import java.sql.SQLException;
import java.util.List;

public class ControllerTinderDao {
    private final ServiceTinderDao serviceTinderDao = new ServiceTinderDao();

    public void setMessage(int userFrom, int userTo, String message) throws SQLException {
        serviceTinderDao.setMessage(userFrom,userTo,message);
    }

    public List<ChatMessage> getChatList(int userOne, int userTwo) throws SQLException {
        return serviceTinderDao.getChatList(userOne,userTwo);
    }

    public User getUserById(int userId) throws SQLException {
        return serviceTinderDao.getUserById(userId);
    }

    public String checkUser(String username, String password) throws SQLException {
        return serviceTinderDao.checkUser(username, password);
    }

    public List<User> getLiked(int userId) throws SQLException {
        return serviceTinderDao.getLiked(userId);
    }

    public void signUpUser(String name, String username, String password, String file) throws Exception {
        serviceTinderDao.signUpUser(name,username,password,file);
    }

    public List<User> getUsers() throws SQLException {
        return serviceTinderDao.getUsers();
    }

    public void doLike(int userId, int whoLikedId) throws SQLException {
        serviceTinderDao.doLike(userId,whoLikedId);
    }

    public void doDisLike(int userId, int whoDisLikedId) throws SQLException {
        serviceTinderDao.doDisLike(userId,whoDisLikedId);
    }
}

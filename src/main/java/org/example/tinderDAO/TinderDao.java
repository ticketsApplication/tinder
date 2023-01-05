package org.example.tinderDAO;

import org.example.ChatMessage;
import org.example.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TinderDao {

    public Connection getConnection();
    public List<User> getUsers() throws SQLException;
    public List<User> getLiked(int userId) throws SQLException;
    public List<ChatMessage> getChatList(int userOne, int userTwo) throws SQLException;
    public void setMessage(int userFrom, int userTo, String message) throws SQLException;
    public void signUpUser(String name, String login, String password, String file) throws SQLException;
    public String checkUser(String username, String password) throws SQLException;
    public User getUserById(int userId) throws SQLException;
    public void doLike(int userId, int whoLikedId) throws SQLException;
    public void doDisLike(int userId, int whoDisLikedId) throws SQLException;
}

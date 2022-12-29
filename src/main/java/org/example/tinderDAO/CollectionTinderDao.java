package org.example.tinderDAO;

import org.example.Message;
import org.example.User;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionTinderDao {

    public CollectionTinderDao() {
    }

    public Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://suleiman.db.elephantsql.com:5432/kkjammbr",
                    "kkjammbr",
                    "jELEJTiSbMjZS9_f-2grM7v4BPuqBLGe");
        } catch (SQLException e) {
            System.out.println("no connection");
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select * from users");

        List<User> userList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("photo");
            userList.add(new User(id, name, link));
        }
        connection.close();
        return userList;
    }

    public List<User> getLiked(int userId) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from users join liked l on users.id = l.who_liked where user_id =?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("photo");
            userList.add(new User(id, name, link));
        }
        connection.close();
        return userList;
    }

    public List<Message> getMessageList(int userOne, int userTwo) throws SQLException {
        Connection connection = getConnection();
        List<Message> messageList = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement(
                "select *, 'L' dir from messages " +
                        "where user_from = ? and user_to = ? " +
                        "union select *, 'R' dir from messages " +
                        "where user_from = ? and user_to = ? " +
                        "order by dt");
        stmt.setInt(1, userOne);
        stmt.setInt(2, userTwo);
        stmt.setInt(3, userTwo);
        stmt.setInt(4, userOne);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int from = rs.getInt("user_from");
            int to = rs.getInt("user_to");
            String message = rs.getString("message");
            LocalDateTime dt = rs.getTimestamp("dt").toLocalDateTime();
            String direction = rs.getString("dir");
            messageList.add(new Message(id, from, to, message, dt, direction));
        }
        connection.close();
        return messageList;
    }

    public void setMessage(int userFrom, int userTo, String message) throws SQLException {
        Connection connection = getConnection();
        String sql = "insert into public.messages (id, user_from, user_to, message, dt) " +
                "values (default, ?, ?, ?, default)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userFrom);
        stmt.setInt(2, userTo);
        stmt.setString(3, message);
        stmt.execute();
        connection.close();
    }

    public User getUserById(int userId) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from users where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String photo = rs.getString("photo");
            connection.close();
            return new User(id, name, photo);
        }
        connection.close();
        return null;
    }

    public void doLike(int userId, int whoLikedId) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from liked where user_id=? and who_liked=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, whoLikedId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            connection.close();
        } else {
            String sql2 = "insert into public.liked (id, user_id, who_liked) values (default, ?, ?)";
            stmt = connection.prepareStatement(sql2);
            stmt.setInt(1, userId);
            stmt.setInt(2, whoLikedId);
            stmt.execute();
            connection.close();
        }
    }

    public void doDisLike(int userId, int whoLikedId) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from liked where user_id=? and who_liked=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, whoLikedId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String sql2 = "delete from public.liked where user_id=? and who_liked=?";
            stmt = connection.prepareStatement(sql2);
            stmt.setInt(1, userId);
            stmt.setInt(2, whoLikedId);
            stmt.execute();
            connection.close();
        } else {
            connection.close();
        }
    }
}
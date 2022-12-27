package org.example.tinderDAO;

import org.example.Message;
import org.example.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        List <User> userList = new ArrayList<>();
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

    public List <User> getLiked(int user_id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "select * from users\n" +
                        "join liked l on users.id = l.who_liked\n" +
                        "where user_id =" + user_id);
        List <User> userList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("photo");
            userList.add (new User(id, name, link));
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
        String sql = "INSERT INTO public.messages (id, user_from, user_to, message, dt) " +
                "VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userFrom);
        stmt.setInt(2, userTo);
        stmt.setString(3, message);
        stmt.execute();
        connection.close();
    }
}
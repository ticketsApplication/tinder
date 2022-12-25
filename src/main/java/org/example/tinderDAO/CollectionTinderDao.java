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

    public List<Message> getMessageList(int userFrom, int userTo) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "select *, 'L' dir from messages where user_from = " + userFrom +
                        " and user_to = " + userTo +
                        " union select *, 'R' dir from messages where user_from = " + userTo +
                        " and user_to = " + userFrom +
                        " order by dt");
        List<Message> messageList = new ArrayList<>();
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
}
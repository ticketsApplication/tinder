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
                    "jdbc:postgresql://localhost:5432/tinder",
                    "tinder",
                    "Gbtntn751");
        } catch (SQLException e) {
            System.out.println("no connection");
            throw new RuntimeException(e);
        }
    }

    public HashMap<Integer, User> getUsers() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select * from users");
        HashMap<Integer, User> iuHashMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("photo");
            iuHashMap.put(id, new User(id, name, link));
        }
        connection.close();
        return iuHashMap;
    }

    public HashMap<Integer, User> getLiked(int user_id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "select * from users\n" +
                        "join liked l on users.id = l.who_liked\n" +
                        "where user_id =" + user_id);

        HashMap<Integer, User> iuHashMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("photo");
            iuHashMap.put(id, new User(id, name, link));
        }
        connection.close();
        return iuHashMap;
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
//connection.prepareStatement("INSERT INTO public.login (id, user_id, user_login, user_password, dt) VALUES (DEFAULT, 5, '555', '555', DEFAULT)");


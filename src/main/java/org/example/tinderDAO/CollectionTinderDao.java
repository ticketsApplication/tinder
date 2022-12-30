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

    ///////////
    public void signUpUser(String name, String login, String password, String file, String userId) throws Exception {
        Connection connection = getConnection();
        String sql = "insert into users (name, photo, user_login, user_password, dt, usr) values (?, ?, ?, ?, default, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, file);
        stmt.setString(3, login);
        stmt.setString(4, password);
        stmt.setString(5, userId);

        stmt.execute();
    }

    public ArrayList<String> getAll() throws Exception {
        Connection connection = getConnection();
        String sql = "select name, photo, user_login, user_password, dt, usr from users";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<String> outcome = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            String login = rs.getString("login");
            String password = rs.getString("password");
            String file = rs.getString("file");
            String userId = rs.getString("id");
            outcome.add(String.format("name: %s, photo: s, username: %s, password: %s", name, file, login, password));
        }
        return outcome;
    }
    ///////////

}
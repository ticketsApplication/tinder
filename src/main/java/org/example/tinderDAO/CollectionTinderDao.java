package org.example.tinderDAO;

import org.example.ChatMessage;
import org.example.Message;
import org.example.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionTinderDao {

    public CollectionTinderDao() {
    }

    private int currentUserId = 2;
    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
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

    public List<ChatMessage> getChatList(int userOne, int userTwo) throws SQLException {
        List<ChatMessage> chatList = new ArrayList<>();
        Connection connection = getConnection();
        List<Message> messageList = new ArrayList<>();
        String sql = "select * from messages join users u on messages.user_from = u.id " +
                "where (user_from = ? and user_to = ?) " +
                "or (user_from = ? and user_to = ?) " +
                "order by messages.dt";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userOne);
        stmt.setInt(2, userTwo);
        stmt.setInt(3, userTwo);
        stmt.setInt(4, userOne);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String message = rs.getString("message");
            chatList.add(new ChatMessage(name, message));
        }
        connection.close();
        String temp = "";
        for (int i = 0; i < chatList.size(); i++) {
            if (chatList.get(i).getName().equals(temp))
                chatList.get(i).setName("");
            else temp = chatList.get(i).getName();
        }
        return chatList;
    }

//    public List<Message> getMessageList(int userOne, int userTwo) throws SQLException {
//        Connection connection = getConnection();
//        List<Message> messageList = new ArrayList<>();
//        String sql = "select * from messages join users u on messages.user_from = u.id " +
//                "where (user_from = ? and user_to = ?) " +
//                "or (user_from = ? and user_to = ?) " +
//                "order by messages.dt";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setInt(1, userOne);
//        stmt.setInt(2, userTwo);
//        stmt.setInt(3, userTwo);
//        stmt.setInt(4, userOne);
//
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            int from = rs.getInt("user_from");
//            int to = rs.getInt("user_to");
//            String message = rs.getString("message");
//            LocalDateTime dt = rs.getTimestamp("dt").toLocalDateTime();
//            //String direction = rs.getString("dir");
//            String ico = rs.getString("photo");
//            messageList.add(new Message(id, from, to, message, dt, ico));
//        }
//        connection.close();
//        return messageList;
//    }

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

    ///////////
    public void signUpUser(String name, String login, String password, String file) throws Exception {
        Connection connection = getConnection();
        String sql = "insert into users (name, photo, user_login, user_password, dt) values (?, ?, ?, ?, default)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, file);
        stmt.setString(3, login);
        stmt.setString(4, password);

        stmt.execute();
        connection.close();
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
    public ArrayList<Integer> checkUser(String username, String password) throws SQLException {
        Connection connection = getConnection();
        String sql = "select id from users where user_login = ? and user_password = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Integer> outcomeId = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            outcomeId.add(id);
        }
        connection.close();
        return outcomeId;
    }
    ///////////


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
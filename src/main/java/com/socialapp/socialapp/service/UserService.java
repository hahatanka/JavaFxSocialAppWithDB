package com.socialapp.socialapp.service;

import com.socialapp.socialapp.model.Post;
import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class UserService {

  private Connection connection = DBManager.getConnection();

  public void createDBandUserTable() throws SQLException {
    connection = DBManager.getConnection();
    String query =
            "CREATE TABLE IF NOT EXISTS users(\n" +
                    "  id int not null auto_increment,\n" +
                    "  name varchar(100) not null,\n" +
                    "  username varchar(100) unique not null,\n" +
                    "  password text not null,\n" +
                    "  location varchar(100),\n" +
                    "  email varchar(100) unique null,\n" +
                    "  about text null,\n" +
                    "  createdAt timestamp default current_timestamp,\n" +
                    "  updatedAt timestamp default current_timestamp on update current_timestamp,\n" +
                    "  primary key (id)\n" +
                    ");";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.execute();
    connection.close();
    statement.close();
  }


  public int verifyUserDetails(String username, String password) throws Exception {
    connection = DBManager.getConnection();
    String query = "SELECT * FROM users WHERE userName = ? && password = ? LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, username);
    statement.setString(2, password);

    Integer userId = null;

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) userId = resultSet.getInt("id");

    DBManager.close(resultSet, statement, connection);

    if (userId == null || userId == 0) throw new Exception("Username or password not correct");

    return userId;
  }

  public User getUserProfile(int userId) throws Exception {
    connection = DBManager.getConnection();
    String query = "SELECT id, name, username, email, about, location, createdAt, updatedAt" +
            " From users WHERE id = ? LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, userId);

    ResultSet result = statement.executeQuery();
    User user = null;
    if (result.next()) {
      user = new User(result.getInt("id"), result.getString("name"),
              result.getString("username"), result.getString("location"),
              result.getString("email"), result.getString("about"),
              result.getTimestamp("createdAt"), result.getTimestamp("updatedAt"));
    }

    DBManager.close(result, statement, connection);

    if (user == null) throw new Exception("User not found");

    return user;
  }

  public void addUser(String name, String userName, String password, String location, String email, String about) throws SQLException {
    connection = DBManager.getConnection();
    String query = "INSERT INTO users (name, username, password, location, " +
            "email, about)" +
            "VALUES(?,?,?,?,?,?);";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, name);
    statement.setString(2, userName);
    statement.setString(3, password);
    statement.setString(4, location);
    statement.setString(5, email);
    statement.setString(6, about);
//    statement.setString(1,createdAt);
//    statement.setString(1,updatedAt);

    statement.execute();
    //could not use DBManager.close() because there is no resultSet
    connection.close();
    statement.close();

  }

  public void updateUser(int userId, String name, String userName, String password, String location, String email, String about) throws SQLException {
    connection = DBManager.getConnection();

    String query = "UPDATE users SET name = ?, username = ?, email = ?, about = ?, location = ?, password =? WHERE id = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, name);
    preparedStatement.setString(2, userName);
    preparedStatement.setString(3, email);
    preparedStatement.setString(4, about);
    preparedStatement.setString(5, location);
    preparedStatement.setString(6, password);
    preparedStatement.setInt(7, userId);

    preparedStatement.execute();
    connection.close();
    preparedStatement.close();
  }

  public ArrayList<User> getListOfFriends(int userId) throws SQLException {
    connection = DBManager.getConnection();
    String query = "SELECT * FROM users WHERE NOT id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, userId);
    ArrayList<User> userList = new ArrayList<>();
    ResultSet result = statement.executeQuery();
    User user = null;
    while (result.next()) {
      user = new User(result.getInt("id"), result.getString("name"),
              result.getString("username"), result.getString("location"),
              result.getString("email"), result.getString("about"),
              result.getTimestamp("createdAt"), result.getTimestamp("updatedAt"));
      userList.add(user);

    }
    return userList;
  }

  public void createChatTable() throws SQLException {
    connection = DBManager.getConnection();
    String query = "CREATE TABLE IF NOT EXISTS userChats (user_id int not null,\n" +
            "friend_id int not null,\n" +
            "username varchar(100)  not null,\n" +
            "messages varchar(2000),createdAt timestamp default current_timestamp)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.execute();

    connection.close();
    statement.close();
  }

  public void addMessageToDB(int userId, int friendId, String name, String message) throws SQLException {
    connection = DBManager.getConnection();
    String query = "INSERT INTO userChats (username, messages, user_id, friend_id) VALUES(?,?,?,?);";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setString(1, name);
    statement.setString(2, message);
    statement.setInt(3, userId);
    statement.setInt(4, friendId);
    statement.execute();

    connection.close();
    statement.close();
  }

  public ArrayList<String> viewChat(int friendId, int userId) throws SQLException {
    connection = DBManager.getConnection();
    String query = "SELECT * FROM userChats WHERE user_id IN (?, ?) AND friend_id IN (?, ?)";//user_id =? AND friend_id= ?
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, userId);
    statement.setInt(2, friendId);
    statement.setInt(3, userId);
    statement.setInt(4, friendId);
    ArrayList<String> messageList = new ArrayList<>();
    ResultSet result = statement.executeQuery();
    while (result.next()) {
      String message = result.getTimestamp("createdAt") + "--- " + result.getString("username") + ": " + result.getString("messages") + " ";
      messageList.add(message);
    }
    return messageList;
  }

  public void createFeedTable() throws SQLException {
    connection = DBManager.getConnection();
    String query = "CREATE TABLE IF NOT EXISTS feed_text(\n" +
            "post_id int not null unique auto_increment,\n" +
            "  user_id int not null,\n" +
            "  username varchar(100) not null,\n" +
            "  post varchar(2000) not null,\n" +
            "  createdAt timestamp default current_timestamp);";

    PreparedStatement statement = connection.prepareStatement(query);
    statement.execute();
    connection.close();
    statement.close();
  }

  public void addPostToFeed(int userId, String name, String text) throws SQLException {
    connection = DBManager.getConnection();
    String query = "INSERT INTO feed_text(user_id, post, username) VALUES(?,?,?);";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setInt(1, userId);
    statement.setString(2, text);
    statement.setString(3, name);

    statement.execute();
    connection.close();
    statement.close();

  }

  public ArrayList<String> viewFeedText() throws SQLException {
    connection = DBManager.getConnection();
    String query ="SELECT post_id, createdAt, username, post FROM feed_text;";

    PreparedStatement statement = connection.prepareStatement(query);
    ArrayList<String> postList = new ArrayList<>();
    ResultSet result = statement.executeQuery();
    while (result.next()) {
      String post = result.getString("post_id")+ "@"+ result.getTimestamp("createdAt")+"----"+ result.getString("username")+": "+result.getString("post");
      postList.add(post);
    }
    return postList;
  }


  public ArrayList<Post> getListOfPosts() throws SQLException {
    connection = DBManager.getConnection();
    String query = "SELECT * FROM feed_text";
    PreparedStatement statement = connection.prepareStatement(query);
    ArrayList<Post> listOfPosts = new ArrayList<>();
    ResultSet result = statement.executeQuery();
    Post post = null;
    while (result.next()) {
      post = new Post(result.getInt("post_id"), result.getString("username"),
              result.getString("post"), result.getTimestamp("createdAt"));
      listOfPosts.add(post);

    }
    return listOfPosts;
  }

  public void createCommentsTable() throws SQLException {
    connection = DBManager.getConnection();
    String query = "CREATE TABLE IF NOT EXISTS post_comments(\n" +
            "  post_id int not null,\n" +
            "  friend_id int not null,\n" +
            "  username varchar(100) not null,\n" +
            "  post_comment varchar(2000) not null,\n" +
            "  createdAt timestamp default current_timestamp)";

    PreparedStatement statement = connection.prepareStatement(query);
    statement.execute();
    connection.close();
    statement.close();

  }

  public void addComment(int postId, int friendId, String username, String comment) throws SQLException {
    connection = DBManager.getConnection();
    String query = "INSERT INTO post_comments(post_id, friend_id, username, post_comment) VALUES(?,?,?,?);";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setInt(1, postId);
    statement.setInt(2, friendId);
    statement.setString(3, username);
    statement.setString(4, comment);

    statement.execute();
    connection.close();
    statement.close();
  }

  public ArrayList<String> prepareComments(int postId) throws SQLException {
    connection = DBManager.getConnection();
    String query = "SELECT feed_text.post_id, post_comments.friend_id, post_comments.username,\n" +
            "post_comments.post_comment, post_comments.createdAt \n" +
            "FROM post_comments \n" +
            "INNER JOIN feed_text ON feed_text.post_id=post_comments.post_id WHERE feed_text.post_id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, postId);
    ResultSet result = statement.executeQuery();
    ArrayList<String> listOfComments = new ArrayList<>();
    while (result.next()) {
      String comment = result.getString("createdAt") + "---" +result.getString("username")
              + ": " + result.getString("post_comment");
      listOfComments.add(comment);

    }
    return listOfComments;

  }
}
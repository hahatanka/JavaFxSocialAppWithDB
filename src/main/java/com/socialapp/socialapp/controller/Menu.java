package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.Post;
import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Menu extends ViewController implements Initializable {

    public Label nameText;
    public TextArea feedText;
    private UserService userService = new UserService();
    public ListView<String> listView = new ListView<>();
    public ArrayList<Post> posts;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            User user = this.userService.getUserProfile(userId);
            nameText.setText(user.getName() + "!");
            userService.createFeedTable();

            try {
                for (Post post : userService.getListOfPosts()) {
                    String currentPost = post.getPost_id() + "@" + post.getCreatedAt() + ": "
                            + post.getUsername() +  "\n" + post.getPostText();
                    listView.getItems().add(currentPost);
                    if(listView.getItems().isEmpty()){
                        showAlert("INFO", "Nothing to show in feed, be the first to add post :)", Alert.AlertType.INFORMATION);
                    }
                }
            }catch(Exception e){
                showAlert("Problem with navigation", e.getMessage(), Alert.AlertType.ERROR);
            }


//            feedText.setText(userService.viewFeedText());
//            feedText.setEditable(false);
//            File file = new File("Box13.jpg");
//            Image image = new Image(file.toURI().toString());
//            imageView.setImage(image);

        } catch (Exception ex) {
            showAlert("INFO", "The feed is empty, be the first to make a post :)", Alert.AlertType.INFORMATION);
            ex.printStackTrace();
        }
    }

    public void viewProfile(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            User user = this.userService.getUserProfile(userId);
            changeScene(actionEvent, "profile");
        } catch (Exception ex) {
            showAlert("Problem with navigation", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void viewFriends(ActionEvent actionEvent) {
        try {
            changeSceneSmall(actionEvent, "friendList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void viewComments(ActionEvent actionEvent) throws SQLException {
//        try {
//            posts = this.userService.getListOfPosts();
//            String selectedPost = listView.getSelectionModel().getSelectedItem();
//            System.out.println(selectedPost);
//            String[] splittedPost = selectedPost.split("@", 2);
//            System.out.println(splittedPost[0]);
//            for (Post post : this.posts) {
//                if (Objects.equals(post.getPost_id(), String.valueOf(splittedPost[0]))) {
//                    DataManager.getInstance().setPostId(post.getPost_id());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void writePost(ActionEvent actionEvent) {
        try {
            changeSceneSmall(actionEvent, "writePost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLogout(ActionEvent actionEvent) {
        try {
            DataManager.getInstance().setLoggedInUserId(null);
            changeScene(actionEvent, "login");
        } catch (Exception ex) {
            showAlert("Problem with navigation", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void handleLoadComments(ActionEvent actionEvent) {
        try {
            posts = this.userService.getListOfPosts();
            String selectedPost = listView.getSelectionModel().getSelectedItem();
            System.out.println(selectedPost);
            String[] splittedPost = selectedPost.split("@", 2);
            System.out.println(splittedPost[0]);
            for (Post post : this.posts) {
                if (post.getPost_id() == Integer.valueOf(splittedPost[0])) {
                    System.out.println("splitted post id: " + Integer.valueOf(splittedPost[0]));
                    System.out.println("post id " + post.getPost_id());
                    DataManager.getInstance().setPostId(Integer.valueOf(splittedPost[0]));
                    changeScene(actionEvent, "comments");
                }
                }

            } catch(Exception e){
                e.printStackTrace();
            showAlert("INFO", "Please choose a post from the feed to see the comments", Alert.AlertType.INFORMATION);
//        }
//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

            }
        }
    }




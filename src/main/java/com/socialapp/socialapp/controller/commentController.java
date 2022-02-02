package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.Post;
import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class commentController extends ViewController implements Initializable {


    public ListView commentList;
    public Text postText;
    public TextArea commentText;
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Integer postId = DataManager.getInstance().getPostId();
            userService.createCommentsTable();
            for (Post post : userService.getListOfPosts()) {
                if(post.getPost_id() == postId){
                    postText.setText("\" " + post.getPostText()+ " \"");
                    postText.setWrappingWidth(500);
                }
            }
            commentText.setWrapText(true);
            commentText.setPromptText("Leave your comment here...");

            for(String message: userService.prepareComments(postId)) {
                commentList.getItems().add(message);}
            if(commentList.getItems().isEmpty()){
                showAlert("Oops!", "Nobody has commented this post yet, be the first one :)", Alert.AlertType.INFORMATION);
            }
        }catch(Exception e){
            e.printStackTrace();
            showAlert("Problem loading comments", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void handleAddComment(ActionEvent actionEvent) {
        try {
            Integer postId = DataManager.getInstance().getPostId();
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            User user = this.userService.getUserProfile(userId);
            userService.addComment(postId, userId,user.getName(),commentText.getText() );
            changeScene(actionEvent, "comments");
        }catch (Exception e){
            e.printStackTrace();
            showAlert("Comment is not added!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void handleLoadMenu(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            DataManager.getInstance().setLoggedInUserId(userId);
            changeScene(actionEvent, "menu");
        } catch (Exception ex) {
            showAlert("Problem with navigation", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}

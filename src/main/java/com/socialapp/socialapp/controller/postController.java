package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


public class postController extends ViewController implements Initializable {
    UserService userService = new UserService();
    public TextArea postText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postText.setWrapText(true);
        postText.setPromptText("Enter your post here...");
    }


    public void handlePost(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            User user = this.userService.getUserProfile(userId);
            userService.addPostToFeed(userId, user.getUserName(), postText.getText());

        showAlert("SUCCESS ", "You have posted to the news feed", Alert.AlertType.INFORMATION);
        changeScene(actionEvent,"menu");
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Adding post failed", e.getMessage(), Alert.AlertType.ERROR);
    }
}

    public void handleLoadMenu(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            DataManager.getInstance().setLoggedInUserId(userId);
            changeScene(actionEvent, "menu");
        }catch (Exception ex){
            showAlert("Problem with navigation", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}

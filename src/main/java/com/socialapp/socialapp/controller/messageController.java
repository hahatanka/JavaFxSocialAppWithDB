package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class messageController extends ViewController implements Initializable {
    public TextArea messageTextArea;
    UserService userService = new UserService();
    public ListView<String> listView = new ListView<>();

@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    Integer userId = DataManager.getInstance().getLoggedInUserId();
    Integer friendId = DataManager.getInstance().getFriendId();
    try {
        userService.createChatTable();
        for(String message: userService.viewChat(friendId,userId)) {
            listView.getItems().add(message);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void handleSendMessage(ActionEvent actionEvent){
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            Integer friendId = DataManager.getInstance().getFriendId();
            User user = this.userService.getUserProfile(userId);
            userService.createChatTable();
            userService.addMessageToDB(userId,friendId,user.getName(),messageTextArea.getText());
            changeScene(actionEvent, "sendMessage");
            } catch (Exception exception) {
            exception.printStackTrace();
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

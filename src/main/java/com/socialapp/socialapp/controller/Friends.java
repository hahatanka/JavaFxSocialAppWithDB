package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Friends extends ViewController implements Initializable {

    ;
    public ListView<String> listView = new ListView<>();
    private UserService userService = new UserService();
    public ArrayList<User> friends;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            System.out.println(userId);
            friends = this.userService.getListOfFriends(userId);
            for (User user : friends) {
                String name = user.getName();
                listView.getItems().add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleMessenger(ActionEvent actionEvent) throws IOException, SQLException {
        Integer userId = DataManager.getInstance().getLoggedInUserId();
        friends = this.userService.getListOfFriends(userId);
        String name = listView.getSelectionModel().getSelectedItem();
        System.out.println(name);
        for (User user : this.friends) {
            if (user.getName().equals(name)) {
                DataManager.getInstance().setFriendId(user.getId());
                changeScene(actionEvent, "sendMessage");
            }
        }
    }

    public void handleViewProfile (ActionEvent actionEvent) throws IOException, SQLException{

                Integer userId = DataManager.getInstance().getLoggedInUserId();
                friends = this.userService.getListOfFriends(userId);
                String name = listView.getSelectionModel().getSelectedItem();
                System.out.println(name);
                for (User user : this.friends) {
                    if (user.getName().equals(name)) {
                        DataManager.getInstance().setFriendId(user.getId());
                        changeScene(actionEvent, "friendProfile");
                    }
                }
        }

        public void handleLoadMenu (ActionEvent actionEvent){
            try {
                Integer userId = DataManager.getInstance().getLoggedInUserId();
                DataManager.getInstance().setLoggedInUserId(userId);
                changeScene(actionEvent, "menu");
            } catch (Exception ex) {
                showAlert("Problem with navigation", ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }



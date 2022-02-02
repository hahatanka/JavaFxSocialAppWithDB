package com.socialapp.socialapp.controller;

import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController extends ViewController implements Initializable{
//
//        public Label usernameLabel;
//        public Label emailAddressLabel;
//        public Label nameLabel;
//        public Label locationLabel;
//        public Label aboutLabel;
        public TextField nameField;
        public TextField usernameField;
        public TextField emailField;
        public PasswordField passwordField;
        public PasswordField confirmPasswordField;
        public TextField locationField;
        public TextArea aboutTextArea;

        private UserService userService = new UserService();
        Integer userId = DataManager.getInstance().getLoggedInUserId();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                //Integer userId = DataManager.getInstance().getLoggedInUserId();
                User user = this.userService.getUserProfile(userId);
                nameField.setText(user.getName());
                nameField.setEditable(true);
                usernameField.setText(user.getUserName());
                emailField.setText(user.getEmail());
                locationField.setText(user.getLocation());
                aboutTextArea.setText(user.getAbout());


            }catch (Exception ex){
                showAlert("SOMETHING FAILED ", ex.getMessage(), Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        }


    public void handleSaveUpdates(ActionEvent actionEvent) {
            try {
                userService.updateUser(userId,nameField.getText(),usernameField.getText(),passwordField.getText(),locationField.getText(),
                        emailField.getText(), aboutTextArea.getText());
                showAlert("SUCCESS ", "User "+ nameField.getText() + " updated successfully", Alert.AlertType.INFORMATION);
                changeScene(actionEvent,"profile");
            }catch (Exception e){
                e.printStackTrace();
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



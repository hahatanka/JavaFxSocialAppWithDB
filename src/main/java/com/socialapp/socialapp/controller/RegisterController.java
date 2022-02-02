package com.socialapp.socialapp.controller;
import com.socialapp.socialapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterController extends ViewController{
    public TextField nameField;
    public TextField locationField;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;
    public TextField emailField;
    public TextArea aboutTextArea;
    public Label notificationLabel;

    private UserService userService = new UserService();

    public void handleRegistration(ActionEvent actionEvent){
        /// interact with db
        try {
            userService.createDBandUserTable();
            userService.addUser(nameField.getText(),usernameField.getText(),passwordField.getText(),locationField.getText(),
                    emailField.getText(), aboutTextArea.getText());
            showAlert("SUCCESS ", "User "+ nameField.getText() + " added successfully", Alert.AlertType.INFORMATION);
            changeScene(actionEvent,"login");
        } catch (Exception e) {
            e.printStackTrace();
            notificationLabel.setText(e.getMessage());
            showAlert("Adding user failed", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void handleLoadLogin(ActionEvent actionEvent){
        try {
            changeScene(actionEvent,"login");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

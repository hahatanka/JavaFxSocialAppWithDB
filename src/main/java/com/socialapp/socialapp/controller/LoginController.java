  package com.socialapp.socialapp.controller;
  import com.socialapp.socialapp.repository.DataManager;
  import com.socialapp.socialapp.service.UserService;
  import javafx.event.ActionEvent;
  import javafx.fxml.FXML;
  import javafx.scene.control.Alert.AlertType;
  import javafx.scene.control.Label;
  import javafx.scene.control.PasswordField;
  import javafx.scene.control.TextField;

    public class LoginController extends ViewController {
      UserService userService = new UserService();
      public Label notificationLabel;
      public TextField usernameField;
    @FXML
      PasswordField passwordField;

      public void handleUserLogin(ActionEvent actionEvent){
    /// interact with db
      try {
      Integer userId = userService.verifyUserDetails(usernameField.getText(), passwordField.getText());
      notificationLabel.setText("Login successful for " + usernameField.getText() + "User" + userId);
      DataManager.getInstance().setLoggedInUserId(userId);
      changeScene(actionEvent,"menu");
        } catch (Exception e) {
        e.printStackTrace();
        notificationLabel.setText(e.getMessage());
        showAlert("Login failed", e.getMessage(),AlertType.ERROR);
      }
    }

      public void handleLoadRegister (ActionEvent actionEvent) {
        try {
        changeScene(actionEvent,"register");
        }catch(Exception ex){
        ex.printStackTrace();
     }
    }
  }

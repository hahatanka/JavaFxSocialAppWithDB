package com.socialapp.socialapp.controller;
import com.socialapp.socialapp.model.User;
import com.socialapp.socialapp.repository.DataManager;
import com.socialapp.socialapp.service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController extends ViewController implements Initializable {
    public Label usernameLabel;
    public Label emailAddressLabel;
    public Label locationLabel;
    public Label nameLabel;
    public Label aboutLabel;
    public Label createdAtLabel;
    public Label updatedAtLabel;

    @FXML
    ImageView myImageview;


    private UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{

            Integer userId = DataManager.getInstance().getLoggedInUserId();
            User user = this.userService.getUserProfile(userId);
            nameLabel.setText(user.getName());
            usernameLabel.setText(user.getUserName());
            emailAddressLabel.setText(user.getEmail());
            locationLabel.setText(user.getLocation());
            aboutLabel.setText(user.getAbout());
            createdAtLabel.setText(user.getCreatedAt().toString());
            updatedAtLabel.setText(user.getUpdatedAt().toString());

            String imagePath = "/view/stickers/"+ user.getId() +".png";
            Image myImage = new Image(getClass()
                    .getResourceAsStream(imagePath));
            myImageview.setImage(myImage);
            myImageview.setFitHeight(250);
            myImageview.setFitWidth(250);

        } catch (Exception e) {
            showAlert("Profile load failed", e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }


    public void handleEdit(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            DataManager.getInstance().setLoggedInUserId(userId);

            changeScene(actionEvent, "editProfile");
        }catch (Exception ex){
            showAlert("Problem with navigation", ex.getMessage(), AlertType.ERROR);
        }
    }

    public void handleLoadMenu(ActionEvent actionEvent) {
        try {
            Integer userId = DataManager.getInstance().getLoggedInUserId();
            DataManager.getInstance().setLoggedInUserId(userId);
        changeScene(actionEvent, "menu");
    }catch (Exception ex){
        showAlert("Problem with navigation", ex.getMessage(), AlertType.ERROR);
    }
}
}

package com.socialapp.socialapp.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ViewController {

    public void changeScene(ActionEvent event, String sceneName) throws IOException {
        String scenePath = "/view/" + sceneName + ".fxml";
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenePath)));
        Scene scene = new Scene(parent,800,800);
        scene.getRoot().setStyle("-fx-font-family:'serif'");
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneSmall(ActionEvent event, String sceneName) throws IOException {
        String scenePath = "/view/" + sceneName + ".fxml";
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(scenePath)));
        Scene scene = new Scene(parent,600,500);
        scene.getRoot().setStyle("-fx-font-family:'serif'");
        stage.setScene(scene);
        stage.show();
    }

    /*showAlertExample();*/
    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.getDialogPane().setStyle("-fx-font-family:'serif'"); // change font !!!!
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();
    }
}

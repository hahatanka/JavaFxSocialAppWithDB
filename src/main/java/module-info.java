module com.socialapp.socialapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires commons.configuration;

  opens com.socialapp.socialapp to javafx.fxml;
  exports com.socialapp.socialapp;
  exports com.socialapp.socialapp.controller;
  opens com.socialapp.socialapp.controller to javafx.fxml;
}
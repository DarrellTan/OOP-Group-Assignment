package com.example.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {


    // Scene Swapping Attributes
    private Stage stage;
    private Scene scene;
    private Parent root;

// Controller to switch scenes
    public void switchToGuestMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestMainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/GuestMainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/Login.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
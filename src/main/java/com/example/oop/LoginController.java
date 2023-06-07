package com.example.oop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    // Declaring the variables needed for login
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // Scene Swapping Attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean passwordMatch = false;



    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    // Event Handler which performs a method to get Login Details into Strings
    @FXML
    private void buttonEventHandler(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + "\n" + password);
        if (passwordMatch == true) {
            // Changes to Main Menu
            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 580, 460);
            scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

//
//    public void start(Stage stage) throws IOException {
//        //Gets UI from XML File
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("Login.fxml"));
//        fxmlLoader.setLocation(getClass().getResource("Login.fxml"));
//
//        // Creates Scene for JavaFX Program
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//
//        // Gets CSS file for Styling
//        scene.getStylesheets().add(getClass().getResource("CSS/Login.css").toExternalForm());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//
//    }
}
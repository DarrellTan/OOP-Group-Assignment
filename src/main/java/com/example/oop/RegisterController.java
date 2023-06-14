package com.example.oop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    // Declaring the variables needed for login
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater( () -> registerButton.requestFocus() );
    }

}
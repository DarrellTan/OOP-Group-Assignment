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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Label feedbackMessage;

    // Scene Swapping Attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean passwordMatch = false;

    // Variables for registration
    private String regUsr;
    private String regPw;
    private String regConfPw;


    public static String filePath = "src/main/resources/com/example/oop/admins.txt";

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void assignVariables(){
        regUsr = usernameField.getText();
        regPw = passwordField.getText();
        regConfPw = confirmPasswordField.getText();
    }

    public void register() throws IOException {
        if (regPw.equals(regConfPw)) {
            FileWriter file = new FileWriter(filePath, true);
            BufferedWriter out = new BufferedWriter(file);

            out.write("\n\n");
            out.write("Username: " + regUsr + "\n");
            out.write("Password: " + regPw);
            out.close();

            usernameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            feedbackMessage.setText("Successfully Registered User " + regUsr);

        }
        else {
            feedbackMessage.setText("Passwords do not match");
        }
    }


    public void buttonRegister() throws IOException {
        assignVariables();
        register();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater( () -> registerButton.requestFocus() );
    }
}
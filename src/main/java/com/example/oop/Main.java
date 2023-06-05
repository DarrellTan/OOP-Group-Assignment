package com.example.oop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            //Gets UI from XML File
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("Login.fxml"));
            Parent root = fxmlLoader.load();

            // Creates Scene for JavaFX Program
            Scene scene = new Scene(root, 400, 400);

            // Gets CSS file for Styling
            scene.getStylesheets().add(getClass().getResource("CSS/Login.css").toExternalForm());
            stage.setTitle("Cancelling Companies App");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

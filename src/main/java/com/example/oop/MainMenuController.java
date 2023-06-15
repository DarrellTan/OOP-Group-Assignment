package com.example.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToInsertData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("InsertData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("CSS/InsertData.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToViewCompanyData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewCompany.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/ViewCompany.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToUpdateData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UpdateData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("CSS/UpdateData.css").toExternalForm());
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
    public void switchToDeleteData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeleteData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/DeleteData.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCancelledCompanies(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewCancelled.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/ViewCancelled.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRegisterNewAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/DeleteData.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToViewPenalties(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewPenalties.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/ViewPenalties.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

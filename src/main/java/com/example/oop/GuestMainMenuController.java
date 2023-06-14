package com.example.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuestMainMenuController {
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
    public void switchToGuestViewCompany(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestViewCompany.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/GuestViewCompany.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToViewCancelledCompanies(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewCancelledCompanies.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("CSS/ViewCancelledCompanies.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/FirstPage.css").toExternalForm());
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
}

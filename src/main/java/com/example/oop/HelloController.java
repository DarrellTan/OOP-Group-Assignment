package com.example.oop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    // This class is part of the package
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
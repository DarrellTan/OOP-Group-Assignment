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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    // Declaring the variables needed for login
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label feedbackMessage;

    // Scene Swapping Attributes
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean passwordMatch = false;

    private String unameVal;
    private String passVal;

    public static String filePath = "src/main/resources/com/example/oop/admins.txt";

    public void assignVariables() {
        unameVal = usernameField.getText();
        passVal = String.valueOf(passwordField.getText());
        System.out.println(unameVal + passVal);
    }
    public void login(ActionEvent event) throws IOException {
        assignVariables();
        //Scanner to read through admins file containing usernames and pws
        Scanner admins = new Scanner(Login.class.getResourceAsStream("admins.txt"));

        //Empty arrays for usernames and passwords
        ArrayList<String> users = new ArrayList<String>();
        ArrayList<String> passes = new ArrayList<String>();

        //This loop reads through admin file and imports username and password to the empty arrays
        while (admins.hasNextLine()) {
            String adLine = admins.nextLine();

            if (!adLine.equals("")) {
                String admSub = adLine.substring(0, 4);

                if (admSub.equals("User")) {
                    String usr = adLine.substring(10);

                    users.add(usr);
                } else if (admSub.equals("Pass")) {
                    String pass = adLine.substring(10);

                    passes.add(pass);
                }
            }
        }
            for (int i=0; i<users.size(); i++) {
                if (unameVal.equals(users.get(i))
                        && passVal.equals(passes.get(i))) {
                    System.out.println("Credentials Test Pass");
                    root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root, 580, 460);
                    scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();

                    break;

                } else {
                    feedbackMessage.setText("Invalid Username or Password!");
                }
            }
    }

// Controller to switch scenes

    public void switchToMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            root = loader.load();

            // Create a new scene with the root node and dimensions
            scene = new Scene(root, 580, 460);
            scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());


            // Create a new stage
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Pollufree!"); // Set the title for the new stage


            // Hide the current stage
            Stage currentStage = (Stage) root.getScene().getWindow();
            currentStage.hide();

            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }



    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/FirstPage.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


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
        Platform.runLater( () -> loginButton.requestFocus() );
    }

}
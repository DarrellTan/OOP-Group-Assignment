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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class InsertData implements Initializable {
    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";

    // FXML Variables
    @FXML
    private TextField cFID;
    @FXML
    private TextField cFName;
    @FXML
    private TextField cFYearFounded;
    @FXML
    private TextField cFAnnualRevenue;
    @FXML
    private TextField cFPollutionIndex;
    @FXML
    private Label feedbackMessage;
    @FXML
    private Button back;

    // Variables for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Variables for Data
    private static int cID;
    private static String cName;
    private static int cYearFounded;
    private static double cRevenue;
    private static int cPI;
    private static ArrayList<String> CompanyData;

    public static ArrayList<String> callCompanyData() throws Exception{ //a class method to help save the current data in the text file
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<String> CompanyData = new ArrayList<String>();

        while((line = br.readLine()) != null){
            if("".equals(line)){ //If statement that helps to neutralize any empty lines in the txt file so that there will not be any empty indexes in the array
                continue;
            }
            else{
                CompanyData.add(line); //adds each line of text in the txt file into each index of the array list
            }
        }
        br.close();
        return CompanyData; //returns the ArrayList to the main Method
    }

    //clears the txt file to be replaced with the new data
    public static void flushCompanyTxt()throws Exception{
        FileWriter files = new FileWriter(filePath, false);
        files.flush();
        files.close();
    }

    public boolean checkForUniqueID() {
        for(int i = 0 ; i < CompanyData.size() ; i++){ //validates if the entered company ID already exists within the system
            if(CompanyData.get((i)).equals("Company " + cID)){
                System.out.println("Company ID already exists \n");
                return false;
            }
        }
        return true;
    }

    public void assignDataToVariables() {
        cID = Integer.parseInt(cFID.getText());
        cName= cFName.getText();
        cYearFounded = Integer.parseInt(cFYearFounded.getText());
        cRevenue = Double.parseDouble(cFAnnualRevenue.getText());
        cPI = Integer.parseInt(cFPollutionIndex.getText());
    }
    public void submitData() {
        CompanyData.add("Company " + cID);
        CompanyData.add("Company Name: " + cName);
        CompanyData.add("Year Founded: " + cYearFounded);
        CompanyData.add("Annual revenue: " + cRevenue);
        CompanyData.add("Pollution index: " + cPI);
    }
    public void writeToTxt(){
        int counter = 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < CompanyData.size(); i++) {
                writer.write(CompanyData.get(i));
                writer.write("\n");
                if(counter == 5){
                    writer.write("\n");
                    counter = 0;
                }
                counter++;
            }
            System.out.println("Data written to company_data.txt successfully.");
        }

        catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void success() {
        cFID.clear();
        cFName.clear();
        cFYearFounded.clear();
        cFAnnualRevenue.clear();
        cFPollutionIndex.clear();
        feedbackMessage.setText("Successfully Entered Company " + cID);
    }

    public void submitButton() throws Exception {
        assignDataToVariables();
        checkForUniqueID();
        if (checkForUniqueID() == false) {
            feedbackMessage.setText("Company ID already exists, try another one.");
        } else {
            submitData();
            flushCompanyTxt();
            writeToTxt();
            success();
        }
    }

    // Controller to switch scenes
    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater( () -> back.requestFocus() );
        try {
            CompanyData = callCompanyData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

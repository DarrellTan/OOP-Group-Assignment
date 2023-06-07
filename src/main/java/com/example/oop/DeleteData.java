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

public class DeleteData implements Initializable {
    @FXML
    private TextField cFID;
    @FXML
    private Label feedbackMessage;
    @FXML
    private Button back;

    private int deleteCID;

    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";
    public static boolean existenceOfID;

    // Variables for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Arrays for Storage
    private static ArrayList<String> CompanyData;
    private static ArrayList<String> UpdatedCompanyData;

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
    public static void flushCompanyTxt() throws Exception{
        FileWriter files = new FileWriter(filePath, false);
        files.flush();
        files.close();
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
    public static void deleteCompany(ArrayList<String> companyData, int companyID) {
        int index = -1;

        for (int i = 0; i < companyData.size(); i++) {
            if (companyData.get(i).startsWith("Company " + companyID)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            companyData.subList(index, index + 5).clear();
            System.out.println("Company details deleted successfully.");
        } else {
            System.out.println("Company ID not found.");
            existenceOfID = false;
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater( () -> back.requestFocus() );
        try {
            CompanyData = callCompanyData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UpdatedCompanyData = new ArrayList<String>();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void success() {
        cFID.clear();
        feedbackMessage.setText("Successfully Deleted Company " + deleteCID);
    }

    public void idNonExistant() {
        cFID.clear();
        feedbackMessage.setText("Comapny " + deleteCID + " does not exist.");
    }


    public void deleteData() throws Exception {
        deleteCID = Integer.parseInt(cFID.getText());
        deleteCompany(CompanyData, deleteCID);
        flushCompanyTxt();
        writeToTxt();
        if (existenceOfID != false) {
            success();
        } else {
            idNonExistant();
        }

    }
}

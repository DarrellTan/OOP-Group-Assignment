package com.example.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestViewBonusController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea textArea;

    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";
    public static String grantsFilePath = "src/main/resources/com/example/oop/grants.txt";

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestMainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/GuestMainMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private static String readFileContent(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayList<String> callCompanyData() throws FileNotFoundException, IOException { //a class method to help save the current data in the text file
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

    public static void writingToFile() throws IOException {
        ArrayList<ArrayList<String>> companiesData = new ArrayList<ArrayList<String>>(); //This ArrayList will store each companies data in seperate ArrayList

        int stopper = 5;

        while (stopper<= callCompanyData().size()) {
            ArrayList<String> singleCompanyData = new ArrayList<String>(); //This ArrayList is to seperate each companies data

            for (int i=stopper-5; i<stopper; i++) {
                singleCompanyData.add(callCompanyData().get(i)); //This will add item index 0-5 from callCompanyData to a new ArrayList
            }

            companiesData.add(singleCompanyData); //This adds the whole singleCompanyData ArrayList to the new ArrayList
            stopper += 5;
        }

        //fr creates a connection to an empty penalties file
        FileWriter fr = new FileWriter(grantsFilePath);
        //br helps write in the chosen companies' data in that file
        BufferedWriter br = new BufferedWriter(fr);

        br.write("RM5,000 GRANT FOR API BETWEEN 51-100 \n\n");

        for (int i=0; i<companiesData.size(); i++) {
            int pollIndex = Integer.parseInt(companiesData.get(i).get(4).substring(17));

            if (pollIndex > 51 && pollIndex <= 100) {

                for (int j=0; j<companiesData.get(i).size(); j++) {
                    br.write(companiesData.get(i).get(j) + "\n");
                }

                br.write("\n");
            }
        }

        br.write("RM15,000 GRANT FOR API UNDER 50 \n\n");

        for (int i=0; i<companiesData.size(); i++) {
            int pollIndex = Integer.parseInt(companiesData.get(i).get(4).substring(17));

            if (pollIndex < 50) {

                for (int j=0; j<companiesData.get(i).size(); j++) {
                    br.write(companiesData.get(i).get(j) + "\n");
                }

                br.write("\n");
            }
        }

        br.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            writingToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileContent = readFileContent(grantsFilePath);
        textArea.setText(fileContent);
    }
}
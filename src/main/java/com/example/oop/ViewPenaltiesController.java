package com.example.oop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPenaltiesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea textArea;
    @FXML
    private TextField searchBar;

    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";
    public static String penaltiesFilePath = "src/main/resources/com/example/oop/penalties.txt";
    public static String searchFilePath = "src/main/resources/com/example/oop/search.txt";

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 580, 460);
        scene.getStylesheets().add(getClass().getResource("CSS/MainMenu.css").toExternalForm());
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

    public void refresh(){
        String fileContent = readFileContent(penaltiesFilePath);
        textArea.setText(fileContent);
    }

    public void searchButton() throws IOException {
        ArrayList<ArrayList<String>> companiesData = new ArrayList<ArrayList<String>>(); //This ArrayList will store each companies data in seperate ArrayList

        int stopper = 5;

        while (stopper <= callCompanyData().size()) {
            ArrayList<String> singleCompanyData = new ArrayList<String>(); //This ArrayList is to seperate each companies data

            for (int i = stopper - 5; i < stopper; i++) {
                singleCompanyData.add(callCompanyData().get(i)); //This will add item index 0-5 from callCompanyData to a new ArrayList
            }
            companiesData.add(singleCompanyData); //This adds the whole singleCompanyData ArrayList to the new ArrayList
            stopper += 5;
        }

        //fr creates a connection to an empty penalties file
        FileWriter fr = new FileWriter(searchFilePath);
        //br helps write in the chosen companies' data in that file
        BufferedWriter br = new BufferedWriter(fr);

        String search = searchBar.getText();

        for (int i=0; i<companiesData.size(); i++) { //For int i smaller than the number of companies in our database
            for (int j=0; j<companiesData.get(i).size(); j++) { //For int j smaller than the number of data for each company
                if (companiesData.get(i).get(j).contains(search)) { //If any of the data contains user's searched word
                    for (int k=0; k<companiesData.get(i).size(); k++) { //For int k smaller than the number of data for each company
                        br.write(companiesData.get(i).get(k) + "\n"); //Write all the data of that specific company containing the searched word
                    }
                } else {
                    continue;
                }
                br.write("\n"); //Just to make it look nicer hehe
                break;
            }
        }
        br.close();
        String newFileContent = readFileContent(searchFilePath);
        textArea.setText(newFileContent);
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
        FileWriter fr = new FileWriter(penaltiesFilePath);
        //br helps write in the chosen companies' data in that file
        BufferedWriter br = new BufferedWriter(fr);

        br.write("RM10,000 PENALTY FOR API EXCEEDING 200 \n\n");

        for (int i=0; i<companiesData.size(); i++) {
            //Company's Air Pollution Index
            int pollIndex = Integer.parseInt(companiesData.get(i).get(4).substring(17));

            //If pollution index 200 - 250
            if (pollIndex > 200) {

                //Add that company to penalty list
                for (int j=0; j<companiesData.get(i).size(); j++) {
                    br.write(companiesData.get(i).get(j) + "\n");
                }

                br.write("\n"); //Spaces to make it look nice hehe
            }
        }

        br.write("RM5,000 PENALTY FOR API BETWEEN 151-200 \n\n");

        for (int i=0; i<companiesData.size(); i++) {
            int pollIndex = Integer.parseInt(companiesData.get(i).get(4).substring(17));

            if (pollIndex > 150 && pollIndex <= 200) {

                for (int j=0; j<companiesData.get(i).size(); j++) {
                    br.write(companiesData.get(i).get(j) + "\n");
                }

                br.write("\n");
            }
        }

        br.write("RM2,500 PENALTY FOR API BETWEEN 101-150 \n\n");

        for (int i=0; i<companiesData.size(); i++) {
            int pollIndex = Integer.parseInt(companiesData.get(i).get(4).substring(17));

            if (pollIndex > 100 && pollIndex <= 150) {

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
        String fileContent = readFileContent(penaltiesFilePath);
        textArea.setText(fileContent);
    }
}
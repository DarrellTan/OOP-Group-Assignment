/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.oop;

import java.io.*;
import java.util.*;


public class cancelLicense {

    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";
    public static String cancellationFilePath = "src/main/resources/com/example/oop/cancellationCandidates.txt";


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
    
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> companiesData = new ArrayList<ArrayList<String>>(); //This ArrayList will store each companies data in seperate ArrayList
        
        int stopper = 5;
        
        while (stopper<=callCompanyData().size()) {
            ArrayList<String> singleCompanyData = new ArrayList<String>(); //This ArrayList is to seperate each companies data
            
            for (int i=stopper-5; i<stopper; i++) {
                singleCompanyData.add(callCompanyData().get(i)); //This will add item index 0-5 from callCompanyData to a new ArrayList
            }
            
            companiesData.add(singleCompanyData); //This adds the whole singleCompanyData ArrayList to the new ArrayList
            stopper += 5;
        }
        
        //fr creates a connection to an empty Cancellation Candidates file
        FileWriter fr = new FileWriter(cancellationFilePath);
        //br helps write in the chosen companies' data in that file
        BufferedWriter br = new BufferedWriter(fr);
        
        for (int i=0; i<companiesData.size(); i++) {
            //Company's Air Pollution Index
            int pollIndex = Integer.valueOf(companiesData.get(i).get(4).substring(17));
            
            //If pollution index is higher than 250
            if (pollIndex > 250) {
                
                //Add that company to cancellation list
                for (int j=0; j<companiesData.get(i).size(); j++) {
                    br.write(companiesData.get(i).get(j) + "\n");
                }
                
                br.write("\n"); //Spaces to make it look nice hehe
            }
        }
        
        br.close();
    }

}

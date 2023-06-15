/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.oop;

import java.io.*;
import java.util.*;

public class Search {
    public static String filePath = "src/main/resources/com/example/oop/company_data.txt";


    public static ArrayList<String> callCompanyData() throws FileNotFoundException, IOException{ //a class method to help save the current data in the text file
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
        
        while (stopper<= callCompanyData().size()) {
            ArrayList<String> singleCompanyData = new ArrayList<String>(); //This ArrayList is to seperate each companies data
            
            for (int i=stopper-5; i<stopper; i++) {
                singleCompanyData.add(callCompanyData().get(i)); //This will add item index 0-5 from callCompanyData to a new ArrayList
            }
            companiesData.add(singleCompanyData); //This adds the whole singleCompanyData ArrayList to the new ArrayList
            stopper += 5;
        }
        System.out.println(companiesData);
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Search:");
        String search = sc.nextLine();
        
        for (int i=0; i<companiesData.size(); i++) { //For int i smaller than the number of companies in our database
            for (int j=0; j<companiesData.get(i).size(); j++) { //For int j smaller than the number of data for each company
                if (companiesData.get(i).get(j).contains(search)) { //If any of the data contains user's searched word
                    for (int k=0; k<companiesData.get(i).size(); k++) { //For int k smaller than the number of data for each company
                        System.out.println(companiesData.get(i).get(k)); //Print all the data of that specific company containing the searched word
                    }
                } else {
                    continue;
                }
                System.out.println(""); //Just to make it look nicer hehe
            }
        }
    }
    
}

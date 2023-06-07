package com.example.oop;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class OOP_Assignment_Insert {
    public static String filePath = "src/main/resources/com/example/oop/company_data.txt"; // Replace file path according to where your txt file is stored
    //declares this string as a global variable to be used in all methods and classes
    
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

    static void MenuSelection(){ //A static method to print the menu selection without having to keep repeating it
        System.out.println("\nSelect number to access respective menues.");
        System.out.println("1: Insert data on companies");
        System.out.println("2. Access data on companies");
        System.out.println("3. Update company data");
        System.out.println("4. Log out");
    }

    //clears the txt file to be replaced with the new data
    static void FlushCompanyTxt()throws Exception{ 
        FileWriter files = new FileWriter(filePath, false);
        files.flush();
        files.close();
    }
 
    public static void main(String[] args) throws Exception {
        //Variables to be used throughout code
        Scanner input = new Scanner(System.in);
        boolean loggedIn = true;
        int counter=1;
 
        //ArrayLists to be used throughput the code
        ArrayList<String> CompanyData = OOP_Assignment_Insert.callCompanyData();
        
        //While loop to keep looping after having cleared each option
        while(loggedIn){ 
            MenuSelection();
            System.out.print("Please enter in Menu Option: ");
            int menuInput =input.nextInt(); 
            System.out.println();
            switch (menuInput){ //switch statement for menu input

                case 1: // Inserting new company Data into the TXT file
                    
                    //Variables to be used when inserting into company_data.txt
                    int totalCompanyAdded = 0;
                    int cID;
                    String cName;
                    int cYearFounded;
                    double cRevenue;
                    int cPI;
                    boolean cIDvalidate = true;

                    System.out.print("Insert number of new company files to be added: "); //gathers how many companies are going to be added
                    totalCompanyAdded = input.nextInt();
                    
                    while(cIDvalidate){ //will keep asking to enter company details until the entered company ID is a new company ID rather then one existsing in the system
                        System.out.println("=== INSERTING COMPANY DATA ===");
                        for(int x = 0 ; x < totalCompanyAdded ; x++){
                            System.out.println("Insert details for company " + (x + 1) + ":");

                            // Asks for the details of the companies from the government
                            System.out.print("Company ID: ");
                            cID = input.nextInt();

                            if(CompanyData.isEmpty()){ //checks if the array is empty and if it is empty will not need to validate for Company ID
                                cIDvalidate=false;
                            }
                            
                            for(int i = 0 ; i < CompanyData.size() ; i++){ //validates if the entered company ID already exists within the system
                                if(CompanyData.get((i)).equals("Company "+cID)){
                                    System.out.println("Company ID already exists \n");
                                    x--; //reduces the X in the for loop by -1 so that it will continue to ask for the current company number id code
                                    cIDvalidate=true;
                                    break;
                                }
                                else{ //if Company ID entered is not in the system will change validation boolean to false in order to continue
                                    cIDvalidate = false;
                                }
                            }
                            if(cIDvalidate == false){ //if returned boolean is false will then proceed to ask for company details
                                System.out.print("Name: ");
                                input.nextLine();
                                cName= input.nextLine();

                                System.out.print("Year founded: ");
                                cYearFounded = input.nextInt();

                                System.out.print("Annual revenue: ");
                                cRevenue = input.nextDouble();
                        
                                System.out.print("Pollution Index: ");
                                cPI = input.nextInt();

                                //Adds it into the current company txt Array

                                CompanyData.add("Company "+cID);
                                CompanyData.add("Company Name: "+cName);
                                CompanyData.add("Year Founded: "+cYearFounded);
                                CompanyData.add("Annual revenue: "+cRevenue);
                                CompanyData.add("Pollution index: "+cPI);
                            }
                        }
                    }

                    FlushCompanyTxt(); //clears the text file to be rewritten with the new data

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

                    break;
                
                case 2: // showcasing the current company data in the TXT file
                    System.out.println("=== SHOWING COMPANY LIST ===");
                    if(CompanyData.isEmpty()){ //checks if the array is empty and if it is empty will not need to validate for Company ID
                        System.out.println("No Company details to show.");
                    }
                    else{
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(filePath));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            reader.close();
                        }   
                        catch (IOException e) {
                        System.out.println("An error occurred while reading the file.");
                        e.printStackTrace();
                        }
                    }

                    break;
                
                case 3: // Updating current company data in the TXT file
                    if(CompanyData.isEmpty()){ //checks if the array is empty and if it is empty will not need to validate for Company ID
                        System.out.println("There are no company details to enter");
                        break;
                    }

                    else{
                        ArrayList<String> UpdatedCompanyData = new ArrayList<String>(); //using an array list as items can be appended without need of increasing array size.
                        
                        
                        int pointer=0; //sets a variable to count for the updated company info array
                
                        System.out.print("Enter the company ID to update: ");
                        int updateCID = input.nextInt();
                        input.nextLine();

                        if(CompanyData.contains("Company "+updateCID)){
                            int IndexOfCID = CompanyData.indexOf("Company "+updateCID); //gets the index of the company ID in the CompanyData Array
                            int FirstIndex = IndexOfCID +1; //gets the first index of the range to update the details of the company, 
                                                            //IndexOfCID tells us the index of the CID but we need to change from the Company Name to the Pollution index so we +1
                            
                            int LastIndex = IndexOfCID +5; //We add 5 to it to get the last range of the index.

                            System.out.println("=== UPDATING COMPANY DETAILS ===");
                            for(int i = IndexOfCID ; i < LastIndex ; i++){
                                System.out.println(CompanyData.get(i));
                            }

                            System.out.print("\nEnter updated company name: ");
                            String updatedName = input.nextLine();

                            System.out.print("Enter updated year founded: ");
                            int updatedYear = input.nextInt();
                            input.nextLine(); // Consume the newline character

                            System.out.print("Enter updated annual revenue: ");
                            int updatedRevenue = input.nextInt();
                            input.nextLine(); // Consume the newline character

                            System.out.print("Enter updated pollution index: ");
                            int updatedPolIndex = input.nextInt();
                            input.nextLine(); // Consume the newline character

                            //Appends all the data into a temp array to then transfer into the original Array which stores the Txt company details.
                            UpdatedCompanyData.add("Company Name: "+updatedName);
                            UpdatedCompanyData.add("Year Founded: "+updatedYear);
                            UpdatedCompanyData.add("Annual revenue: "+updatedRevenue);
                            UpdatedCompanyData.add("Pollution index: "+updatedPolIndex);
                            
                            for(int i = FirstIndex ; i < LastIndex ; i++){
                                CompanyData.set(i, UpdatedCompanyData.get(pointer));
                                pointer++;
                            }
                            
                            FlushCompanyTxt(); //clears the text file to be rewritten with the new data

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
                        else{
                            System.out.println("This company ID does not exist.");
                        }
                    }
                    

                    break;

                case 4: // Ending the program
                    System.out.println("Have a nice day");  
                    loggedIn = false;
                    break;
                
                default: //defaulting to show that incorrect menu option was entered
                    System.out.println("Incorrect Menu option");
            }
        }
        input.close();
    }
}

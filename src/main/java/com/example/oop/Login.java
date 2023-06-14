package com.example.oop;

import java.util.*;
import javax.swing.*;
import java.io.*;

public class Login {
    
    //This class registers new admins
    public static void register() throws Exception {
        //Input field for registration
        JTextField inputUname = new JTextField();
        JPasswordField inputPass = new JPasswordField();
        JPasswordField inputConfPass = new JPasswordField();

        inputPass.setEchoChar('*');
        inputConfPass.setEchoChar('*');

        Object[] inputs = {"Username", inputUname, "Password", inputPass, "Confirm Password", inputConfPass};

        int register = JOptionPane.showConfirmDialog(
                null,
                inputs,
                "Enter Username & password",
                JOptionPane.OK_CANCEL_OPTION);

        //Confirms whether user pressed "OK" or "Cancel"
        if (register == JOptionPane.YES_OPTION) {
            String regUsr = inputUname.getText();
            String regPw = String.valueOf(inputPass.getPassword());
            String regConfPw = String.valueOf(inputConfPass.getPassword());
            
            //Confirms whether the passwords match, if yes, registration is success
            if (regPw.equals(regConfPw)) {
                FileWriter file = new FileWriter("src/login/admins.txt", true);
                BufferedWriter out = new BufferedWriter(file);

                out.write("\n\n");
                out.write("Username: " + regUsr + "\n");
                out.write("Password: " + regPw);
                out.close();
                
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Password doesn't match!",
                    "Try Again..",
                    JOptionPane.ERROR_MESSAGE);
                
                register(); //Register again due incorrect "Confirm Password"
            }
        }
    }
    
    //Selecting "1 - Admin Login" will run this class
    public static void login() {
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
        
        //This is username and pw input fields
        JTextField inputUname = new JTextField();
        JPasswordField inputPass = new JPasswordField();

        inputPass.setEchoChar('*');

        Object[] inputs = {"Username", inputUname, "Password", inputPass};

        int login = JOptionPane.showConfirmDialog(
                null,
                inputs,
                "Enter Username & password",
                JOptionPane.OK_CANCEL_OPTION);
        
        //Checks whether the user pressed "OK" or "Cancel"
        if (login == JOptionPane.YES_OPTION) {
            //If "OK", this checks whether the input details match our database
            String unameVal = inputUname.getText();
            String passVal = String.valueOf(inputPass.getPassword());

            for (int i=0; i<users.size(); i++) {
                if (unameVal.equals(users.get(i))
                        && passVal.equals(passes.get(i))) {
                    System.out.println("Welcome " + users.get(i) + "!!");
                    
                    break;
                    
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid Username or Password!!",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        //To receive user input
        Scanner sc = new Scanner(System.in);
        
        //Loop runs as long as user don't press 0 for QUIT or other system errors
        while (true) {
            System.out.println("\nEnter...");
            System.out.println("0 - Quit Program");
            System.out.println("1 - Admin Login");
            System.out.println("2 - Admin Register");
            System.out.println("3 - Continue as User");
            
            int act1 = sc.nextInt();
            
            //Checks user integer input received from scanner act1
            switch (act1) {
                case 0: //Quits the whole Java program
                    System.out.println("Quitting program...");
                    System.exit(0);

                case 1: //Runs the login() class
                    login();
                    break;

                case 2:
                    register(); //Runs the login() class
                    break;

                default: //If user enters integre other than 0,1,2
                    System.out.println("Error!! Try again...\n");
                    main(null);
                    break;
            }
        }
    }
    
}

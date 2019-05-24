package com.company;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

//    database connection
    private static String url = "jdbc:postgresql://138.197.107.95:5432/group2";
    private static String user = "student";
    private static String password = "C0d3Cr3w";

//    connection function
    private static Connection connect() throws SQLException {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected!");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

//    initial callmailid
    public static void main(String[] args) {














        welcomePrompt();
    }

//    welcome prompt: register or sign in
    private static void welcomePrompt(){
        Scanner read = new Scanner(System.in);
        System.out.println("Welcome to Code School Mail!");

        System.out.println("Please choose an option below:");
        System.out.println("------------------------------------");
        System.out.println("A. Create a new user");
        System.out.println("B. Sign In");
        String userInput = read.next();

        while(!userInput.toLowerCase().equals("a") && !userInput.toLowerCase().equals("b")){
            System.out.println("Please choose an option below:");
            System.out.println("------------------------------------");
            System.out.println("A. Create a new user");
            System.out.println("B. Sign In");
            userInput = read.next();
        }

        if(userInput.toLowerCase().equals("a")){
            newUserPrompt();
        }
        else if(userInput.toLowerCase().equals("b")){
            signInPrompt();
        }

    }

//    register a user
    private static void newUserPrompt(){
        Scanner read = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Enter your username");
        String userName = read.nextLine();
        //todo: perform validation for unique username (check against user table records)
        System.out.println("Enter your password");
        String passWord = read.nextLine();
        try{
            newUser.newUser(userName,passWord);
        }
        catch (SQLException e){
            e.getMessage();
        }
        System.out.println("Username: " + userName + " Password: " + passWord);
        System.out.println("You will now be directed to sign in");
        System.out.println("------------------------------------");
        signInPrompt();
    }

//    sign in an existing user
    private static void signInPrompt(){
        Scanner read = new Scanner(System.in);
        User newUser = new User();

        System.out.println("Enter your username");
        String userName = read.nextLine();
        System.out.println("Enter your password");
        String passWord = read.nextLine();

        //todo: perform validation for correct sign in information (grab user from database using user class method)
        System.out.println(newUser.grabUser(userName).get(0));
        int loggedInUserID = (int)newUser.grabUser(userName).get(0);
        System.out.println("------------------------------------");
        System.out.println("Welcome " + userName);
        System.out.println("Please choose an option below:");
        System.out.println("------------------------------------");
        System.out.println("A. Check Mail");
        System.out.println("B. Send Mail");
        System.out.println("C. LogOut");
        String userInput = read.nextLine();

        while(!userInput.toLowerCase().equals("a") && !userInput.toLowerCase().equals("b") && !userInput.toLowerCase().equals("c")){
            System.out.println("Please choose an option below:");
            System.out.println("------------------------------------");
            System.out.println("A. Check Mail");
            System.out.println("B. Send Mail");
            System.out.println("C. LogOut");
            userInput = read.next();
        }

        if(userInput.toLowerCase().equals("a")){
            checkMail(loggedInUserID);
        }
        else if(userInput.toLowerCase().equals("b")){
            sendMail(loggedInUserID);
        }
        else if(userInput.toLowerCase().equals("c")){
            logOut();
        }
    }

//    check mail
    private static void checkMail(int loggedInUserID){
        Message newMessage = new Message();
        System.out.println("Choose a mail item");
        newMessage.listMail(loggedInUserID);
        ArrayList<ArrayList> mailItemArray = new ArrayList<>();
        //todo: put message records into an array and loop through those messages(maybe array in array) PK
        //todo: when a message record is chosen (by ID?) give more options
        //checkMail options: Show message info in full (not an option), option to delete, option to go back
        signInPrompt();
    }

//    send mail
    private static void sendMail(int loggedInUserID) {
        Scanner read = new Scanner(System.in);
        Message newMessage = new Message();
        User newUser = new User();

        System.out.println("Choose a recipient");
        try {
            newUser.selectUser();
        } catch (SQLException e) {
            e.getMessage();
        }

        int recipientID = read.nextInt();

        System.out.println("Subject Line: ");
        String tester = read.nextLine();
        String subjectLine = read.nextLine();

        System.out.println("Body: ");
        String body = read.nextLine();

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        System.out.println("To: " + recipientID + " From: " + loggedInUserID + " Subject: " + subjectLine + " Body: " + body);

        newMessage.createMessage(subjectLine, body, loggedInUserID, recipientID, currentTimestamp);

        System.out.println("Message Sent!");
        //todo: create message using vale
        // s but deal with variable types
        signInPrompt();
    }

    private static void logOut(){
        welcomePrompt();
    }
}

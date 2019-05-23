package com.company;

import java.sql.*;

public class Message {
    private final String url = "postgresql://138.197.107.95:5432/group2";
    private final String user = "student";
    private final String password = "C0d3Cr3w";

    // Convenience method to get a JDBC cnxn
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //    setting up message properties
    private int mailID;
    private String messageSubject;
    private String messageBody;
    private Boolean isImportant;
    private int fromUserID;
    private int sentUserID;
    private int dateTimeSent;


    public static void displayResults(ResultSet rs)throws SQLException {
        while (rs.next()) {
            System.out.print("mailID: " + rs.getString(1));
            System.out.print("messageSubject: " + rs.getString(2));
            System.out.print(" messageBody: " + rs.getString(3));
            System.out.println(" fromUserID: " + rs.getString(4));
            System.out.println(" sentUserID: " + rs.getString(5));
            System.out.println(" dateTimeSent: " + rs.getString(6));
        }
    }

    //    Create Message records from the database
    public static void createMessage(int messageID, String messageSubject, String messageBody, int fromUserID, int fromSentID, int dateTimeSent){
        String SQL =
                "INSERT INTO ccmail (messageID, messageSubject, messageBody, fromUserID, sentUserID, dateTimeSent) "+
                        " VALUES (?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1,messageID);
            pstmt.setString(2,messageSubject);
            pstmt.setString(3,messageBody);
            pstmt.setInt(4,fromUserID);
            pstmt.setInt(5,fromSentID);
            pstmt.setInt(6,dateTimeSent);
            ResultSet rs = pstmt.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //    SELECT all Message records from the database
    public static void listMail(){
        String SQL =
                "SELECT * " + "FROM ccmail";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){

            ResultSet rs = pstmt.executeQuery();
            displayResults(rs);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }





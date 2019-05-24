package com.company;

import java.sql.*;

public class Message {
    private static String url = "jdbc:postgresql://138.197.107.95:5432/group2";
    private static String user = "student";
    private static String password = "C0d3Cr3w";

    // Convenience method to get a JDBC cnxn
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //    setting up message properties
    private int mailID;
    private String subject;
    private String body;
    private Boolean isImportant;
    private int fromUserID;
    private int sentUserID;
    private int dateTimeSent;


    public static void displayResults(ResultSet rs)throws SQLException {
        while (rs.next()) {
            System.out.print("mailid: " + rs.getString(1));
            System.out.print("subject: " + rs.getString(2));
            System.out.print(" body: " + rs.getString(3));
            System.out.println(" fromuserid: " + rs.getString(4));
            System.out.println(" sentuserid: " + rs.getString(5));
            System.out.println(" datetimesent: " + rs.getString(6));
        }
    }

    //    Create Message records from the database
    public static void createMessage(int mailID, String subject, String body, int fromUserID, int fromSentID, int dateTimeSent){
        String SQL =
                "INSERT INTO ccmail (mailid, subject, body, fromUserID, sentUserID, dateTimeSent) "+
                        " VALUES (?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setInt(1,mailID);
            pstmt.setString(2,subject);
            pstmt.setString(3,body);
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
}





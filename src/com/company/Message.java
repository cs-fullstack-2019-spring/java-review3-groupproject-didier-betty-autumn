package com.company;

import java.sql.*;
//connection
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

//display results shows mail in the db with id, subject, to and from
    public static void displayResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.print("mailid: " + rs.getString(1));
            System.out.println(" subject: " + rs.getString(2));
//            System.out.print(" body: " + rs.getString(3));
            System.out.print("fromuserid: " + rs.getString(5));
            System.out.println(" sentuserid: " + rs.getString(6));
//            System.out.println(" datetimesent: " + rs.getString(7));
            System.out.println("----------------------");
        }
    }

    //    Create Message records from the database
    public static void createMessage(String subject, String body, int fromUserID, int fromSentID, Timestamp dateTimeSent) {
        String SQL =
                "INSERT INTO ccmail ( subject, body, fromUserID, sentUserID, datetimesent) " +
                        " VALUES (?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, subject);
            pstmt.setString(2, body);
            pstmt.setInt(3, fromUserID);
            pstmt.setInt(4, fromSentID);
            pstmt.setTimestamp(5, dateTimeSent);
            int rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //    List all Message records from the database
    public static void listMail(int loggedInUserID) {
        String SQL =
                "SELECT * " + "FROM ccmail WHERE sentuserid=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, loggedInUserID);
            ResultSet rs = pstmt.executeQuery();
            displayResults(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
//    allows users to select specific mail by id
    public static void userMail(int selectMail) {
        String SQL =
                "SELECT * " + "FROM ccmail WHERE mailid=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, selectMail);
            ResultSet rs = pstmt.executeQuery();
            selectedResults(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
//    displays all details of specific mail item chosen by user
    public static void selectedResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.print("mailid: " + rs.getString(1));
            System.out.println(" subject: " + rs.getString(2));
            System.out.print(" body: " + rs.getString(3));
            System.out.print("fromuserid: " + rs.getString(5));
            System.out.println(" sentuserid: " + rs.getString(6));
            System.out.println(" datetimesent: " + rs.getString(7));
            System.out.println("----------------------");
        }
    }

}






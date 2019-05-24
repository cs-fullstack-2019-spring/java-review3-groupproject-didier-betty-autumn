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


    public static void displayResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("Item ID: " + rs.getString(1));
            System.out.println("Subject: " + rs.getString(2));
            System.out.println(" body: " + rs.getString(3));
            System.out.println(" From: " + rs.getString(5));
            System.out.print(" To: " + rs.getString(6));
            System.out.print(" TimeStamp: " + rs.getString(7));
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

    //    SELECT all Message records from the database
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
}





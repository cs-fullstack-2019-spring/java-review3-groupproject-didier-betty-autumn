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

    public void setmailID(int newMailID) {
        this.mailID = newMailID;
    }

    public void setMessageSubject(String newMessageSubject) {
        this.messageSubject = newMessageSubject;
    }

    public void setMessageBody(String newMessageBody) {
        this.messageBody = newMessageBody;
    }

    public void setFromUserID(int newFromUserID) {
        this.fromUserID = newFromUserID;
    }

    public int getDateTimeSent() {
        return dateTimeSent;
    }

    public int getSentUserID() {
        return sentUserID;
    }

    //    List all Mail
    private void listMail() throws SQLException {
        String selectSQL = "SELECT * FROM ccmail";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connect();
            pstmt = conn.prepareStatement(selectSQL);

            ResultSet rs = pstmt.executeQuery();
            System.out.println("List Messages");
            while (rs.next()) {
                System.out.println(String.format("%s\t%s\t%s\t%s\t%s",
                        rs.getString("mailID"),
                        rs.getString("messageSubject"),
                        rs.getString("messageBody"),
                        rs.getInt("fromUserID"),
                        rs.getInt("fromSentID"),
                        rs.getInt("dateTimeSent")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {

            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        }
    }


        }


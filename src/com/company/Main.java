package com.company;
import java.sql.*;
public class Main {

    //    database connection
    private static String url = "jdbc:postgresql://138.197.107.95:5432/group2";
    private static String user = "student";
    private static String password = "C0d3Cr3w";

    private static Connection connect() throws SQLException {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
            connect();
        }
        catch (SQLException e){
            e.getMessage();
        }
    }
}

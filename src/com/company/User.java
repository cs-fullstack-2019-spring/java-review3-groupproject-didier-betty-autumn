package com.company;
import java.sql.*;

public class User {
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

    public void newUser(String username, String password) throws SQLException{
        String insertSQL = "INSERT INTO usertable (username,password) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement createnewuser = null;

        try{
            conn = connect();
            createnewuser = conn.prepareStatement(insertSQL);
            createnewuser.setString(1,username);
            createnewuser.setString(2,password);
            int rs = createnewuser.executeUpdate();


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            if(createnewuser != null){
                createnewuser.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }

    public void selectUser(String username, String password) throws SQLException{
        String selectSQL = "SELECT * from usertable";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            resultsFromTable(rs);

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void resultsFromTable(ResultSet rs) throws SQLException{
        while (rs.next()) {
            System.out.println("Username: "+rs.getString(2));
            System.out.println("Username: "+rs.getString(3));
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bewick
 */
public class DBManager {

    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:derby:SudokuDB;create=true";

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    //Returns the conn Connection variable
    public Connection getConnection() {
        return this.conn;
    }

    //Establishes connection to the database using the supplied URL, username, and password
    public void establishConnection() {
        if (this.conn == null) {
            try {              
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " connected...");
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        }
    }

    //Closes the connecttion to the database
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

   
    

}

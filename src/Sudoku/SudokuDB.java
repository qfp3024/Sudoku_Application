/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bewick
 */
public class SudokuDB {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public SudokuDB() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    //Checks if the database is connected, if not throws an error
    public void connectSudokuDB() {
        if (conn == null) {
            System.err.println("Connection to database has not been established");
        }

    }

    //Checks if the Users table exists, if not,
    //defines and runs statements to create the Users table with data inserted
    public void createAbsentTable() {
        if (!tableExists("Users")) {
            try {
                statement = conn.createStatement();
                String sqlCreateTable = "CREATE TABLE USERS (USERNAME VARCHAR(20), PASSWORD VARCHAR(20), SCORE DOUBLE)";
                String sqlInsertData1 = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES ('User', 'test', 44.58)";
                String sqlInsertData2 = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES ('@notherU5er', 'password', 25.84)";

                statement.executeUpdate(sqlCreateTable);
                statement.executeUpdate(sqlInsertData1);
                statement.executeUpdate(sqlInsertData2);
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        }
    }

    //Gets the metadata and attempts to get tables, if successfull returns true, else returns false
    public boolean tableExists(String table) {
        boolean exists = false;
        try {
            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet tables = metadata.getTables(null, null, table.toUpperCase(), null);
            exists = tables.next();
            tables.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return exists;
    }

    //Checks the database is connected, if so defines and runs a statement to retrieve the usernames and passwords
    //of each user in the Users table, and checks if the retrieved data matches the supplied data
    //checks if the entered username matches any in database, if it does, compares passwords, returns result
    //if username doesn't appear in the database, run addUser method and set userCheck to false
    //Returns usercheck
    public boolean checkName(String username, String password) {
        boolean userCheck = false;
        boolean newUser = true;
        try {
            if (conn != null) {
                String query = "SELECT USERNAME, PASSWORD FROM USERS";
                PreparedStatement statement = conn.prepareStatement(query);

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String pass = rs.getString("password");
                    String user = rs.getString("username");

                    if (username.compareTo(user) == 0) {
                        newUser = false;
                        userCheck = password.compareTo(pass) == 0;
                    }
                }
                if (newUser) {
                    addUser(username, password);
                    userCheck = true;
                }
            } else {
                System.out.println("No database connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return userCheck;
    }

    //Defines statement to insert supplied username and password into Users table with default score of 0.00
    //Executes defined statement, prints SQL error if anything fails
    public void addUser(String username, String password) {
        try {
            String insertQuery = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setDouble(3, 0.00);

            insertStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
    }

    //Defines statement to get username and score from Users, but only the username and score that 
    //matches the supplied username. Executes statement, then sets score equal to retrieved score, returns score
    //Prints message if any errors or if the database isn't connected
    public double getUserScore(String username) {
        double score = 0;
        try {
            if (conn != null) {
                String query = "SELECT USERNAME, SCORE FROM USERS WHERE USERS.USERNAME = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, username);

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    score = rs.getDouble("SCORE");
                }
            } else {
                System.out.println("No database connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return score;
    }

    //Defines statement to update pre-exsiting data in Users table,
    //Changing the score of the supplied user to be equal to the supplied score
    //Executes statement, prints error messages if any errors occur
    public void updateUserScore(double score, double time, String username) {
        try {
            if (conn != null) {
                String query = "UPDATE USERS SET SCORE = ? WHERE USERNAME = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setDouble(1, score);
                statement.setString(2, username);

                statement.executeUpdate();
            } else {
                System.out.println("No database connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
    }

    //Defines and runs statement to delete the supplied user from Users
    //Prints error message if any errors occur
    public void deleteUser(String username) {
        try {
            statement = conn.createStatement();

            String sqlDelete = "DELETE FROM USERS WHERE USERNAME = ?";
            PreparedStatement statement = conn.prepareStatement(sqlDelete);
            statement.setString(1, username);

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

}

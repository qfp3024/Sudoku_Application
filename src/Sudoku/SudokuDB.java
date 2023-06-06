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

    public void connectSudokuDB() {
        if (conn == null) {
            System.err.println("Connection to database has not been established");
        }
        if (!tableExists("Users")) {
            try {
                statement = conn.createStatement();
                String sqlCreateTable = "CREATE TABLE USERS (USERNAME VARCHAR(20), PASSWORD VARCHAR(20), SCORE DOUBLE)";
                String sqlInsertData1 = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES ('Bob', 'password', 84.56)";
                String sqlInsertData2 = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES ('User', 'test', 44.58)";

                statement.executeUpdate(sqlCreateTable);
                statement.executeUpdate(sqlInsertData1);
                statement.executeUpdate(sqlInsertData2);
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        }
    }

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
                    userCheck = false;
                }
            } else {
                System.out.println("No database connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
        return userCheck;
    }

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

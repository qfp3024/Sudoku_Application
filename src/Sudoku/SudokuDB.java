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
                String sqlCreateTable = "CREATE TABLE USERS (USERID VARCHAR(20), USERNAME VARCHAR(20), PASSWORD VARCHAR(20), SCORE INT, TIME FLOAT)";
                String sqlInsertData1 = "INSERT INTO USERS (USERID, USERNAME, PASSWORD, SCORE, TIME) VALUES ('1', 'Bob', 'password', 120, 3.45)";

                statement.executeUpdate(sqlCreateTable);
                statement.executeUpdate(sqlInsertData1);
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
        try {
            if (conn != null) {
                String query = "SELECT USERNAME, PASSWORD FROM USERS";
                PreparedStatement statement = conn.prepareStatement(query);

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    String pass = rs.getString("password");
                    System.out.println("***" + pass);
                    System.out.println("found user");
                    if (password.compareTo(pass) == 0) {
                        userCheck = true;
                    } else {
                        userCheck = false;
                    }
                } else {
                    System.out.println("no such user");
                    String insertQuery = "INSERT INTO USERS (USERNAME, PASSWORD, SCORE) VALUES (?, ?, ?)";
                    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                    insertStatement.setString(1, username);
                    insertStatement.setString(2, password);
                    insertStatement.setInt(3, 0);
                    insertStatement.executeUpdate();

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

}
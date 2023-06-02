/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.Observable;

/**
 *
 * @author Bewick
 */
public class UserModel extends Observable {

    UserView view;
    public String username = null;
    public String password = null;
    SudokuDB database = new SudokuDB();
    
    public UserModel(UserView view) {
        System.out.println("Model");
        this.view = view;
    }
    
    public boolean loginUser() {
        System.out.println("Logging in the user");
        username = view.getunInput();
        password = view.getpwInput();
        if (username != null && password != null) {
            return database.checkName(username, password);
        }
        return false;
    }
    
     public void getValidUsername(String username) {
        boolean isValid = username.matches("[a-zA-Z\\s]+");
    }
}

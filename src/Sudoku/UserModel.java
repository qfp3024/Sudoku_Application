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
        this.view = view;
    }

    public int loginUser() {
        if (!getValidUsername(view.getunInput())) {
            return 1;//If invalid username return 1
        } else {
            username = view.getunInput();
            password = view.getpwInput();
            if (username != null && password != null) {
                if (database.checkName(username, password)) {
                    return 0; //If the valid username matches with the password return 0
                }
            } else {
                return 2;//If passwords don't match return 2
            }
        }
        return 2;//Default causes wrong password error
    }

    public boolean getValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]+$");
    }
}

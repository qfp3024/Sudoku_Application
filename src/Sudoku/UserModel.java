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

    //Runs getValidUsername, if it returns false, the method returns 1, meaning invalid username
    //If getValidUsername is true, username and password are retrieved from "view"
    //Then if username and password aren't null, checkName is run from "SudokuDB" to check the inputs are valid
    //If so, the method returns 0, meaning no errors
    //If password or username are null, or by default, the method returns 2 meaning password error
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

    //If the supplied username matches the regex of .matches and contains only letters or numbers,
    //then it returns true, if not it returns false
    public boolean getValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]+$");
    }
    
}

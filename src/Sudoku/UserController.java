/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Bewick
 */
public class UserController {

    private UserModel model;
    private UserView view;
    private SudokuGame sudokuGame;
    private SudokuDB database = new SudokuDB();

    public UserController(SudokuGame sudokuGame, UserView view, UserModel model) {
        this.view = view;
        this.model = model;
        this.sudokuGame = sudokuGame;

        view.addLoginController(new LoginButtonListener());
        view.addDeleteController(new DeleteUserButtonListener());
    }

    //Listens and runs the actionPerformed method if the loginButton is pressed
    class LoginButtonListener implements ActionListener {

        //Runs the loginUser method in "model" setting loginResult equal to the result
        //If loginResult is equal to 0 it runs closeWindow in "view" to close the userGUI window and then runs sudokuMVC
        //If loginResult is equal to 1 it runs unError in "view" to trigger a popup username error
        //If loginResult is equal to 2 it runs pswError in "view" to trigger a popup password error
        @Override
        public void actionPerformed(ActionEvent e) {
            int loginResult = model.loginUser();
            if (loginResult == 0) {
                view.closeWindow();
                sudokuGame.sudokuMVC(view.getunInput());
            } else if (loginResult == 1) {
                view.unError();
            } else if (loginResult == 2) {
                view.pswError();
            }
        }
    }

    //Listens and runs the actionPerdomed method if the deleteUser button is pressed
    class DeleteUserButtonListener implements ActionListener {

        //Runs checkDelete in "view" to promt the user to comfirm deletion, the result is store in deleteUser
        //If deleteUser is true, and if loginUser returns 0, deleteUser from "SudokuDB" is run to delete the user
        //If loginUser is false, pswError is run in "view" to trigger a popup password error
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean deleteUser = view.checkDelete();
            if (deleteUser == true) {
                if (model.loginUser() == 0) {
                    database.deleteUser(view.getunInput());
                } else {
                    view.pswError();
                }
            }
        }
    }
}

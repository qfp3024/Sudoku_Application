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

    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.loginUser() == 0) {
                view.closeWindow();
                sudokuGame.sudokuMVC(view.getunInput());
            } else if (model.loginUser() == 1) {
                view.unError();
            } else if (model.loginUser() == 2) {
                view.pswError();
            }
        }
    }

    class DeleteUserButtonListener implements ActionListener {

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

    public void addModel(UserModel m) {
        this.model = m;
    }

    public void addView(UserView v) {
        this.view = v;
    }
}

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
public class UserController implements ActionListener {

    private UserModel model;
    private UserView view;
    private SudokuGame sudokuGame;

    public UserController(SudokuGame sudokuGame) {
        this.sudokuGame = sudokuGame;
    }

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

    public void addModel(UserModel m) {
        this.model = m;
    }

    public void addView(UserView v) {
        this.view = v;
    }
}

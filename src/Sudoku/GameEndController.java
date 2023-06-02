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
public class GameEndController {

    private GameEndModel model;
    private GameEndView view;
    private double time;
    private String username;

    public GameEndController(GameEndView view, GameEndModel model, double time, String username) {
        this.view = view;
        this.model = model;
        this.time = time;
        this.username = username;

        view.addYesButtonListener(new GameEndController.yesButtonListener());
        view.addNoButtonListener(new GameEndController.noButtonListener());
    }

    class yesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateScore(time, username);
            if (view.isReplay()) {
                SudokuGame sudokuGame = new SudokuGame();
                sudokuGame.setUsername(username);
                sudokuGame.initialiseGame();
                view.closeWindow();
            } else {
                view.closeWindow();
            }
        }
    }

    class noButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.isReplay()) {
                SudokuGame sudokuGame = new SudokuGame();
                sudokuGame.setUsername(username);
                sudokuGame.initialiseGame();
                view.closeWindow();
            } else {
                view.closeWindow();
            }
        }
    }

    public void setScores() {
        double oldScore = model.getOldScore();
        view.setOldScore(oldScore);

        double newScore = model.getNewScore();
        view.setNewScore(newScore);
    }
}

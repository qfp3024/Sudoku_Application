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

    //Listens and runs the actionPerformed method if the "Yes" button is pressed
    //the "updateScore" method in "model" is run
    //The method also runs the "isReplay()" method in view, if the method returns true
    // "replayGame()" is run, if not the "view" window is closed
    class yesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateScore(time, username);
            if (view.isReplay()) {
                replayGame();
            } else {
                view.closeWindow();
            }
        }
    }

    //Listens and runs the actionPerformed method if the "No" button is pressed
    //The method runs the "isReplay()" method in view, if the method returns true
    // "replayGame()" is run, if not the "view" window is closed
    class noButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.isReplay()) {
                replayGame();
            } else {
                view.closeWindow();
            }
        }
    }

    //Gets the new and old score by running the getOldScore and getNewScore "model" methods,
    //then runs the "view" methods setOldScore and setNewScore with the previously gotten scores as parameters
    public void setScores() {
        double oldScore = model.getOldScore();
        view.setOldScore(oldScore);

        double newScore = model.getNewScore();
        view.setNewScore(newScore);
    }

    //Creates a new game by setting a new SudokuGame
    //Sets the username in SudokuGame so that the user doesn't have to sign in again
    //Creates the boards and fills them by running initialiseGame()
    //And finally closes the "view" window
    public void replayGame() {
        SudokuGame sudokuGame = new SudokuGame();
        sudokuGame.setUsername(username);
        sudokuGame.initialiseGame();
        view.closeWindow();
    }
}

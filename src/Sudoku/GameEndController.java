package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
    class yesButtonListener implements ActionListener {

        //The "updateScore" method in "model" is run
        //The method also runs the "askReplay()" method in view, if the method returns true
        // "replayGame()" is run, if not the "view" window is closed
        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateScore(time, username);
            if (isReplay()) {
                replayGame();
            } else {
                view.closeWindow();
            }
        }
    }

    //Listens and runs the actionPerformed method if the "No" button is pressed
    class noButtonListener implements ActionListener {

        //Runs the "askReplay()" method in view, if the method returns true
        // "replayGame()" is run, if not the "view" window is closed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isReplay()) {
                replayGame();
            } else {
                view.closeWindow();
            }
        }
    }

    //Gets the new and old score by running the getPreviousScore and getNewScore "model" methods,
    //then runs the "view" methods setOldScore and setNewScore with the previously gotten scores as parameters
    public void setScores() {
        double oldScore = model.getPreviousScore();
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

    //Runs the "view" askReplay and sets replay equal to the integer result
    //If yes is selected, return true
    //If no is selected, return false
    //If closed, return false by default
    public boolean isReplay() {
        int replay = view.askReplay();
        if (replay == JOptionPane.YES_OPTION) {
            return true;
        } else if (replay == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return false;
        }
    }
}

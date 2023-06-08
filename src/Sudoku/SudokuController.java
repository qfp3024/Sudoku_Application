/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Bewick
 */
public class SudokuController {

    private SudokuModel model;
    private SudokuView view;
    private GameEndMVC gameEnd = new GameEndMVC();
    private int[][] userBoard;
    private SudokuBoard sudokuBoard;
    private boolean helpUser = false;
    private String username;
    private JTextField[][] board;
    private JComboBox<String> difficulty;

    //Constructs various variables in the SudokuController class,
    //and adds Listener classes to components in the view class
    public SudokuController(SudokuModel model, SudokuView view, SudokuBoard sudokuBoard, String username) {
        this.model = model;
        this.view = view;
        this.sudokuBoard = sudokuBoard;
        this.userBoard = sudokuBoard.userBoard;
        this.username = username;
        this.board = view.getBoard();
        this.difficulty = view.getDifficulty();

        view.addButtonListener(new ButtonListener());
        view.addTextFieldFocusListener(new TextFieldFocusListener());
        view.addComboBoxListener(new ComboBoxListener());
        view.addToggleListener(new ToggleListener());
        view.addLogoutBtnListener(new LogoutButtonListener());
        view.addHowToBtnListener(new HowToButtonListener());
        view.addRestartBtnListener(new RestartButtonListener());
    }

    //Listens and runs the actionPerformed method if the toggleButton is pressed
    //It then gets the pressed toggleButton, and runs the changeBtn "model" method,
    //setting helpUser equal to the result. 
    //It then, if helpUser is true, loops through each cell of the userBoard running the "model" showErrors method
    //If helpUser is false, the text colour of the cell is set to black,
    //and the change is set in "view" using the setBoard method
    class ToggleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JToggleButton toggleBtn = view.getToggle();
            helpUser = model.changeBtn(toggleBtn, helpUser);
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    if (helpUser == true) {
                        model.showErrors(row, column, sudokuBoard);
                    } else {
                        board[row][column].setForeground(Color.black);
                        view.setBoard(board);
                    }
                }
            }
        }
    }

    //Listens and runs actionPerformed if an option is selected in the "view" comboBox
    //The method runs the "model" setDifficulty method with SudokuBoard as a parameter to set the board difficulty
    //and runs the reserTimer method to reset the timer
    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setDifficulty(sudokuBoard);
            model.resetTimer();
        }
    }

    //Listens and runs actionPerfomed when the endGame button is pressed
    //The method runs the "model" endGame method, if the method returns true, the "view" window is closed,
    //the difficulty is retrieved using getSelectedItem, and the gameEndMVC is run to create the gameEndGUI
    //If endGame returns false, an message dialog is triggered in "view" by running the incorrectBoard method
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.endGame(sudokuBoard)) {
                view.closeWindow();
                Object selectedDifficulty = difficulty.getSelectedItem();
                gameEnd.GameEndMVC(username, selectedDifficulty.toString(), model.getTotalTime(), helpUser);
            } else {
                view.incorrectBoard();
            }
        }
    }

    //Listens and runs actionPerformed when the logout button is pressed
    //Then restarts the program by creating a new SudokuGame instance, and initialising the game,
    //however username is set to null so that the user can log in as a different user
    //Finally the "view" frame is disposed of by running the closeWindow method
    class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SudokuGame sudokuGame = new SudokuGame();
            sudokuGame.setUsername(null);
            sudokuGame.initialiseGame();
            view.closeWindow();
        }
    }

    //Listens and runs actionPerformed when the user clicks out of a textField
    //The method cycles throught each cell of the board setting the text colour to black
    //and then setting the change by running setBoard.
    //It then runs checkCellContent to check the cell content is valid
    //If helpUser has been set to true, it runs showErrors to turn the invalid entered answers red
    class TextFieldFocusListener extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    board[row][column].setForeground(Color.black);
                    view.setBoard(board);
                    model.checkCellContent(row, column);
                    if (helpUser == true) {
                        model.showErrors(row, column, sudokuBoard);
                    }
                }
            }
        }
    }

    //Listens and runs actionPerformed when the "How To Play" button is clicked
    //It then runs the showHowTo method in "view" triggering a popup with the game instructions
    class HowToButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHowTo();
        }
    }

    //Listens and runs actionPerformed when the restart button is pressed
    //It the cycles though each cell, running the "model" restartBaord method
    class RestartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    model.restartBoard(row, column);
                }
            }
        }
    }
}

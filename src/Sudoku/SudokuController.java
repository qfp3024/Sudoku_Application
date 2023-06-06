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
    private JComboBox<String> difficulty ;

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

    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setDifficulty(sudokuBoard);
            model.resetTimer();
        }
    }

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

    class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SudokuGame sudokuGame = new SudokuGame();
            sudokuGame.setUsername(null);
            sudokuGame.initialiseGame();
            view.closeWindow();
        }
    }

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

    class HowToButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHowTo();
        }
    }

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

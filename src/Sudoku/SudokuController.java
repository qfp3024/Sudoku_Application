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

    public SudokuController(SudokuModel model, SudokuView view, SudokuBoard sudokuBoard, String username) {
        this.model = model;
        this.view = view;
        this.sudokuBoard = sudokuBoard;
        this.userBoard = sudokuBoard.userBoard;
        this.username = username;

        view.addButtonListener(new ButtonListener());
        view.addTextFieldFocusListener(new TextFieldFocusListener());
        view.addComboBoxListener(new ComboBoxListener());
        view.addToggleListener(new ToggleListener());
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
                    }
                    else {
                        view.board[row][column].setForeground(Color.black);
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
                Object selectedDifficulty = view.difficulty.getSelectedItem();
                gameEnd.GameEndMVC(username, selectedDifficulty.toString(), model.getTotalTime(), helpUser);
            } else {
                view.incorrectBoard();
            }
        }
    }

    class TextFieldFocusListener extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    view.board[row][column].setForeground(Color.black);
                    model.checkCellContent(row, column);
                    if (helpUser == true) {
                        model.showErrors(row, column, sudokuBoard);
                    }
                }
            }
        }
    }

    public void addModel(SudokuModel m) {
        this.model = m;
    }

    public void addView(SudokuView v) {
        this.view = v;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author Bewick
 */
public class SudokuController {

    SudokuModel model;
    SudokuView view;
    GameEndMVC gameEnd = new GameEndMVC();
    int[][] userBoard;
    SudokuBoard sudokuBoard;
    String username;
    

    public SudokuController(SudokuModel model, SudokuView view, SudokuBoard sudokuBoard, String username) {
        this.model = model;
        this.view = view;
        this.sudokuBoard = sudokuBoard;
        this.userBoard = sudokuBoard.userBoard;
        this.username = username;
        
        view.addButtonListener(new ButtonListener());
        view.addTextFieldFocusListener(new TextFieldFocusListener());
        view.addComboBoxListener(new ComboBoxListener());
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
            model.endGame(sudokuBoard);
            view.closeWindow();
            Object selectedDifficulty = view.difficulty.getSelectedItem();
            gameEnd.GameEndMVC(username, selectedDifficulty.toString(), model.getTotalTime());
        }
    }

    class TextFieldFocusListener extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            for(int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    model.checkCellContent(row, column);
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

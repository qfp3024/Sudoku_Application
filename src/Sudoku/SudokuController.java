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

    public SudokuController(SudokuModel model, SudokuView view) {
        this.model = model;
        this.view = view;

        view.addButtonListener(new ButtonListener());
        view.addTextFieldFocusListener(new TextFieldFocusListener());
        view.addComboBoxListener(new ComboBoxListener());
    }
    
    class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setDifficulty();
        }
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Controller: acting on 1");
        }
    }

    class TextFieldFocusListener extends FocusAdapter {

        @Override
        public void focusLost(FocusEvent e) {
            System.out.println("Controller: acting on 2");
            for(int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    model.checkCellContent(row, column);
                }
            }
        }
    }

    public void addModel(SudokuModel m) {
        System.out.println("Controller: adding model");
        this.model = m;
    }

    public void addView(SudokuView v) {
        System.out.println("Controller: adding view");
        this.view = v;
    }
}

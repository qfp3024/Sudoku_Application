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
public class SudokuController implements ActionListener {

    SudokuModel model;
    SudokuView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Controller: acting on model");
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

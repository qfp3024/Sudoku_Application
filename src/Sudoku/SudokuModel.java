/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.Observable;

/**
 *
 * @author Bewick
 */
public class SudokuModel extends Observable {

    SudokuView view;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
    }

}

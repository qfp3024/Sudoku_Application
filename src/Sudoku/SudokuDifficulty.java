/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import javax.swing.JComboBox;

/**
 *
 * @author Bewick
 */
public class SudokuDifficulty {

    //Gets the selected item from the difficulty combobox, converts the result to a string,
    //then sets the value of difficultyNum depending on the difficultyString
    //Returns difficultyNum at the end
    public int getDifficulty(JComboBox<String> difficulty) {
        int difficultyNum = 0;
        Object selectedDifficulty = difficulty.getSelectedItem();
        String difficultyString = selectedDifficulty.toString();

        if (difficultyString.equals("Beginner")) {
            difficultyNum = 2;
        } else if (difficultyString.equals("Amateur")) {
            difficultyNum = 3;
        } else if (difficultyString.equals("Intermediate")) {
            difficultyNum = 4;
        } else if (difficultyString.equals("Expert")) {
            difficultyNum = 5;
        } else if (difficultyString.equals("Master")) {
            difficultyNum = 6;
        }

        return difficultyNum;
    }

    //Returns a difficultyValue depending on the game difficulty, higher difficulty = higher value
    public double getDifficultyValue(String difficulty) {
        double difficultyValue = 0.00;
        if (difficulty.equals("Beginner")) {
            difficultyValue = 1.00;
        } else if (difficulty.equals("Amateur")) {
            difficultyValue = 2.00;
        } else if (difficulty.equals("Intermediate")) {
            difficultyValue = 3.00;
        } else if (difficulty.equals("Expert")) {
            difficultyValue = 4.00;
        } else if (difficulty.equals("Master")) {
            difficultyValue = 5.00;
        }
        return difficultyValue;
    }
}

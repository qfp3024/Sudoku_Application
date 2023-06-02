/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.text.DecimalFormat;

/**
 *
 * @author Bewick
 */
public class GameEndModel {

    private SudokuDB database = new SudokuDB();
    private String username;
    private double totalScore;

    public GameEndModel(String username) {
        this.database = database;
        this.username = username;
    }

    public double getOldScore() {
        return database.getUserScore(username);
    }

    public double getNewScore() {
        return totalScore;
    }

    public void calculateScore(String difficulty, double time) {
        double difficultyValue = 0.00;
        switch (difficulty) {
            case "Beginner":
                difficultyValue = 1.00;
                break;
            case "Ameteur":
                difficultyValue = 2.00;
                break;
            case "Intermediate":
                difficultyValue = 3.00;
                break;
            case "Expert":
                difficultyValue = 4.00;
                break;
            case "Master":
                difficultyValue = 5.00;
                break;
            default:
                break;
        }

        totalScore = difficultyValue / time;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String decimalString = decimalFormat.format(totalScore);
        totalScore = Double.parseDouble(decimalString);
    }

    public void updateScore(double time, String username) {
        database.updateUserScore(totalScore, time, username);
    }
}

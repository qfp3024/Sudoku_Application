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
        this.username = username;
    }

    public double getOldScore() {
        return database.getUserScore(username);
    }

    public double getNewScore() {
        return totalScore;
    }

    public void calculateScore(String difficulty, double time, boolean helpUser) {
        double difficultyValue = 0.00;
        difficultyValue = getDifficultyValue(difficultyValue, difficulty);
        totalScore = difficultyValue / time;
        totalScore *= 100;
        shortenDecimal();
        if (helpUser) {
            totalScore /= 2;
        }
    }

    public void shortenDecimal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String decimalString = decimalFormat.format(totalScore);
        totalScore = Double.parseDouble(decimalString);
    }

    public double getDifficultyValue(double difficultyValue, String difficulty) {
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

    public void updateScore(double time, String username) {
        database.updateUserScore(totalScore, time, username);
    }
}

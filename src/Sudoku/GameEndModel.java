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

    //Returns the score retrieved from the "getUserScore" method from "database"
    public double getOldScore() {
        return database.getUserScore(username);
    }

    //Returns the totalScore variable;
    public double getNewScore() {
        return totalScore;
    }

    //Calculates the score by running the difficultyValue method to get the value of the user's difficulty,
    //Divides the difficultyValue by time to get the score
    //Multiplies the score by 100, makes the score look like it's worth more, 85 looks better than 0.85
    //Shortens the decimal to 2 decimale places (0.00)
    //Divides the total score by 2 if helpUser is true, as the user would have had help
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

    //Sets the decimal format to be 0.00
    //Converts the totalScore into a 2 decimal point version, but as a string
    //Converts totalScore back into a double and sets totalScore equal to it
    public void shortenDecimal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String decimalString = decimalFormat.format(totalScore);
        totalScore = Double.parseDouble(decimalString);
    }

    //Returns a difficultyValue depending on the game difficulty, higher difficulty = higher value
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

    //Runs the database updateUserScore method with totalScore, time, and username as parameters,
    // to update the score in the database
    public void updateScore(double time, String username) {
        database.updateUserScore(totalScore, time, username);
    }
}

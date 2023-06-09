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
    private SudokuDifficulty sudokuDifficulty;

    public GameEndModel(String username) {
        this.username = username;
        this.sudokuDifficulty = new SudokuDifficulty();
    }

    //Returns the score retrieved from the "getUserScore" method from "database"
    public double getPreviousScore() {
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
        double difficultyValue = getDifficultyValue(difficulty);
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
    private void shortenDecimal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String decimalString = decimalFormat.format(totalScore);
        totalScore = Double.parseDouble(decimalString);
    }

    //GetDifficultyValue
    public double getDifficultyValue(String difficulty) {
        return sudokuDifficulty.getDifficultyValue(difficulty);
    }

    //Runs the database updateUserScore method with totalScore, time, and username as parameters,
    // to update the score in the database
    public void updateScore(double time, String username) {
        database.updateUserScore(totalScore, time, username);
    }
}

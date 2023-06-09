/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import java.util.Observable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Bewick
 */
public class SudokuModel extends Observable {

    private SudokuView view;
    private long startTime = System.nanoTime();
    private double totalTime = 0.00;
    private JTextField[][] board;
    private JComboBox<String> difficulty;
    private SudokuValidate validation;
    private SudokuDifficulty sudokuDifficulty;
    private AlterBoards alterBoard;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
        this.board = view.getBoard();
        this.difficulty = view.getDifficulty();
        this.validation = new SudokuValidate(view, board);
        this.sudokuDifficulty = new SudokuDifficulty();
        this.alterBoard = new AlterBoards(view, board);
    }

    //Calls the checkCellContent method in "SudokuValidation"
    public void checkCellContent(int row, int column) {
        validation.checkCellContent(row, column);
    }

    //Sets the new difficultyNum int equal to the result of getDifficulty
    //Then runs the updateBoard function to update the sudokuBoard with the new difficulty
    public void setDifficulty(SudokuBoard sudokuBoard) {
        int difficultyNum = sudokuDifficulty.getDifficulty(difficulty);
        alterBoard.updateBoard(difficultyNum, sudokuBoard);
    }
    
    //Runs the restartBoard method in the "AlterBoard" class
    public void restartBoard(int row, int column) {
        alterBoard.restartBoard(row, column);
    }

    //Runs the merge board method, then returns the result of the checkBoardCorrect method
    public boolean endGame(SudokuBoard sudokuBoard) {
        alterBoard.mergeBoards(sudokuBoard.userBoard);
        return checkBoardCorrect(sudokuBoard);
    }

    //Runs the "checkBoard" checkBoardCorrect method to check if the userBoard and answerBoard match
    //If so totalTime is equal to the result of convertToMinutes, and true is returned
    //If no false is returned
    public boolean checkBoardCorrect(SudokuBoard sudokuBoard) {

        if (CheckBoard.checkBoardCorrect(sudokuBoard)) {
            totalTime = convertToMinutes(startTime);
            return true;
        } else {
            return false;
        }
    }

    //Converts answer to a string
    //Runs the setColour method to set the text colour
    //Updates the "view" board by running setBoard
    public void showErrors(int row, int column, SudokuBoard sudokuBoard) {

        JTextField[][] boardUser = board;
        String cellContent = boardUser[row][column].getText();

        int answerBoard[][] = sudokuBoard.getAnswerBoard();
        int answer = answerBoard[row][column];
        String answerString = Integer.toString(answer);

        setColour(cellContent, answerString, row, column);

        view.setBoard(board);
    }

    //If cellContent is equal to answerString set the cell text colour to black
    //If cellContent is equal to an empty string set the cell text colour to black
    //If neither is true, set the cell text colour to red
    public void setColour(String cellContent, String answerString, int row, int column) {

        if (cellContent.equals(answerString)) {
            board[row][column].setForeground(Color.black);
        } else if (cellContent.equals("")) {
            board[row][column].setForeground(Color.black);
        } else {
            board[row][column].setForeground(Color.red);
        }
    }

    //Calculates the duration using the difference between start and end times, divide by 1000000000 to get seconds value
    //Sets minutes equal to duration secs, if value is more than 60, divide by 60 to get minutes
    //Return minutes
    public double convertToMinutes(double startTime) {
        double minutes = 0;
        double endTime = System.nanoTime();
        double durationSecs = (endTime - startTime) / 1000000000;

        minutes = durationSecs;

        if (durationSecs >= 60) {
            minutes = (durationSecs / 60);
        }
        return minutes;
    }

    //Sets startTime equal to the current nanoTime using System.nanoTime
    public void resetTimer() {
        startTime = System.nanoTime();
    }

    //Return SudokuModel totalTime
    public double getTotalTime() {
        return totalTime;
    }

    //If toggleBtn has been selected (clicked) set the text of toggleBtn to be "ON" and helpUser to be true
    //If toggleBtn has been unselected (reclicked) set the text of toggleBtn to be "OFF" and helpUser to be false
    //and the button colour to be red
    //Update the button in "view" by running setToggleBtn
    //Return helpUser
    public boolean changeBtn(JToggleButton toggleBtn, boolean helpUser) {

        if (toggleBtn.isSelected()) {
            toggleBtn.setText("ON");
            helpUser = true;
        } else {
            toggleBtn.setText("OFF");
            toggleBtn.setBackground(Color.RED);
            helpUser = false;
        }
        view.setToggleBtn(toggleBtn);
        return helpUser;
    }

    //Sets the colour of the text in the supplied cell row,column
    //Then the change is set in "view" using the setBoard method
    public void setCellColour(int row, int column) {

        board[row][column].setForeground(Color.black);
        view.setBoard(board);
    }
}

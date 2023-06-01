/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.Observable;
import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class SudokuModel extends Observable {

    SudokuView view;
    private long startTime = System.nanoTime();
    private double totalTime = 0.00;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
        this.startTime = startTime;
    }

    public void checkCellContent(int row, int column) {
        JTextField[][] board = view.board;
        String cellContent = board[row][column].getText();

        if (!cellContent.equals("")) {
            if (cellContent.length() >= 2) {
                char cellContentArray[] = cellContent.toCharArray();
                cellContent = "" + cellContentArray[0];
            }

            if (!checkIntInput(cellContent)) {
                cellContent = "";
                board[row][column].setText(cellContent);
                view.board[row][column] = board[row][column];
                view.InputError();
            }
        }
        board[row][column].setText(cellContent);
        view.board[row][column] = board[row][column];
    }

    public boolean checkIntInput(String cellContent) {
        return cellContent.matches("-?\\d+");
    }

    public void setDifficulty(SudokuBoard sudokuBoard) {
        int difficulty = 0;
        difficulty = getDifficulty(difficulty);
        updateBoard(difficulty, sudokuBoard);
    }

    public void updateBoard(int difficulty, SudokuBoard sudokuBoard) {
        SudokuBoard.clearBoards(sudokuBoard.getUserBoard(), sudokuBoard.getAnswerBoard());
        SudokuBoard.initialiseBoard(difficulty, sudokuBoard.getUserBoard(), sudokuBoard.getAnswerBoard());
        setSudokuBoard(sudokuBoard.getUserBoard());
    }

    public int getDifficulty(int difficulty) {
        Object selectedDifficulty = view.difficulty.getSelectedItem();
        switch (selectedDifficulty.toString()) {
            case "Beginner":
                difficulty = 2;
                break;
            case "Ameteur":
                difficulty = 3;
                break;
            case "Intermediate":
                difficulty = 4;
                break;
            case "Expert":
                difficulty = 5;
                break;
            case "Master":
                difficulty = 6;
                break;
            default:
                break;
        }
        return difficulty;
    }

    public void setSudokuBoard(int[][] userBoard) {
        JTextField[][] board = view.board;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String newNumber = Integer.toString(userBoard[row][column]);

                if (newNumber.equals("0")) {
                    board[row][column].setEditable(true);
                    newNumber = "";
                } else {
                    board[row][column].setEditable(false);
                }
                board[row][column].setText(newNumber);
            }
        }
        view.board = board;
    }

    public void endGame(SudokuBoard sudokuBoard) {
        mergeBoards(sudokuBoard.userBoard);
        CheckBoards checkBoard = new CheckBoards();
        if (checkBoard.checkBoardCorrect(sudokuBoard.userBoard, sudokuBoard.answerBoard)) {
            totalTime = convertToMinutes(startTime);
        } else {
            System.out.println("You did not successfully complete the Sudoku Board");
            view.incorrectBoard();
        }

    }

    public int[][] mergeBoards(int[][] userBoard) {
        int viewNumber = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (view.board[row][column].getText().equals("")) {
                    viewNumber = 0;
                } else {
                    viewNumber = Integer.parseInt(view.board[row][column].getText());
                }
                userBoard[row][column] = viewNumber;
            }
        }
        return userBoard;
    }

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

    public void resetTimer() {
        startTime = System.nanoTime();
    }
    
    public double getTotalTime() {
        return totalTime;
    }
}

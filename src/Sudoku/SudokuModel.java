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
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard userBoard;
    private SudokuBoard answerBoard;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
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

    public void setDifficulty() {
        int difficulty = 0;
        difficulty = getDifficulty(difficulty);
        userBoard = new SudokuBoard();
        answerBoard = new SudokuBoard();
        updateBoard(difficulty);
    }

    public void updateBoard(int difficulty) {
        SudokuBoard.clearBoards(userBoard.getUserBoard(), answerBoard.getAnswerBoard());
        SudokuBoard.initialiseBoard(difficulty, userBoard.getUserBoard(), answerBoard.getAnswerBoard());
        setSudokuBoard(userBoard.getUserBoard());
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
                String newNumber = Integer.toString(userBoard[column][row]);

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

}

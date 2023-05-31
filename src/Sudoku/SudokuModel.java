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
    private long startTime;
    private SudokuBoard userInputBoard;
    private SudokuBoard answerBoard;

    public SudokuModel(SudokuView view, long startTime) {
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

    public void setDifficulty() {
        int difficulty = 0;
        difficulty = getDifficulty(difficulty);
        userInputBoard = new SudokuBoard();
        answerBoard = new SudokuBoard();
        updateBoard(difficulty);
    }

    public void updateBoard(int difficulty) {
        SudokuBoard.clearBoards(userInputBoard.getUserBoard(), answerBoard.getAnswerBoard());
        SudokuBoard.initialiseBoard(difficulty, userInputBoard.getUserBoard(), answerBoard.getAnswerBoard());
        setSudokuBoard(userInputBoard.getUserBoard());
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

    public void endGame(SudokuBoard sudokuBoard) {
        mergeBoards(sudokuBoard.userBoard);
        CheckBoards checkBoard = new CheckBoards();
        if (checkBoard.checkBoardCorrect(sudokuBoard.userBoard, sudokuBoard.answerBoard)) {
            System.out.println("Congratulations, you correctly completed the Sudoku Board!");

//            if (!user.userMap.containsKey(user.username)) {
//                updateTime(startTime);
//            } else {
            Integer timeInteger = convertToMinutes(startTime);
            System.out.println("Time: " + timeInteger + " minutes");
//                updateTime(startTime);
//            }
        } else {
            System.out.println("You did not successfully complete the Sudoku Board");
        }

    }

    public int[][] mergeBoards(int[][] userBoard) {
        int viewNumber = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (view.board[column][row].getText().equals("")) {
                    viewNumber = 0;
                } else {
                    viewNumber = Integer.parseInt(view.board[column][row].getText());
                }
                userBoard[row][column] = viewNumber;
            }
        }
        return userBoard;
    }

    public int convertToMinutes(long startTime) {
        int minutes = 0;
        long endTime = System.nanoTime();
        long durationSecs = (endTime - startTime) / 1000000000;
        if (durationSecs >= 60) {
            minutes = (int) (durationSecs / 60);
        }
        return minutes;
    }
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import java.util.Observable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

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
            case "Amateur":
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
                board[row][column].setForeground(Color.black);
            }
        }
        view.board = board;
    }

    public boolean endGame(SudokuBoard sudokuBoard) {
        mergeBoards(sudokuBoard.userBoard);
        CheckBoards checkBoard = new CheckBoards();
        if (checkBoard.checkBoardCorrect(sudokuBoard.userBoard, sudokuBoard.answerBoard)) {
            totalTime = convertToMinutes(startTime);
            return true;
        } else {
            return false;
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

    public void showErrors(int row, int column, SudokuBoard sudokuBoard) {
        JTextField[][] board = view.board;
        String cellContent = board[row][column].getText();

        int answerBoard[][] = sudokuBoard.getAnswerBoard();
        int answer = answerBoard[row][column];
        String answerString = Integer.toString(answer);

        if (cellContent.equals(answerString)) {
            view.board[row][column].setForeground(Color.black);
        } else if (cellContent.equals("")) {
            view.board[row][column].setForeground(Color.black);
        } else {
            view.board[row][column].setForeground(Color.red);
        }
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

    public boolean toggleBtnColour(JToggleButton toggleBtn, boolean helpUser) {
        if (toggleBtn.isSelected()) {
            toggleBtn.setText("ON");
            helpUser = true;
        } else {
            toggleBtn.setText("OFF");
            helpUser = false;
        }

        return helpUser;
    }

    public boolean changeBtn(JToggleButton toggleBtn, boolean helpUser) {
        if (toggleBtn.isSelected()) {
            toggleBtn.setText("ON");
            toggleBtn.setBackground(Color.GREEN);
            helpUser = true;
        } else {
            toggleBtn.setText("OFF");
            toggleBtn.setBackground(Color.RED);
            helpUser = false;
        }
        view.setToggleBtn(toggleBtn);
        return helpUser;
    }
    
    public void restartBoard(int row, int column) {
        if (view.board[row][column].isEditable() == true) {
           view.board[row][column].setText("");
        }
    }
}

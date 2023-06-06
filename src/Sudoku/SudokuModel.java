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

    SudokuView view;
    private long startTime = System.nanoTime();
    private double totalTime = 0.00;
    private JTextField[][] board;
    private JComboBox<String> difficulty;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
        this.board = view.getBoard();
        this.difficulty = view.getDifficulty();
    }

    public void checkCellContent(int row, int column) {
        JTextField[][] userBoard = board;
        String cellContent = userBoard[row][column].getText();

        if (!cellContent.equals("")) {
            if (cellContent.length() >= 2) {
                char cellContentArray[] = cellContent.toCharArray();
                cellContent = "" + cellContentArray[0];
            }

            if (!checkIntInput(cellContent)) {
                cellContent = "";
                userBoard[row][column].setText(cellContent);
                board[row][column] = userBoard[row][column];
                view.setBoard(board);
                view.InputError();
            }
        }
        userBoard[row][column].setText(cellContent);
        board[row][column] = userBoard[row][column];
        view.setBoard(board);
    }

    public boolean checkIntInput(String cellContent) {
        return cellContent.matches("-?\\d+");
    }

    public void setDifficulty(SudokuBoard sudokuBoard) {
        int difficultyNum = 0;
        difficultyNum = getDifficulty(difficultyNum);
        updateBoard(difficultyNum, sudokuBoard);
    }

    public void updateBoard(int difficulty, SudokuBoard sudokuBoard) {
        SudokuBoard.clearBoards(sudokuBoard.getUserBoard(), sudokuBoard.getAnswerBoard());
        SudokuBoard.initialiseBoard(difficulty, sudokuBoard.getUserBoard(), sudokuBoard.getAnswerBoard());
        setSudokuBoard(sudokuBoard.getUserBoard());
    }

    public int getDifficulty(int difficultyNum) {
        Object selectedDifficulty = difficulty.getSelectedItem();
        switch (selectedDifficulty.toString()) {
            case "Beginner":
                difficultyNum = 2;
                break;
            case "Amateur":
                difficultyNum = 3;
                break;
            case "Intermediate":
                difficultyNum = 4;
                break;
            case "Expert":
                difficultyNum = 5;
                break;
            case "Master":
                difficultyNum = 6;
                break;
            default:
                break;
        }
        return difficultyNum;
    }

    public void setSudokuBoard(int[][] userBoard) {
        JTextField[][] boardUser = board;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String newNumber = Integer.toString(userBoard[row][column]);

                if (newNumber.equals("0")) {
                    boardUser[row][column].setEditable(true);
                    newNumber = "";
                } else {
                    boardUser[row][column].setEditable(false);
                }
                boardUser[row][column].setText(newNumber);
                boardUser[row][column].setForeground(Color.black);
            }
        }
        board = boardUser;
        view.setBoard(board);
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
                if (board[row][column].getText().equals("")) {
                    viewNumber = 0;
                } else {
                    viewNumber = Integer.parseInt(board[row][column].getText());
                }
                userBoard[row][column] = viewNumber;
            }
        }
        return userBoard;
    }

    public void showErrors(int row, int column, SudokuBoard sudokuBoard) {
        JTextField[][] boardUser = board;
        String cellContent = boardUser[row][column].getText();

        int answerBoard[][] = sudokuBoard.getAnswerBoard();
        int answer = answerBoard[row][column];
        String answerString = Integer.toString(answer);

        if (cellContent.equals(answerString)) {
            board[row][column].setForeground(Color.black);
        } else if (cellContent.equals("")) {
            board[row][column].setForeground(Color.black);
        } else {
            board[row][column].setForeground(Color.red);
        }
        view.setBoard(board);
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
        if (board[row][column].isEditable() == true) {
            board[row][column].setText("");
            view.setBoard(board);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class AlterBoards {

    private SudokuView view;
    private JTextField[][] board;

    public AlterBoards(SudokuView view, JTextField[][] board) {
        this.view = view;
        this.board = board;
    }

    //Clears and initialises (Fills) the userBoard and answerBoard
    //Then sets the sudokuBoard using setSudokuBoard and the newly updated userBoard as a parameter
    public void updateBoard(int difficulty, SudokuBoard sudokuBoard) {
        SudokuBoard.clearBoards(sudokuBoard);
        SudokuBoard.initialiseBoard(difficulty, sudokuBoard);
        setSudokuBoard(sudokuBoard.getUserBoard());
    }

    //Cycles through each cell in the board, setting newNumber equal to the string version
    //of the number contained in the current cell
    //The text of the cell is set equal to newNumber, and the colour set to be black
    //At the end of the loop SudokuBoard board is set equal to boardUser and the "view" board is set using setBaord
    public void setSudokuBoard(int[][] userBoard) {
        JTextField[][] boardUser = board;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String newNumber = Integer.toString(userBoard[row][column]);
                newNumber = setEditable(newNumber, row, column, boardUser);

                boardUser[row][column].setText(newNumber);
                boardUser[row][column].setForeground(Color.black);
            }
        }
        board = boardUser;
        view.setBoard(board);
    }

    //Iterates through each cell with nested loops,
    //If the text of the cell is an empty string viewnumber is equal to 0
    //If not, viewNumber is equal to the int version of the cell text
    //the value of the cell is then set equal to viewNumber
    //And the updated userBoard is returned
    public int[][] mergeBoards(int[][] userBoard) {
        int viewNumber = 0;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellText = board[row][column].getText();

                if (cellText.equals("")) {
                    viewNumber = 0;
                } else {
                    viewNumber = Integer.parseInt(cellText);
                }
                userBoard[row][column] = viewNumber;
            }
        }
        return userBoard;
    }

    //If the current cell in board is editable, set the text to be an empty string
    // and then update the board in "view" by running setBoard
    public void restartBoard(int row, int column) {

        if (board[row][column].isEditable() == true) {
            board[row][column].setText("");
            view.setBoard(board);
        }
    }

    //If newNumber is equal to 0, the current textField cell is set to be editable,
    //and newNumber is set to be equal to an empty string
    //If newNumber isn't equal to 0, the current textField cell is set to be uneditable
    private String setEditable(String newNumber, int row, int column, JTextField[][] boardUser) {
        if (newNumber.equals("0")) {
            boardUser[row][column].setEditable(true);
            newNumber = "";
        } else {
            boardUser[row][column].setEditable(false);
        }
        return newNumber;
    }
}

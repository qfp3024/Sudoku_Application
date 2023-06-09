/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class SudokuValidate {

    private SudokuView view;
    private JTextField[][] board;

    public SudokuValidate(SudokuView view, JTextField[][] board) {
        this.view = view;
        this.board = board;
    }

    //Defines and sets new JTextField 2D array variable equal to SudokuModel board
    //Defines and sets new String cellContent equal to the text of the cell located at row,column in 2D array
    //Checks cellContent is not equal to empty string, if so, runs checkSingleInput, setting cellContent to the result
    //Then check if cellContent is an int, if not, runs invalidInput, setting cellConent to the result
    //Finally sets the text of the cell at row,column in userBoard equal to cellContent
    //Updates the same cell in the sudokuModel board, and then updates the board in "view" using setBoard
    public void checkCellContent(int row, int column) {
        JTextField[][] userBoard = board;
        String cellContent = userBoard[row][column].getText();

        if (!cellContent.equals("")) {
            cellContent = checkSingleInput(cellContent);

            if (!checkIntInput(cellContent)) {
                cellContent = invalidInput(cellContent, userBoard, row, column);
            }
        }
        userBoard[row][column].setText(cellContent);
        board[row][column] = userBoard[row][column];
        view.setBoard(board);
    }

    //Checks if cellContent contains more than 2 characters , if so, gets cellContent as a charArray,
    //then takes the first number in the array and adds it to cellContent via an empty string,
    //this ensures only one character exists in each cell. Returns cellContent at the end
    public String checkSingleInput(String cellContent) {
        if (cellContent.length() >= 2) {
            char cellContentArray[] = cellContent.toCharArray();
            cellContent = "" + cellContentArray[0];
        }
        return cellContent;
    }

    //Returns true if cellContent matches the regex and so contains only integers, false if it doesn't
    private boolean checkIntInput(String cellContent) {
        return cellContent.matches("-?\\d+");
    }

    //Cets the invalid cell text, updates the cell in the board,
    //and then sets the board changes in "view" using setBoard,
    //before running the input error popup method in "view"
    //Then returns cellContent
    public String invalidInput(String cellContent, JTextField[][] userBoard, int row, int column) {
        cellContent = "";

        userBoard[row][column].setText(cellContent);

        board[row][column] = userBoard[row][column];
        view.setBoard(board);
        view.InputError();

        return cellContent;
    }
}

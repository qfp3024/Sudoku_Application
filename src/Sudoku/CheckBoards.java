/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class CheckBoards {

    //Checks whether the randomly generated number already exists in the row, column, or box
    // that corresponds to the supplied row and column numbers
    // If the number already exists, the the method returns false
    // If the number isn't in the row, column, or box, the method returns true
    public static boolean checkBoard(int row, int column, int num, int[][] answerBoard) {
        for (int i = 0; i < 9; i++) {
            if (answerBoard[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (answerBoard[i][column] == num) {
                return false;
            }
        }

        int rowBox = (row / 3) * 3;
        int columnBox = (column / 3) * 3;
        for (int i = rowBox; i < (rowBox + 3); i++) {
            for (int j = columnBox; j < (columnBox + 3); j++) {
                if (answerBoard[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    //Checks whether the supplied userBoard is equal to the answerBoard
    //Every number in userBoard is compared to the number is answerBoard
    // If the numbers match correctBoard increases by 1
    // the method returns true if all 81 cells are correct, returns false otherwise
    public static boolean checkBoardCorrect(int[][] userBoard, int[][] answerBoard) {
        int correctBoard = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (userBoard[i][j] == answerBoard[i][j]) {
                    correctBoard += 1;
                }
            }
        }

        return correctBoard == 81;
    }

}
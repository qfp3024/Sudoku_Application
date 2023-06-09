package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bewick
 */
public class FillBoards {

    private static Random random = new Random();
    private static final int boardMax = 9;
    private static final int randMax = 10;

    //Copies the contents of answerBoard into userBoard
    //Iterates throught each cell, changing the contents to 0 randomly
    //Higher difficulty value results in more numbers being changed to 0
    public static void fillUserBoard(int difficulty, SudokuBoard sudokuBoard) {
        int[][] userBoard = sudokuBoard.userBoard;
        int[][] answerBoard = sudokuBoard.answerBoard;
        
        for (int i = 0; i < boardMax; i++) {
            System.arraycopy(answerBoard[i], 0, userBoard[i], 0, boardMax);
        }
        
        for (int i = 0; i < boardMax; i++) {
            for (int j = 0; j < boardMax; j++) {
                int randNum = random.nextInt(randMax);
                if ((randNum >= 1) && (randNum <= difficulty)) {
                    userBoard[i][j] = 0;
                }
            }
        }
    }

    //Creates a List collection, adds numbers from 1-boardMax to the list, then shuffles it
    //Uses checkBoard method to check if selected number is valid according to Sudoku rules
    //Iterates through the list adding the selected number to the answerBoard
    public static boolean fillBoard(int row, int column, int[][] answerBoard) {
        if (column == boardMax) {
            column = 0;
            row++;
            if (row == boardMax) {
                return true;
            }
        }

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= boardMax; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums, random);

        int num = 0;
        for (int i = 0; i < boardMax; i++) {
            num = nums.get(i);
            if (CheckBoard.checkBoard(row, column, num, answerBoard)) {
                answerBoard[row][column] = num;
                if (fillBoard(row, column + 1, answerBoard)) {
                    return true;
                }
            }
        }

        answerBoard[row][column] = 0;
        return false;
    }
}

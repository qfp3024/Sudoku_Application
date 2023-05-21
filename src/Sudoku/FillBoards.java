/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    //Copies the contents of answerBoard into userBoard
    //Iterates throught each cell, changing the contents to 0 randomly
    //Higher difficulty value results in more numbers being changed to 0
    public static void fillUserBoard(int difficulty, int[][] userBoard, int[][] answerBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                userBoard[i][j] = answerBoard[i][j];
            }
        }
        int randNum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                randNum = random.nextInt(10);
                if ((randNum >= 1) && (randNum <= difficulty)) {
                    userBoard[i][j] = 0;
                }
            }
        }
    }

    //Creates a List collection, adds numbers from 1-9 to the list, then shuffles it
    //Uses checkBoard method to check if selected number is valid according to Sudoku rules
    //Iterates through the list adding the selected number to the answerBoard
    public static boolean fillBoard(int row, int column, int[][] answerBoard) {
        if (column == 9) {
            column = 0;
            row++;
            if (row == 9) {
                return true;
            }
        }

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums, random);

        int num = 0;
        for (int i = 0; i < 9; i++) {
            num = nums.get(i);
            if (CheckBoards.checkBoard(row, column, num, answerBoard)) {
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class SudokuBoard {

    private int[][] userBoard;
    private int[][] answerBoard;

    public SudokuBoard() {
        userBoard = new int[9][9];
        answerBoard = new int[9][9];
    }

    //Sets answerBoard to an empty 9x9 2d int array
    //Runs fillBoard to fill the answerBoard
    //Runs fillUserBoard to fill the userBoard using the filled answerBoard
    //Runs printBoard to print the userBoard
    public static void initialiseBoard(int difficulty, int[][] userBoard, int[][] answerBoard) {
        FillBoards.fillBoard(0, 0, answerBoard);
        FillBoards.fillUserBoard(difficulty, userBoard, answerBoard);
        SudokuBoardPrinter.printBoard(userBoard);
    }

    //Adds the numInput into the board at the co-ordinates specified by YInput and XInput
    public void updateBoard(int XInput, int YInput, int numInput) {
        userBoard[(YInput - 1)][(XInput - 1)] = numInput;
    }

    //Returns the userBoard
    public int[][] getUserBoard() {
        return userBoard;
    }

    //Returns the answerBoard
    public int[][] getAnswerBoard() {
        return answerBoard;
    }

    //Clears the boards of the last game's numbers
    public static void clearBoards(int[][] userBoard, int[][]answerBoard) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                answerBoard[i][j] = 0;
                userBoard[i][j] = 0;
            }
        }
    }
}
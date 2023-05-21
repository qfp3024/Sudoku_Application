/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class SudokuBoardPrinter {

    //Prints the numbers that act as the co-ordinates outside the Sudoku box
    //Prints the top, bottom, and sides of the puzzle box
    //Runs the printRow function to print the rows
    public static void printBoard(int[][] userBoard) {

        System.out.println(" X123 456 789");
        System.out.println("Y+---+---+---+");

        for (int i = 0; i < 9; i++) {
            System.out.print((i + 1) + "|");

            printRow(userBoard, i);

            System.out.println("|");
            if (i == 2) {
                System.out.println(" +---+---+---+");
            } else if (i == 5) {
                System.out.println(" +---+---+---+");

            }
        }
        System.out.println(" +---+---+---+");
    }

    //Prints the numbers containted in the userBoard row by row
    //If the number is a zero, then a space " " is printed instead
    //Prints "|" every 3 numbers/spaces to split the boxes
    public static void printRow(int[][] userBoard, int i) {
        for (int j = 0; j < 9; j++) {
            if (userBoard[i][j] == 0) {
                System.out.print(" ");
            } else {
                System.out.print(userBoard[i][j]);
            }
            if (j == 2) {
                System.out.print("|");
            } else if (j == 5) {
                System.out.print("|");
            }
        }
    }
}

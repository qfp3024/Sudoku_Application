/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
//import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGame {

    int userBoard[][];
    int answerBoard[][];
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuDB sudokuDB;

    public SudokuGame() {
        userBoard = sudokuBoard.userBoard;
        answerBoard = sudokuBoard.answerBoard;
        sudokuDB = new SudokuDB();
    }
    Scanner scanner = new Scanner(System.in);

    //Runs initialiseUser to set the current user
    //Sets up the board by running initialiseBoard
    public void initialiseGame() {
        sudokuDB.connectSudokuDB();
//        int difficulty = selectDifficulty();
        int difficulty = 0;
        SudokuBoard.clearBoards(userBoard, answerBoard);
        SudokuBoard.initialiseBoard(difficulty, userBoard, answerBoard);
//        userMVC();
        sudokuMVC();

    }

    public void userMVC() {
        UserView view = new UserView();
        UserModel model = new UserModel(view);

        model.addObserver(view);

        UserController controller = new UserController();
        controller.addModel(model);
        controller.addView(view);
        view.addController(controller);
    }

    public void sudokuMVC() {
        long startTime = System.nanoTime();
        SudokuView view = new SudokuView(userBoard);
        SudokuModel model = new SudokuModel(view, startTime);

        model.addObserver(view);

        SudokuController controller = new SudokuController(model, view, sudokuBoard);
        controller.addModel(model);
        controller.addView(view);
    }

    //Asks user if they want to update their time (score) 
    //If yes; runs addUserMap method with the puzzle time
    //If no, the function ends
//    public void updateTime(long startTime) {
//        boolean validInput = false;
//        char userInput = ' ';
//        Integer timeInteger = convertToMinutes(startTime);
//
//        System.out.println("Would you like to update your time? (y or n)");
//        while (!validInput) {
//            userInput = scanner.next().charAt(0);
//            switch (userInput) {
//                case 'y':
//                    user.addUserMap(timeInteger);
//                    validInput = true;
//                    break;
//                case 'n':
//                    validInput = true;
//                    break;
//                default:
//                    System.out.println("Please enter y or n");
//                    break;
//            }
//        }
//    }

    // Asks user if they want to replay the game, if yes; the initialiseGame and playGame methods are run
    // if no, prints thank you message and ends program
//    public void replay() {
//        boolean validInput = false;
//        System.out.println("Would you like to play again? (y or n)");
//        while (!validInput) {
//            char replay = scanner.next().charAt(0);
//            switch (replay) {
//                case 'y':
//                    validInput = true;
//                    initialiseGame();
//                    playGame();
//                    break;
//                case 'n':
//                    System.out.println("Thank you for playing!");
//                    validInput = true;
//                    break;
//                default:
//                    System.out.println("Please enter y or n");
//                    validInput = false;
//                    break;
//            }
//        }
//    }
    //Asks for X, Y, and number inputs, then runs method to update the board with
    // the new number, before printing the new board
    // Runs endGame() method once the user enters 0
//    public void playGame() {
//        long startTime = System.nanoTime();
//        int XInput = 0;
//        int YInput = 0;
//        int numInput = 0;
//
//        while (true) {
//            System.out.print("X:");
//            XInput = inputNumber();
//            if (XInput == 0) {
//                break;
//            }
//            System.out.print("Y:");
//            YInput = inputNumber();
//            if (YInput == 0) {
//                break;
//            }
//
//            System.out.print("Number:");
//            numInput = inputNumber();
//            if (numInput == 0) {
//                break;
//            }
//
//            System.out.println("\n");
//            userBoard.updateBoard(XInput, YInput, numInput);
//            SudokuBoardPrinter.printBoard(userBoard.getUserBoard());
//        }
//        endGame(startTime);
//    }
   
    //Uses the System.nanoTime to measure how long the game has been running since startTime was declared
    //Finds the seconds the game was running for by subtracting startTime from the time at the end
    //Converts to minutes, and returns the time, in minutes.
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGame {

    private Users user;
    private SudokuBoard userBoard;
    private SudokuBoard answerBoard;
    private SudokuGUI sudokuGUI;

    public SudokuGame() {
        user = new Users();
        userBoard = new SudokuBoard();
        answerBoard = new SudokuBoard();
        sudokuGUI = new SudokuGUI();
    }
    Scanner scanner = new Scanner(System.in);

    //Runs printIntro function to print the introduction to the game
    //Runs initialiseUser to set the current user
    //Sets difficulty by running selectDifficulty
    //Sets up the board by running initialiseBoard
    public void initialiseGame() {
          sudokuGUI.printUserGUI();
//        printIntro();
//        user.initialiseUser();
//        int difficulty = selectDifficulty();
//        SudokuBoard.clearBoards( userBoard.getUserBoard(), answerBoard.getAnswerBoard());
//        SudokuBoard.initialiseBoard(difficulty, userBoard.getUserBoard(), answerBoard.getAnswerBoard());
    }

    //Asks user to input difficulty level, validates input between 1 and 9
    // reutrns difficulty
    public int selectDifficulty() {
        int difficulty = 0;

        while (difficulty < 0 || difficulty > 9) {
            System.out.println("Select your difficulty:\n"
                    + "Enter a number from 1-9 "
                    + "(1 is easiest, 9 is hardest)\n");
            System.out.print("Difficulty:");
            difficulty = inputNumber();
            System.out.println("\n");
        }
        return difficulty;
    }

    //Prints introduction text
    public void printIntro() {
        System.out.println("Welcome to Sudoku!"
                + "\nTo add a number into the board,"
                + "\nwhen prompted enter the X coordinate and the Y coordanite"
                + "\nof the space you want to add it to,"
                + "\nand then enter the number you want to add\n");

        System.out.println("Enter 0 at any time to check if you're correct or quit\n");
    }

    //Checks if the borard was completed correctly and prints message accordingly
    // If completed correctly, updateTime function is run
    // Either way, replay function is run
    public void endGame(long startTime) {
        if (CheckBoards.checkBoardCorrect(userBoard.getUserBoard(), answerBoard.getAnswerBoard())) {
            System.out.println("Congratulations, you correctly completed the Sudoku Board!");

            if (!user.userMap.containsKey(user.username)) {
                updateTime(startTime);
            } else {
                Integer timeInteger = convertToMinutes(startTime);
                System.out.println("Time: " + timeInteger + " minutes");
                updateTime(startTime);
            }
            replay();

        } else {
            System.out.println("You did not successfully complete the Sudoku Board");
            replay();
        }
    }

    //Asks user if they want to update their time (score) 
    //If yes; runs addUserMap method with the puzzle time
    //If no, the function ends
    public void updateTime(long startTime) {
        boolean validInput = false;
        char userInput = ' ';
        Integer timeInteger = convertToMinutes(startTime);

        System.out.println("Would you like to update your time? (y or n)");
        while (!validInput) {
            userInput = scanner.next().charAt(0);
            switch (userInput) {
                case 'y':
                    user.addUserMap(timeInteger);
                    validInput = true;
                    break;
                case 'n':
                    validInput = true;
                    break;
                default:
                    System.out.println("Please enter y or n");
                    break;
            }
        }
    }

    // Asks user if they want to replay the game, if yes; the initialiseGame and playGame methods are run
    // if no, prints thank you message and ends program
    public void replay() {
        boolean validInput = false;
        System.out.println("Would you like to play again? (y or n)");
        while (!validInput) {
            char replay = scanner.next().charAt(0);
            switch (replay) {
                case 'y':
                    validInput = true;
                    initialiseGame();
                    playGame();
                    break;
                case 'n':
                    System.out.println("Thank you for playing!");
                    validInput = true;
                    break;
                default:
                    System.out.println("Please enter y or n");
                    validInput = false;
                    break;
            }
        }
    }

    //Asks for X, Y, and number inputs, then runs method to update the board with
    // the new number, before printing the new board
    // Runs endGame() method once the user enters 0
    public void playGame() {
        long startTime = System.nanoTime();
        int XInput = 0;
        int YInput = 0;
        int numInput = 0;

        while (true) {
            System.out.print("X:");
            XInput = inputNumber();
            if (XInput == 0) {
                break;
            }
            System.out.print("Y:");
            YInput = inputNumber();
            if (YInput == 0) {
                break;
            }

            System.out.print("Number:");
            numInput = inputNumber();
            if (numInput == 0) {
                break;
            }

            System.out.println("\n");
            userBoard.updateBoard(XInput, YInput, numInput);
            SudokuBoardPrinter.printBoard(userBoard.getUserBoard());
        }
        endGame(startTime);
    }

    //Verifies the user's input by checking whether the inputted number was between one and nine
    // and that the input was actually a number using a while loop.
    //Then returns the input
    public int inputNumber() {
        int userInput = 0;
        boolean validInput = false;
        while (validInput != true) {
            try {
                userInput = scanner.nextInt();
                if ((userInput >= 0) && (userInput <= 9)) {
                    validInput = true;
                } else {
                    System.out.print("Please enter a valid input between 1 and 9:");
                    userInput = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid input between 1 and 9:");
                scanner.next();
            }
        }
        return userInput;

    }

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

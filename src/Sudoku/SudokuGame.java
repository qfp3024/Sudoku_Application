/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */

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

    //Runs initialiseUser to set the current user
    //Sets up the board by running initialiseBoard
    public void initialiseGame() {
        sudokuDB.connectSudokuDB();
        int difficulty = 0;
        SudokuBoard.clearBoards(userBoard, answerBoard);
        SudokuBoard.initialiseBoard(difficulty, userBoard, answerBoard);
        userMVC();

    }

    public void userMVC() {
        UserView view = new UserView();
        UserModel model = new UserModel(view);

        model.addObserver(view);

        UserController controller = new UserController(this);
        controller.addModel(model);
        controller.addView(view);
        view.addController(controller);
    }

    public void sudokuMVC(String username) {
        SudokuView view = new SudokuView(userBoard, username);
        SudokuModel model = new SudokuModel(view);

        model.addObserver(view);

        SudokuController controller = new SudokuController(model, view, sudokuBoard, username);
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
}

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
    private String username = null;

    public SudokuGame() {
        userBoard = sudokuBoard.userBoard;
        answerBoard = sudokuBoard.answerBoard;
        sudokuDB = new SudokuDB();
    }

    //Runs connectSudokuDB method in "sudokuDB" to connect the database
    //Sets default difficulty to 2 (Beginner)
    //Clears and initialises (Create and/or fills) the user and answerBaords
    //If username is not set, runs userMVC
    //If username is set runs sudokuMVC with username parameter
    public void initialiseGame() {
        sudokuDB.connectSudokuDB();
        int difficulty = 2;
        SudokuBoard.clearBoards(sudokuBoard.getUserBoard(), sudokuBoard.getAnswerBoard());
        SudokuBoard.initialiseBoard(difficulty, userBoard, answerBoard);
        if (username == null) {
        userMVC();
        } else {
            this.sudokuMVC(username);
        }
    }

    //Initialises UserView and UserModel classes, adds Observer to view and then initilises UserController
    public void userMVC() {
        UserView view = new UserView();
        UserModel model = new UserModel(view);

        model.addObserver(view);

        UserController controller = new UserController(this, view, model);
    }

    //Initialises SudokuView and SudokuModel classes, adds Observer to view and then initilises SudokuController
    public void sudokuMVC(String username) {
        SudokuView view = new SudokuView(userBoard, username);
        SudokuModel model = new SudokuModel(view);

        model.addObserver(view);

        SudokuController controller = new SudokuController(model, view, sudokuBoard, username);
    }
    
    //Sets the sudokuGame class username to be equal to the supplied username
    public void setUsername(String username) {
        this.username = username;
    }
}

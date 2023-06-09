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

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuDB sudokuDB;
    private String username = null;

    //Runs connectSudokuDB method in "sudokuDB" to connect the database
    public SudokuGame() {
        sudokuDB = new SudokuDB();
        sudokuDB.connectSudokuDB();
    }

    //Sets default difficulty to 2 (Beginner)
    //Clears and initialises (Create and/or fills) the user and answerBaords
    public void initialiseGame() {
        int difficulty = 2;
        SudokuBoard.clearBoards(sudokuBoard);
        SudokuBoard.initialiseBoard(difficulty, sudokuBoard);
        startUserGUI();
    }

    //If username is not set, runs userMVC
    //If username is set runs sudokuMVC with username parameter
    public void startUserGUI() {
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

        UserController controller = new UserController(this, view, model);
    }

    //Initialises SudokuView and SudokuModel classes, adds Observer to view and then initilises SudokuController
    public void sudokuMVC(String username) {
        SudokuView view = new SudokuView(sudokuBoard, username);
        SudokuModel model = new SudokuModel(view);

        SudokuController controller = new SudokuController(model, view, sudokuBoard, username);
    }

    //Sets the sudokuGame class username to be equal to the supplied username
    public void setUsername(String username) {
        this.username = username;
    }
}

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

    //Runs initialiseUser to set the current user
    //Sets up the board by running initialiseBoard
    public void initialiseGame() {
        sudokuDB.connectSudokuDB();
        int difficulty = 0;
//        SudokuBoard.clearBoards(userBoard, answerBoard);
        SudokuBoard.initialiseBoard(difficulty, userBoard, answerBoard);
        if (username == null) {
        userMVC();
        } else {
            this.sudokuMVC(username);
        }
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
    
    public void setUsername(String username) {
        this.username = username;
    }
}

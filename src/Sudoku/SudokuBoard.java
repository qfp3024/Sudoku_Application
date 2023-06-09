package Sudoku;

/**
 *
 * @author Bewick
 */
public class SudokuBoard {

    int[][] userBoard;
    int[][] answerBoard;

    public SudokuBoard() {
        userBoard = new int[9][9];
        answerBoard = new int[9][9];
    }

    //Sets answerBoard to an empty 9x9 2d int array
    //Runs fillBoard to fill the answerBoard
    //Runs fillUserBoard to fill the userBoard using the filled answerBoard
    //Runs printBoard to print the userBoard
    public static void initialiseBoard(int difficulty, SudokuBoard sudokuBoard) {
        int[][] userBoard = sudokuBoard.userBoard;
        int[][] answerBoard = sudokuBoard.answerBoard;
        FillBoards.fillBoard(0, 0, answerBoard);
        FillBoards.fillUserBoard(difficulty, sudokuBoard);
    }

    //Adds the numInput into the board at the co-ordinates specified by YInput and XInput
    public void updateBoard(int XInput, int YInput, int numInput) {
        userBoard[(YInput)][(XInput)] = numInput;
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
    public static void clearBoards(SudokuBoard sudokuBoard) {
        int[][] userBoard = sudokuBoard.userBoard;
        int[][] answerBoard = sudokuBoard.answerBoard;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                answerBoard[i][j] = 0;
                userBoard[i][j] = 0;
            }
        }
    }
}

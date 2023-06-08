/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import java.util.Observable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Bewick
 */
public class SudokuModel extends Observable {

    SudokuView view;
    private long startTime = System.nanoTime();
    private double totalTime = 0.00;
    private JTextField[][] board;
    private JComboBox<String> difficulty;
    private CheckBoards checkBoard;

    public SudokuModel(SudokuView view) {
        System.out.println("Model");
        this.view = view;
        this.board = view.getBoard();
        this.difficulty = view.getDifficulty();
        this.checkBoard = new CheckBoards();
    }

    //Defines and sets new JTextField 2D array variable equal to SudokuModel board
    //Defines and sets new String cellContent equal to the text of the cell located at row,column in 2D array
    //Checks cellContent is not equal to empty string, if so, runs checkSingleInput, setting cellContent to the result
    //Then check if cellContent is an int, if not, runs invalidInput, setting cellConent to the result
    //Finally sets the text of the cell at row,column in userBoard equal to cellContent
    //Updates the same cell in the sudokuModel board, and then updates the board in "view" using setBoard
    public void checkCellContent(int row, int column) {
        JTextField[][] userBoard = board;
        String cellContent = userBoard[row][column].getText();

        if (!cellContent.equals("")) {
            cellContent = checkSingleInput(cellContent);

            if (!checkIntInput(cellContent)) {
                cellContent = invalidInput(cellContent, userBoard, row, column);
            }
        }
        userBoard[row][column].setText(cellContent);
        board[row][column] = userBoard[row][column];
        view.setBoard(board);
    }

    //Checks if cellContent contains more than 2 characters , if so, gets cellContent as a charArray,
    //then takes the first number in the array and adds it to cellContent via an empty string,
    //this ensures only one character exists in each cell. Returns cellContent at the end
    public String checkSingleInput(String cellContent) {
        if (cellContent.length() >= 2) {
            char cellContentArray[] = cellContent.toCharArray();
            cellContent = "" + cellContentArray[0];
        }
        return cellContent;
    }

    //Cets the invalid cell text, updates the cell in the board,
    //and then sets the board changes in "view" using setBoard,
    //before running the input error popup method in "view"
    //Then returns cellContent
    public String invalidInput(String cellContent, JTextField[][] userBoard, int row, int column) {
        cellContent = "";
        userBoard[row][column].setText(cellContent);
        board[row][column] = userBoard[row][column];
        view.setBoard(board);
        view.InputError();
        return cellContent;
    }

    //Returns true if cellContent matches the regex and so contains only integers, false if it doesn't
    public boolean checkIntInput(String cellContent) {
        return cellContent.matches("-?\\d+");
    }

    //Sets the new difficultyNum int equal to the result of getDifficulty
    //Then runs the updateBoard function to update the sudokuBoard with the new difficulty
    public void setDifficulty(SudokuBoard sudokuBoard) {
        int difficultyNum = getDifficulty();
        updateBoard(difficultyNum, sudokuBoard);
    }

    //Clears and initialises (Fills) the userBoard and answerBoard
    //Then sets the sudokuBoard using setSudokuBoard and the newly updated userBoard as a parameter
    public void updateBoard(int difficulty, SudokuBoard sudokuBoard) {
        SudokuBoard.clearBoards(sudokuBoard);
        SudokuBoard.initialiseBoard(difficulty,sudokuBoard);
        setSudokuBoard(sudokuBoard.getUserBoard());
    }

    //Gets the selected item from the difficulty combobox, converts the result to a string,
    // then sets the value of difficultyNum depending on the difficultyString
    //Returns difficultyNum at the end
    public int getDifficulty() {
        int difficultyNum = 0;
        Object selectedDifficulty = difficulty.getSelectedItem();
        String difficultyString = selectedDifficulty.toString();

        if (difficultyString.equals("Beginner")) {
            difficultyNum = 2;
        } else if (difficultyString.equals("Amateur")) {
            difficultyNum = 3;
        } else if (difficultyString.equals("Intermediate")) {
            difficultyNum = 4;
        } else if (difficultyString.equals("Expert")) {
            difficultyNum = 5;
        } else if (difficultyString.equals("Master")) {
            difficultyNum = 6;
        }

        return difficultyNum;
    }

    //Sets new JTextField boardUser equal to the SudokuModel board
    //Cycles through each cell in the board, setting newNumber equal to the string version
    //of the number contained in the current cell
    //If newNumber is equal to 0, the current textField cell is set to be editable,
    //and newNumber is set to be equal to an empty string
    //If newNumber isn't equal to 0, the current textField cell is set to be uneditable
    //The text of the cell is set equal to newNumber, and the colour set to be black
    //At the end of the loop SudokuBoard board is set equal to boardUser and the "view" board is set using setBaord
    public void setSudokuBoard(int[][] userBoard) {
        JTextField[][] boardUser = board;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String newNumber = Integer.toString(userBoard[row][column]);

                if (newNumber.equals("0")) {
                    boardUser[row][column].setEditable(true);
                    newNumber = "";
                } else {
                    boardUser[row][column].setEditable(false);
                }
                boardUser[row][column].setText(newNumber);
                boardUser[row][column].setForeground(Color.black);
            }
        }
        board = boardUser;
        view.setBoard(board);
    }

    //Runs the merge board method, then returns the result of the checkBoardCorrect method
    public boolean endGame(SudokuBoard sudokuBoard) {
        mergeBoards(sudokuBoard.userBoard);
        return checkBoardCorrect(sudokuBoard);
    }

    //Runs the "checkBoard" checkBoardCorrect method to check if the userBoard and answerBoard match
    //If so totalTime is equal to the result of convertToMinutes, and true is returned
    //If no false is returned
    public boolean checkBoardCorrect(SudokuBoard sudokuBoard) {
        if (checkBoard.checkBoardCorrect(sudokuBoard)) {
            totalTime = convertToMinutes(startTime);
            return true;
        } else {
            return false;
        }
    }

    //Sets view number equal to 0, then iterates through each cell,
    //If the text of the cell is an empty string viewnumber is equal to 0
    //If not, viewNumber is equal to the int version of the cell text
    //the value of the cell is then set equal to viewNumber
    //And the updated userBoard is returned
    public int[][] mergeBoards(int[][] userBoard) {
        int viewNumber = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellText = board[row][column].getText();
                
                if (cellText.equals("")) {
                    viewNumber = 0;
                } else {
                    viewNumber = Integer.parseInt(cellText);
                }
                userBoard[row][column] = viewNumber;
            }
        }
        return userBoard;
    }

    //Sets new JTextField boardUser equal to the SudokuModel board
    //Sets cellContent equal to the text version of the current cell's value
    //Sets answerBoard equal to the retrieved answerBoard from sudokuBoard
    //Sets answer equal to the value of the current cell in answerBoard
    //Converts answer to a string
    //Runs the setColour method to set the text colour
    //Updates the "view" board by running setBoard
    public void showErrors(int row, int column, SudokuBoard sudokuBoard) {
        JTextField[][] boardUser = board;
        String cellContent = boardUser[row][column].getText();

        int answerBoard[][] = sudokuBoard.getAnswerBoard();
        int answer = answerBoard[row][column];
        String answerString = Integer.toString(answer);

        setColour(cellContent, answerString, row, column);

        view.setBoard(board);
    }

    //If cellContent is equal to answerString set the cell text colour to black
    //If cellContent is equal to an empty string set the cell text colour to black
    //If neither is true, set the cell text colour to red
    public void setColour(String cellContent, String answerString, int row, int column) {
        if (cellContent.equals(answerString)) {
            board[row][column].setForeground(Color.black);
        } else if (cellContent.equals("")) {
            board[row][column].setForeground(Color.black);
        } else {
            board[row][column].setForeground(Color.red);
        }
    }

    //Sets minutes equal to zero, set endTime equal to current nanoTime value using System.nanoTime
    //Calculate the duration using the difference between start and end times, divide by 1000000000 to get seconds value
    //Sets minutes equal to duration secs, if value is more than 60, divide by 60 to get minutes
    //Return minutes
    public double convertToMinutes(double startTime) {
        double minutes = 0;
        double endTime = System.nanoTime();
        double durationSecs = (endTime - startTime) / 1000000000;
        minutes = durationSecs;
        if (durationSecs >= 60) {
            minutes = (durationSecs / 60);
        }
        return minutes;
    }

    //Sets startTime equal to the current nanoTime using System.nanoTime
    public void resetTimer() {
        startTime = System.nanoTime();
    }

    //Return SudokuModel totalTime
    public double getTotalTime() {
        return totalTime;
    }

    //If toggleBtn has been selected (clicked) set the text of toggleBtn to be "ON" and helpUser to be true
    //If toggleBtn has been unselected (reclicked) set the text of toggleBtn to be "OFF" and helpUser to be false
    //and the button colour to be red
    //Update the button in "view" by running setToggleBtn
    //Return helpUser
    public boolean changeBtn(JToggleButton toggleBtn, boolean helpUser) {
        if (toggleBtn.isSelected()) {
            toggleBtn.setText("ON");
            helpUser = true;
        } else {
            toggleBtn.setText("OFF");
            toggleBtn.setBackground(Color.RED);
            helpUser = false;
        }
        view.setToggleBtn(toggleBtn);
        return helpUser;
    }

    //If the current cell in board is editable, set the text to be an empty string
    // and then update the board in "view" by running setBoard
    public void restartBoard(int row, int column) {
        if (board[row][column].isEditable() == true) {
            board[row][column].setText("");
            view.setBoard(board);
        }
    }
}

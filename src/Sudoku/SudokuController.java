package Sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Bewick
 */
public class SudokuController {

    private SudokuModel model;
    private SudokuView view;
    private GameEndMVC gameEnd = new GameEndMVC();
    private SudokuBoard sudokuBoard;
    private int[][] userBoard;
    private boolean helpUser = false;
    private final String username;
    private final int boardMax = 9;
    
    private JTextField[][] board;
    private JComboBox<String> difficulty;

    //Constructs various variables in the SudokuController class,
    //and adds Listener classes to components in the view class
    public SudokuController(SudokuModel model, SudokuView view, SudokuBoard sudokuBoard, String username) {
        this.model = model;
        this.view = view;
        this.sudokuBoard = sudokuBoard;
        this.userBoard = sudokuBoard.userBoard;
        this.username = username;
        this.board = view.getBoard();
        this.difficulty = view.getDifficulty();

        view.addButtonListener(new ButtonListener());
        view.addTextFieldFocusListener(new TextFieldFocusListener());
        view.addComboBoxListener(new ComboBoxListener());
        view.addToggleListener(new ToggleListener());
        view.addLogoutBtnListener(new LogoutButtonListener());
        view.addHowToBtnListener(new HowToButtonListener());
        view.addRestartBtnListener(new RestartButtonListener());
    }

    //Listens and runs the actionPerformed method if the toggleButton is pressed
    class ToggleListener implements ActionListener {

        //Gets the pressed toggleButton, and runs the changeBtn "model" method,
        //setting helpUser equal to the result. 
        //It then loops through each cell of the userBoard running, if helpUser is true, the "model" showErrors method
        //If helpUser is false, it runs setCellColour to set the text colour of the cell to black
        @Override
        public void actionPerformed(ActionEvent e) {
            JToggleButton toggleBtn = view.getToggle();
            helpUser = model.changeBtn(toggleBtn, helpUser);

            for (int row = 0; row < boardMax; row++) {
                for (int column = 0; column < boardMax; column++) {

                    if (helpUser) {
                        model.showErrors(row, column, sudokuBoard);
                    } else {
                        model.setCellColour(row, column);
                    }
                }
            }
        }
    }

    //Listens and runs actionPerformed if an option is selected in the "view" comboBox
    class ComboBoxListener implements ActionListener {

        //The method runs the "model" setDifficulty method with SudokuBoard as a parameter to set the board difficulty
        //and runs the reserTimer method to reset the timer
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setDifficulty(sudokuBoard);
            model.resetTimer();
        }
    }

    //Listens and runs actionPerfomed when the endGame button is pressed
    class ButtonListener implements ActionListener {

        //The method runs the "model" endGame method, if the method returns true, the "view" window is closed,
        //the difficulty is retrieved using getSelectedItem, and the gameEndMVC is run to create the gameEndGUI
        //If endGame returns false, an message dialog is triggered in "view" by running the incorrectBoard method
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.endGame(sudokuBoard)) {
                view.closeWindow();
                Object selectedDifficulty = difficulty.getSelectedItem();
                gameEnd.gameEndMVC(username, selectedDifficulty.toString(), model.getTotalTime(), helpUser);
            } else {
                view.incorrectBoard();
            }
        }
    }

    //Listens and runs actionPerformed when the logout button is pressed
    class LogoutButtonListener implements ActionListener {

        //Restarts the program by creating a new SudokuGame instance, and initialising the game,
        //however username is set to null so that the user can log in as a different user
        //Finally the "view" frame is disposed of by running the closeWindow method
        @Override
        public void actionPerformed(ActionEvent e) {
            SudokuGame sudokuGame = new SudokuGame();
            sudokuGame.setUsername(null);
            sudokuGame.initialiseGame();
            view.closeWindow();
        }
    }

    //Listens and runs actionPerformed when the user clicks out of a textField
    class TextFieldFocusListener extends FocusAdapter {

        //Cycles throught each cell of the board setting the text colour to black
        //and then setting the change by running setBoard.
        //It then runs checkCellContent to check the cell content is valid
        //If helpUser has been set to true, it runs showErrors to turn the invalid entered answers red
        @Override
        public void focusLost(FocusEvent e) {
            for (int row = 0; row < boardMax; row++) {
                for (int column = 0; column < boardMax; column++) {
                    model.setCellColour(row, column);
                    model.checkCellContent(row, column);
                    if (helpUser == true) {
                        model.showErrors(row, column, sudokuBoard);
                    }
                }
            }
        }
    }

    //Listens and runs actionPerformed when the "How To Play" button is clicked
    class HowToButtonListener implements ActionListener {

        //Runs the showHowTo method in "view" triggering a popup with the game instructions
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHowTo();
        }
    }

    //Listens and runs actionPerformed when the restart button is pressed
    class RestartButtonListener implements ActionListener {

        //It the cycles though each cell, running the "model" restartBaord method
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row = 0; row < boardMax; row++) {
                for (int column = 0; column < boardMax; column++) {
                    model.restartBoard(row, column);
                }
            }
        }
    }
}

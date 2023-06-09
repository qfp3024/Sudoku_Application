/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Bewick
 */
public class SudokuView {

    private JFrame frame = new JFrame();
    private JPanel btnPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel boardPanel = new JPanel(new GridBagLayout());
    private JPanel containerPanel = new JPanel(new BorderLayout());
    private JPanel optionsPanel = new JPanel();
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel Jusername;
    private JLabel gameTitle = new JLabel("Sudoku");
    private JLabel difficultyLabel = new JLabel("Difficulty:");
    private JLabel helpLabel = new JLabel("Show Errors:");
    private JTextArea myJTextArea;
    private JButton endGameBtn = new JButton("End Game");
    private JButton logoutBtn = new JButton("Logout");
    private JButton restartBtn = new JButton("Restart");
    private JButton howToBtn = new JButton("How to Play");
    private JToggleButton helpBtn = new JToggleButton("OFF");
    private GridBagConstraints grid = new GridBagConstraints();
    private GridBagConstraints title = new GridBagConstraints();
    private JComboBox<String> difficulty = new JComboBox<>();
    private JTextField[][] board = new JTextField[9][9];
    private Color bgColour = new Color(151, 192, 240);

    //Runs functions to generate the Sudoku Game GUI, sets the value of the username JLabel
    //Adds the board and options panels to containerPanel
    //Adds all panels to the frame
    public SudokuView(SudokuBoard sudokuBoard, String username) {
        setFrameSettings();
        Jusername = new JLabel(username);
        
        addTitle();
        fillComboBox();
        fillOptionsPanel();
        addSudokuGrid(sudokuBoard.userBoard);
        addButtons();

        containerPanel.add(boardPanel, BorderLayout.CENTER);
        containerPanel.add(optionsPanel, BorderLayout.NORTH);

        setBackgroundColour();
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    //Sets the font of and then adds gameTitle to the titlePanel
    public void addTitle() {
        gameTitle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(gameTitle);
    }

    //Sets the frame settings, including the size, initial screen location, title text, and layout
    public void setFrameSettings() {
        int defaultWidth = 450;
        int defaultHeight = 400;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(defaultWidth, defaultHeight);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Sudoku Board");
        frame.setLayout(new BorderLayout());
    }

    //Add all the necessary components to the optionsPanel
    public void fillOptionsPanel() {
        optionsPanel.add(usernameLabel);
        optionsPanel.add(Jusername);
        optionsPanel.add(difficultyLabel);
        optionsPanel.add(difficulty);
        optionsPanel.add(helpLabel);
        optionsPanel.add(helpBtn);
        helpBtn.setBackground(Color.RED);
    }

    //Add the buttons to btnPanel
    public void addButtons() {
        btnPanel.add(logoutBtn);
        btnPanel.add(howToBtn);
        btnPanel.add(restartBtn);
        btnPanel.add(endGameBtn);
    }

    //Adds options to the difficulty combobox
    public void fillComboBox() {
        difficulty.addItem("Beginner");
        difficulty.addItem("Amateur");
        difficulty.addItem("Intermediate");
        difficulty.addItem("Expert");
        difficulty.addItem("Master");
    }

    //Sets the background colour of each panel
    public void setBackgroundColour() {
        btnPanel.setBackground(bgColour);
        titlePanel.setBackground(bgColour);
        optionsPanel.setBackground(bgColour);
        boardPanel.setBackground(bgColour);
    }

    //Cycles through each cell in the 9x9 board
    //Sets cellNumber equal to the string version of the supplied userBoard cell value
    //Adds a new JTextField to the SudokuView board at the current row,column
    //Runs setEditable to set the cell editable or not depending on cellNumber
    //Runs setCellDisplay to set the display
    //Runs setGridBagConstraints to set how the textfields are displayed
    public void addSudokuGrid(int[][] userBoard) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellNumber = Integer.toString(userBoard[row][column]);
                board[row][column] = new JTextField(cellNumber);

                cellNumber = setEditable(cellNumber, row, column);

                setCellDisplay(row, column, cellNumber);
                setGridBagConstraints(row, column);
                boardPanel.add(board[row][column], grid);
            }
        }
    }

    //Sets text value, font, alignment, and background colour of the cell
    public void setCellDisplay(int row, int column, String cellNumber) {
        board[row][column].setText(cellNumber);
        board[row][column].setFont(new Font("Arial", Font.BOLD, 16));
        board[row][column].setHorizontalAlignment(JTextField.CENTER);
        board[row][column].setBackground(Color.WHITE);
    }

    //Sets the GridBagConstraints for the textfield
    public void setGridBagConstraints(int row, int column) {
        grid.gridx = column;
        grid.gridy = row;
        grid.fill = GridBagConstraints.BOTH;
        grid.insets = splitCells(row, column);
        grid.weightx = 1.0;
        grid.weighty = 1.0;
    }

    //If cellNumber is equal to 0, set the cell to be editable and set cellNumber to an empty string
    //If it's not equal to 0, set the cell to not be editable
    //Returns cellNumber at the end, in case it has been altered
    public String setEditable(String cellNumber, int row, int column) {
        if (cellNumber.equals("0")) {
            board[row][column].setEditable(true);
            cellNumber = "";
        } else {
            board[row][column].setEditable(false);
        }
        return cellNumber;
    }

    //Set splitBtm and splitSide to be false
    //If the current column value is 2 or 5, set splitBtm to be true
    //If the current row value is 2 or 5, set splitSise to be true
    public Insets splitCells(int row, int column) {
        boolean splitBtm = false;
        boolean splitSide = false;

        if (column == 2 || column == 5) {
            splitBtm = true;
        }
        if (row == 2 || row == 5) {
            splitSide = true;
        }

        return setInsets(splitBtm, splitSide);
    }

    //If splitBtm and splitSide are both true, returns an Inset with padding on the bottom and right
    //If splitBtm is true and splitSide isn't, returns an Inset with padding on the right
    //If splitBtm isn't true and splitSide is, returns an Inset with padding on the bottom
    //Else return an inset with no padding
    public Insets setInsets(boolean splitBtm, boolean splitSide) {
        if (splitBtm && splitSide) {
            return new Insets(0, 0, 5, 5);
        } else if (splitBtm && !splitSide) {
            return new Insets(0, 0, 0, 5);
        } else if (!splitBtm && splitSide) {
            return new Insets(0, 0, 5, 0);
        }
        return new Insets(0, 0, 0, 0);
    }

    //Add an actionListener to the difficulty combobox
    public void addComboBoxListener(ActionListener listener) {
        difficulty.addActionListener(listener);
    }

//Add an actionListener to the endGameBtn button
    public void addButtonListener(ActionListener listener) {
        endGameBtn.addActionListener(listener);
    }

    //Add an actionListener to the helpBtn button
    public void addToggleListener(ActionListener listener) {
        helpBtn.addActionListener(listener);
    }

    //Add an actionListener to the logoutBtn button
    public void addLogoutBtnListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }

    //Add an actionListener to each textfield in the board 2D array
    public void addTextFieldFocusListener(FocusAdapter listener) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board[row][column].addFocusListener(listener);
            }
        }
    }

    //Add an actionListener to the howToBtn button
    public void addHowToBtnListener(ActionListener listener) {
        howToBtn.addActionListener(listener);
    }

    //Add an actionListener to the restartBtn button
    public void addRestartBtnListener(ActionListener listener) {
        restartBtn.addActionListener(listener);
    }

    //Returns helpBtn
    public JToggleButton getToggle() {
        return helpBtn;
    }

    //Sets helpBtn to be equal to toggleBtn
    public void setToggleBtn(JToggleButton toggleBtn) {
        this.helpBtn = toggleBtn;
    }


    //Prompts a popup box to appear with the message, asking the user to input a single number
    public void InputError() {
        JOptionPane.showMessageDialog(null, "Please enter a single number", "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Prompts a popup box to appear with the message, notifying the user there is an incorrect number
    public void incorrectBoard() {
        JOptionPane.showMessageDialog(null, "There is at least one incorrect number", "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Prompts a popup box to appear with the message, explaining how to play the game
    public void showHowTo() {
        String message = "Complete the board by filling in the blanks with the missing numbers."
                + "\nEach box of 9x9 should have all numbers from 1-9 to be completed."
                + "\nHowever, there should only be one of each number in every row, column, and box"
                + "\nYour score decreases the longer you take, but is higher depending on the difficulty."
                + "\nShowing errors will half your total score. Have Fun!";
        JOptionPane.showMessageDialog(null, message, "How to Play", JOptionPane.PLAIN_MESSAGE);
    }

    //Closes the SudokuView GUI by disposing of the frame
    public void closeWindow() {
        frame.dispose();
    }

    //Returns board
    public JTextField[][] getBoard() {
        return board;
    }

    //Sets the SudokuView board to be equal to the supplied board
    public void setBoard(JTextField[][] board) {
        this.board = board;
    }

    //Returns difficulty
    public JComboBox<String> getDifficulty() {
        return difficulty;
    }
}

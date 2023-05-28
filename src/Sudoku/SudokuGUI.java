/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class SudokuGUI extends JFrame {

    //Login Variables
    private JPanel userPanel = new JPanel(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    private JLabel userIntroTitle = new JLabel("Welcome to Sudoku");
    private JLabel userIntro = new JLabel("Enter a username and password to login or sign up");
    private JLabel username = new JLabel("Username");
    private JLabel password = new JLabel("Password");
    public JTextField unInput = new JTextField(10);
    public JPasswordField pwInput = new JPasswordField(10);
    private JLabel nameError = new JLabel("Incorrect username or password!");
    private JButton loginButton = new JButton("Login");

    //Sudoku Game Variables
    public void printUserGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);

        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 4;
        grid.ipady = 20;
        userIntroTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        this.userPanel.add(userIntroTitle, grid);
        grid.gridy = 1;
        grid.gridwidth = 4;
        grid.ipady = 0;
        grid.insets = new Insets(0, 0, 10, 0);
        this.userPanel.add(userIntro, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.gridwidth = 2;
        this.userPanel.add(username, grid);
        grid.gridx = 2;
        grid.gridy = 2;
        this.userPanel.add(unInput, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        this.userPanel.add(password, grid);
        grid.gridx = 2;
        grid.gridy = 3;
        this.userPanel.add(pwInput, grid);

        grid.gridx = 0;
        grid.gridy = 4;
        grid.gridwidth = 4;
        grid.insets = new Insets(10, 0, 0, 0);
        grid.fill = GridBagConstraints.HORIZONTAL;
        this.userPanel.add(loginButton, grid);

        userPanel.setBackground(new Color(151, 192, 240));
        this.add(userPanel);
        this.setVisible(true);

    }

    private JTextField[][] board = new JTextField[9][9];
    private JPanel boardPanel = new JPanel(new GridBagLayout());
    private JLabel gameTitle = new JLabel("Sudoku");

    public void printGameGUI() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] answerBoard = sudokuBoard.getAnswerBoard();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Sudoku Board");

        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 11;
        boardPanel.add(gameTitle, grid);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                grid.gridx = row;
                grid.gridy = column + 1;
                grid.gridwidth = 1;

                grid.insets = splitCells(row, column);
                grid.fill = GridBagConstraints.BOTH;
                grid.weightx = 1.0;
                grid.weighty = 1.0;

                String cellNumber = Integer.toString(answerBoard[row][column]);
                board[row][column] = new JTextField(cellNumber);
                board[row][column].setSize(WIDTH, HEIGHT);
                boardPanel.add(board[row][column], grid);
            }
        }

        add(boardPanel);

        setVisible(true);
    }

    public Insets splitCells(int row, int column) {
        boolean splitBtm = false;
        boolean splitSide = false;

        if (column == 2 || column == 5) {
            splitBtm = true;
        }
        if (row == 2 || row == 5) {
            splitSide = true;
        }

        if (splitBtm && splitSide) {
            return new Insets(0, 0, 5, 5);
        } else if (splitBtm && !splitSide) {
            return new Insets(0, 0, 5, 0);
        } else if (!splitBtm && splitSide) {
            return new Insets(0, 0, 0, 5);
        }

        return new Insets(0, 0, 0, 0);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

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

/**
 *
 * @author Bewick
 */
public class SudokuView implements Observer {

    private JTextArea myJTextArea;
    JTextField[][] board = new JTextField[9][9];
    boolean inputError = false;
    private JPanel boardPanel = new JPanel(new GridBagLayout());
    private JLabel gameTitle = new JLabel("Sudoku");
    private JLabel errorLabel = new JLabel("Please enter a single number");
    private JButton endGameButton = new JButton("End Game");
    GridBagConstraints grid = new GridBagConstraints();
    JFrame frame = new JFrame("Game GUI");

    JLabel difficultyLabel = new JLabel("Difficulty:");
    JComboBox difficulty = new JComboBox();

    private SudokuController sudokuController;
    int width = 400;
    int height = 400;

    public SudokuView(int[][] userBoard) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setTitle("Sudoku Board");

        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 11;
        gameTitle.setFont(new Font("Arial", Font.BOLD, 24));
        boardPanel.add(gameTitle, grid);

        grid.gridy = 1;
        grid.gridwidth = 5;
        boardPanel.add(difficultyLabel, grid);
        difficulty.addItem("Beginner");
        difficulty.addItem("Ameteur");
        difficulty.addItem("Intermediate");
        difficulty.addItem("Expert");
        difficulty.addItem("Master");
        grid.gridx = 5;
        grid.gridwidth = 6;
        boardPanel.add(difficulty, grid);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                grid.gridx = row;
                grid.gridy = column + 2;
                grid.gridwidth = 1;

                grid.insets = splitCells(row, column);
                grid.fill = GridBagConstraints.BOTH;
                grid.weightx = 1.0;
                grid.weighty = 1.0;

                String cellNumber = Integer.toString(userBoard[column][row]);
                if (cellNumber.equals("0")) {
                    cellNumber = "";
                }
                board[row][column] = new JTextField(cellNumber);
                if (!cellNumber.equals("")) {
                    board[row][column].setEditable(false);
                }
                board[row][column].setFont(new Font("Arial", Font.BOLD, 16));
                boardPanel.add(board[row][column], grid);
            }
        }

        grid.gridx = 0;
        grid.gridy = 11;
        grid.gridwidth = 11;
        grid.insets = new Insets(5, 10, 0, 10);
        boardPanel.add(endGameButton, grid);
        frame.add(boardPanel);
        frame.setVisible(true);
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
    
    public void addComboBoxListener(ActionListener listener) {
        difficulty.addActionListener(listener);
    }

    public void addButtonListener(ActionListener listener) {
        endGameButton.addActionListener(listener);
    }

    public void addTextFieldFocusListener(FocusAdapter listener) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                board[row][column].addFocusListener(listener);
            }
        }
    }

    @Override
    public void update(Observable o, Object obj) {
        myJTextArea.append(obj + "\n");
    }

    public void InputError() {
        JOptionPane.showMessageDialog(null, "Please enter a single number", "Error", JOptionPane.ERROR_MESSAGE);
    }

}

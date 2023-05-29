/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class SudokuView implements Observer {

    private JTextArea myJTextArea;
    private JTextField[][] board = new JTextField[9][9];
    private JPanel boardPanel = new JPanel(new GridBagLayout());
    private JLabel gameTitle = new JLabel("Sudoku");
    private JButton endGameButton = new JButton("End Game");
    GridBagConstraints grid = new GridBagConstraints();
    
    private SudokuController sudokuController;

    public SudokuView() {
        JFrame frame = new JFrame("Game GUI");
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] answerBoard = sudokuBoard.getAnswerBoard();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setTitle("Sudoku Board");

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
                board[row][column].setSize(row, column);
                boardPanel.add(board[row][column], grid);
            }
        }

        grid.gridx = 0;
        grid.gridy = 10;
        grid.gridwidth = 11;
        grid.insets = new Insets(5,10,0,10);
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

    public void addController(SudokuController controller) {
        this.sudokuController = controller;
        endGameButton.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object obj) {
        myJTextArea.append(obj + "\n");
    }
}

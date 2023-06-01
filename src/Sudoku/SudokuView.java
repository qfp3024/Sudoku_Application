/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.BorderLayout;
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
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel Jusername;
    JTextField[][] board = new JTextField[9][9];
    private JPanel boardPanel = new JPanel(new GridBagLayout());
    private JLabel gameTitle = new JLabel("Sudoku");
    private JLabel difficultyLabel = new JLabel("Difficulty:");
    JComboBox<String> difficulty = new JComboBox<>();
    private JButton endGameBtn = new JButton("End Game");
    private JFrame frame = new JFrame();
    private GridBagConstraints grid = new GridBagConstraints();

    public SudokuView(int[][] userBoard, String username) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku Board");
        frame.setLayout(new BorderLayout());

        gameTitle.setFont(new Font("Arial", Font.BOLD, 24));
        Jusername = new JLabel(username);

        difficulty.addItem("Beginner");
        difficulty.addItem("Amateur");
        difficulty.addItem("Intermediate");
        difficulty.addItem("Expert");
        difficulty.addItem("Master");

        addSudokuGrid(userBoard);

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(usernameLabel);
        optionsPanel.add(Jusername);
        optionsPanel.add(difficultyLabel);
        optionsPanel.add(difficulty);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(boardPanel, BorderLayout.CENTER);
        containerPanel.add(optionsPanel, BorderLayout.NORTH);

        frame.add(gameTitle, BorderLayout.NORTH);
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.add(endGameBtn, BorderLayout.SOUTH);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void addSudokuGrid(int[][] userBoard) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String cellNumber = Integer.toString(userBoard[row][column]);
                if (cellNumber.equals("0")) {
                    cellNumber = "";
                }
                board[row][column] = new JTextField(cellNumber);
                board[row][column].setEditable(false);
                board[row][column].setFont(new Font("Arial", Font.BOLD, 16));
                board[row][column].setHorizontalAlignment(JTextField.CENTER);

                grid.gridx = column;
                grid.gridy = row;
                grid.fill = GridBagConstraints.BOTH;
                grid.insets = splitCells(row, column);
                grid.weightx = 1.0;
                grid.weighty = 1.0;
                boardPanel.add(board[row][column], grid);
            }
        }
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
            return new Insets(0, 0, 0, 5);
        } else if (!splitBtm && splitSide) {
            return new Insets(0, 0, 5, 0);
        }

        return new Insets(0, 0, 0, 0);
    }

    public void addComboBoxListener(ActionListener listener) {
        difficulty.addActionListener(listener);
    }

    public void addButtonListener(ActionListener listener) {
        endGameBtn.addActionListener(listener);
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

    public void incorrectBoard() {
        JOptionPane.showMessageDialog(null, "There is at least one incorrect number", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void closeWindow() {
        frame.dispose();
    }
}

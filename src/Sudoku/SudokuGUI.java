/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

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

    private JPanel userPanel = new JPanel();

    private JLabel userIntro = new JLabel("Welcome to Sudoku! To add a number into the board,when prompted enter the X coordinate and the Y coordanite of the space you want to add it to, and then enter the number you want to add");
    private JLabel username = new JLabel("Username");
    private JLabel password = new JLabel("Password");
    public JTextField unInput = new JTextField(10);
    public JPasswordField pwInput = new JPasswordField(10);
    private JLabel nameError = new JLabel("Incorrect username or password!");
    private JButton loginButton = new JButton("Login");

    public void printUserGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.setLocationRelativeTo(null);

        this.userPanel.add(userIntro);
        
        this.userPanel.add(username);
        this.userPanel.add(unInput);

        this.userPanel.add(password);
        this.userPanel.add(pwInput);

        this.userPanel.add(loginButton);

        this.add(userPanel);
        this.setVisible(true);

    }
}

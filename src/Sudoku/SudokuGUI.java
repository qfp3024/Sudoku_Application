/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.BorderLayout;
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

    private JPanel userNorthPanel = new JPanel();
    private JPanel userSouthPanel = new JPanel();

    private JLabel userIntroTitle = new JLabel("Welcome to Sudoku");
    private JLabel userIntro = new JLabel("Enter a username and password to login or sign up");
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

        this.userNorthPanel.add(userIntroTitle);
        this.userNorthPanel.add(userIntro);
        
        this.userSouthPanel.add(username);
        this.userSouthPanel.add(unInput);

        this.userSouthPanel.add(password);
        this.userSouthPanel.add(pwInput);

        this.userSouthPanel.add(loginButton);

        this.add(userNorthPanel, BorderLayout.NORTH);
        this.add(userSouthPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }
}

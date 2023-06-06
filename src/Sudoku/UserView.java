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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Bewick
 */
public class UserView implements Observer {

    //Login Variables
    private JTextArea myJTextArea;
    private JPanel userPanel = new JPanel(new GridBagLayout());
    GridBagConstraints grid = new GridBagConstraints();

    private JLabel userIntroTitle = new JLabel("Welcome to Sudoku");
    private JLabel userIntro = new JLabel("Enter a username and password to login or sign up");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");
    public JTextField unInput = new JTextField(10);
    public JPasswordField pwInput = new JPasswordField(10);
    private JLabel nameError = new JLabel("Incorrect username or password!");
    private JButton loginButton = new JButton("Login");
    private JFrame frame = new JFrame("User GUI");

    private UserController userController;

    //Sudoku Game Variables
    public UserView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

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
        frame.add(userPanel);
        frame.setVisible(true);

    }

    public void pswError() {
        JOptionPane.showMessageDialog(null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void unError() {
        JOptionPane.showMessageDialog(null, "Please enter a valid username\nUsernames can only contain letters and numbers ", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void addController(UserController controller) {
        this.userController = controller;
        loginButton.addActionListener(controller);
    }

    public String getunInput() {
        return unInput.getText();
    }

    public String getpwInput() {
        return pwInput.getText();
    }

    @Override
    public void update(Observable o, Object obj) {
        myJTextArea.append(obj + "\n");
    }

    public void closeWindow() {
        frame.dispose();
    }
}

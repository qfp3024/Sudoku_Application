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
import java.awt.event.ActionListener;
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
public class UserView{

    private JFrame frame = new JFrame("User Login");

    private JPanel userPanel = new JPanel(new GridBagLayout());
    private JLabel userIntroTitle = new JLabel("Welcome to Sudoku");
    private JLabel userIntro = new JLabel("Enter a username and password to login, sign up, or delete");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");
    public JTextField unInput = new JTextField(10);
    public JPasswordField pwInput = new JPasswordField(10);
    private JButton loginButton = new JButton("Login");
    private JButton deleteUserButton = new JButton("Delete User");
    private GridBagConstraints grid = new GridBagConstraints();
    private JTextArea myJTextArea;

    //Sets the frame settings
    //sets the font and gridbag constraints of userIntroTitle, then adds it to the userPanel
    //Sets the gridbag constraints each component and then adds them to userPanel
    //Sets the background colour of userPanel, then adds it to the frame
    //Sets the frame to be visible
    public UserView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 280);
        frame.setLocationRelativeTo(null);

        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 4;
        grid.ipady = 20;
        userIntroTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        userPanel.add(userIntroTitle, grid);
        grid.gridy = 1;
        grid.gridwidth = 4;
        grid.ipady = 0;
        grid.insets = new Insets(0, 0, 10, 0);
        userPanel.add(userIntro, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        grid.gridwidth = 2;
        userPanel.add(username, grid);
        grid.gridx = 2;
        grid.gridy = 3;
        userPanel.add(unInput, grid);

        grid.gridx = 0;
        grid.gridy = 4;
        userPanel.add(password, grid);
        grid.gridx = 2;
        grid.gridy = 4;
        userPanel.add(pwInput, grid);

        grid.gridx = 0;
        grid.gridy = 5;
        grid.gridwidth = 4;
        grid.insets = new Insets(10, 0, 0, 0);
        grid.fill = GridBagConstraints.HORIZONTAL;
        userPanel.add(loginButton, grid);
        grid.gridx = 0;
        grid.gridy = 6;
        grid.gridwidth = 4;
        grid.insets = new Insets(10, 0, 0, 0);
        grid.fill = GridBagConstraints.HORIZONTAL;
        userPanel.add(deleteUserButton, grid);

        userPanel.setBackground(new Color(151, 192, 240));
        frame.add(userPanel);
        frame.setVisible(true);

    }

    //Prompts an error message, which notifys the user about an incorrect password
    public void pswError() {
        JOptionPane.showMessageDialog(null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Prompts an error message, which notifys the user about an incorrect username
    public void unError() {
        JOptionPane.showMessageDialog(null, "Please enter a valid username\n"
                + "Usernames can only contain letters and numbers ", "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Prompts an confirmation message, which confirms user deletetion, with a yes or no option
    //If the user clicks yes, true is returned, if they click no, false is returned, false is returned by default
    public boolean checkDelete() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else if (result == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return false;
        }
    }

    //Adds an actionListener to the loginButton
    public void addLoginController(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    //Adds an actionListener to the deleteUseButton
    public void addDeleteController(ActionListener listener) {
        deleteUserButton.addActionListener(listener);
    }

    //Returns the text version of unInput
    public String getunInput() {
        return unInput.getText();
    }

    //Returns the text version of pwInput
    public String getpwInput() {
        return pwInput.getText();
    }

    //Closes the UserGUI by disposing of the view frame
    public void closeWindow() {
        frame.dispose();
    }
}

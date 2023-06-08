/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Bewick
 */
public class GameEndView {

    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel(new BorderLayout());
    private JPanel containerPanel = new JPanel(new GridBagLayout());
    private JPanel updatePanel = new JPanel(new BorderLayout());
    private JPanel btnPanel = new JPanel(new GridBagLayout());
    private JLabel congrats = new JLabel("Congratulations!");
    private JLabel completion = new JLabel("You successfully completed the game!");
    private JLabel newScoreLabel = new JLabel("You got a new score of: ");
    private JLabel newScore = new JLabel();
    private JLabel oldScoreLabel = new JLabel("You had an old score of: ");
    private JLabel oldScore = new JLabel();
    private JLabel askUpdate = new JLabel("Would you like to to update your score?");
    private JButton yesBtn = new JButton("Yes");
    private JButton noBtn = new JButton("No");
    private GridBagConstraints grid = new GridBagConstraints();
    private GridBagConstraints btnGrid = new GridBagConstraints();
    private Color bgColour = new Color(151, 192, 240);

    //Sets the frame's closing settings, size, sarting location on the screen, title, and backgroundColour
    //Adds Jcomponents to panels with necessary layout options set
    //Sets background Colour of each panel
    //Adds panels to the frame, the sets the frame to be visible
    public GameEndView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Game End");
        frame.setBackground(new Color(151, 192, 240));

        congrats.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(congrats, BorderLayout.NORTH);
        titlePanel.add(completion, BorderLayout.SOUTH);

        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridwidth = 1;
        containerPanel.add(newScoreLabel, grid);
        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = 1;
        containerPanel.add(newScore, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.gridwidth = 1;
        containerPanel.add(oldScoreLabel, grid);
        grid.gridx = 1;
        grid.gridy = 2;
        grid.gridwidth = 1;
        containerPanel.add(oldScore, grid);

        btnGrid.gridx = 0;
        btnGrid.gridy = 0;
        btnGrid.gridwidth = 1;
        btnGrid.weightx = 1.0;
        btnGrid.fill = GridBagConstraints.BOTH;
        btnPanel.add(yesBtn, btnGrid);

        btnGrid.gridx = 1;
        btnPanel.add(noBtn, btnGrid);

        askUpdate.setHorizontalAlignment(SwingConstants.CENTER);
        updatePanel.add(askUpdate, BorderLayout.NORTH);
        updatePanel.add(btnPanel, BorderLayout.SOUTH);

        congrats.setFont(new Font("Arial", Font.BOLD, 28));
        completion.setFont(new Font("Arial", Font.BOLD, 20));

        int fontSize = 20;
        setJPanelFont(fontSize, containerPanel);

        fontSize = 18;
        setJPanelFont(fontSize, updatePanel);

        titlePanel.setBackground(bgColour);
        containerPanel.setBackground(bgColour);
        updatePanel.setBackground(bgColour);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.add(updatePanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    //Sets the font and font size of each component in the supplied Jpanel
    public void setJPanelFont(int fontSize, JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                Font font = label.getFont();
                label.setFont(font.deriveFont(Font.PLAIN, fontSize));
            }
        }
    }

    //Sets the displayed text of the oldScore JLabel if the score is more than 0
    //If not, removes the containerPanel from the frame, removes the oldScore and Label
    //This is done because the user must be new and thus have no old score to display
    //The update containerPanel is added back to the frame
    public void setOldScore(double score) {
        if (score > 0) {
            this.oldScore.setText(Double.toString(score));
        } else {
            this.frame.remove(containerPanel);
            containerPanel.remove(oldScoreLabel);
            containerPanel.remove(oldScore);
            frame.add(containerPanel, BorderLayout.CENTER);
        }
    }

    //Changes the score into a string, then sets the displayed text of the newScore JLabel
    public void setNewScore(double score) {
        String scoreString = Double.toString(score);
        this.newScore.setText(scoreString);
    }

    //Adds an actionListener to yesBtn
    public void addYesButtonListener(ActionListener listener) {
        yesBtn.addActionListener(listener);
    }

    //Adds an actionListener to noBtn
    public void addNoButtonListener(ActionListener listener) {
        noBtn.addActionListener(listener);
    }

    //Triggers a dialog box to appear asking the user to play again
    //If yes is selected, return true
    //If no is selected, return false
    //If closed, return false by default
    public boolean isReplay() {
        int replay = JOptionPane.showConfirmDialog(null, "Thank you for playing, would you like to play again?", "Replay", JOptionPane.YES_NO_OPTION);
        if (replay == JOptionPane.YES_OPTION) {
            return true;
        } else if (replay == JOptionPane.NO_OPTION) {
            return false;
        } else {
            return false;
        }

    }

    //Closes the view GUI window
    public void closeWindow() {
        frame.dispose();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bewick
 */
public class GameEndView {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel(new GridBagLayout());
    private JLabel congrats = new JLabel("Congratulations, you successfully completed the game!");
    private JLabel newScoreLabel = new JLabel("You got a score of: ");
    private JLabel newScore = new JLabel();
    private JLabel oldScoreLabel = new JLabel("You had an old score of: ");
    private JLabel oldScore = new JLabel();
    private JLabel askUpdate = new JLabel("Would you like to to update your score?");
    private GridBagConstraints grid = new GridBagConstraints();
    private JButton yesBtn = new JButton("Yes");
    private JButton noBtn = new JButton("No");

    public GameEndView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Game End");

        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 2;
        panel.add(congrats, grid);
        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridwidth = 1;
        panel.add(newScoreLabel, grid);
        grid.gridx = 1;
        grid.gridy = 1;
        grid.gridwidth = 1;
        panel.add(newScore, grid);
        grid.gridx = 0;
        grid.gridy = 2;
        grid.gridwidth = 1;
        panel.add(oldScoreLabel, grid);
        grid.gridx = 1;
        grid.gridy = 2;
        grid.gridwidth = 1;
        panel.add(oldScore, grid);
        grid.gridx = 0;
        grid.gridy = 3;
        grid.gridwidth = 2;
        panel.add(askUpdate, grid);

        grid.gridx = 0;
        grid.gridy = 4;
        panel.add(yesBtn, grid);

        grid.gridx = 1;
        grid.gridy = 4;
        panel.add(noBtn, grid);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void setOldScore(int score) {
        this.oldScore.setText(Integer.toString(score));
    }

    public void setNewScore(double score) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String scoreString = decimalFormat.format(score);
        this.newScore.setText(scoreString);
    }

    public void addYesButtonListener(ActionListener listener) {
        yesBtn.addActionListener(listener);
    }
    
    public void addNoButtonListener(ActionListener listener) {
        noBtn.addActionListener(listener);
    }
}

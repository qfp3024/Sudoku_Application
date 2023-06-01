/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Bewick
 */
public class GameEndView {

    private JFrame frame = new JFrame();
    private JLabel congrats = new JLabel("Congratulations, you successfully completed the game!");
    private JLabel newScoreLabel = new JLabel("You got a score of: ");
    private JLabel newScore = new JLabel();
    private JLabel oldScoreLabel = new JLabel("You had an old score of: ");
    private JLabel oldScore = new JLabel();
    private JLabel askUpdate = new JLabel("Would you like to to update your score?");
    
    public GameEndView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Game End");

        
        frame.add(congrats);
        frame.add(newScoreLabel);
        frame.add(newScore);
        frame.add(oldScoreLabel);
        frame.add(oldScore);
        frame.add(askUpdate);
        
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    public void setScore(int score) {
        this.oldScore.setText(Integer.toString(score));
    }
}

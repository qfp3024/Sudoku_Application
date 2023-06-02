/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Bewick
 */
public class GameEndController {

    private GameEndModel model;
    private GameEndView view;
    private double time;
    private String username;
    private boolean replayGame = false;

    public GameEndController(GameEndView view, GameEndModel model, double time, String username) {
        this.view = view;
        this.model = model;
        this.time = time;
        this.username = username;
        
        view.addYesButtonListener(new GameEndController.yesButtonListener());
        view.addNoButtonListener(new GameEndController.noButtonListener());
    }


    class yesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed Yes");
            model.updateScore(time, username);
            if (view.isReplay()) {
                replayGame = true;
            }
        }
    }

    class noButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed No");
        }
    }

    public void setScores() {
        double oldScore = model.getOldScore();
        view.setOldScore(oldScore);

        double newScore = model.getNewScore();
        view.setNewScore(newScore);
    }

    public boolean getReplayGame() {
        return replayGame;
    }
}

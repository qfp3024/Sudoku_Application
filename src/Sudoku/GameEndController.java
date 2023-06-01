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

    GameEndModel model;
    GameEndView view;

    public GameEndController(GameEndView view, GameEndModel model) {
        this.view = view;
        this.model = model;
        
        view.addYesButtonListener(new GameEndController.yesButtonListener());
        view.addNoButtonListener(new GameEndController.noButtonListener());
    }


    class yesButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed Yes");
        }
    }

    class noButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed No");
        }
    }

    public void setScores() {
        int oldScore = model.getOldScore();
        view.setOldScore(oldScore);

        double newScore = model.getNewScore();
        view.setNewScore(newScore);
    }

}

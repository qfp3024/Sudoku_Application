/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class GameEndController {

    GameEndModel model;
    GameEndView view;

    public GameEndController() {
        int score = model.getScore();
        view.setScore(score);
    }

    public void addModel(GameEndModel m) {
        this.model = m;
    }

    public void addView(GameEndView v) {
        this.view = v;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class GameEndMVC {

    public void GameEndMVC(String username, String difficulty, double time) {
        GameEndView view = new GameEndView();
        GameEndModel model = new GameEndModel(username);

//        model.addObserver(view);
        GameEndController controller = new GameEndController( view, model);
        model.calculateScore(difficulty, time);
        controller.setScores();

    }
}

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

    public void GameEndMVC(String username) {
        GameEndView view = new GameEndView();
        GameEndModel model = new GameEndModel(username);

//        model.addObserver(view);
        GameEndController controller = new GameEndController();
        controller.addModel(model);
        controller.addView(view);
    }
}

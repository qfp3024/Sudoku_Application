package Sudoku;

/**
 *
 * @author Bewick
 */
public class GameEndMVC {

    //The Model View Controller method to set up the MVC classes for the GameEnd GUI
    public void gameEndMVC(String username, String difficulty, double time, boolean helpUser) {
        GameEndView view = new GameEndView();
        GameEndModel model = new GameEndModel(username);

        GameEndController controller = new GameEndController( view, model, time, username);
        model.calculateScore(difficulty, time, helpUser);
        controller.setScores();
    }
}

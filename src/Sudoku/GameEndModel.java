/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

/**
 *
 * @author Bewick
 */
public class GameEndModel {
    
   private SudokuDB database = new SudokuDB();
   private String username;
   
   public GameEndModel(String username) {
       this.username = username;
   }
   
   public int getScore() {
      return database.getUserScore(username);
   }
}

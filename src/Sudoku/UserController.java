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
public class UserController implements ActionListener {

    UserModel model;
    UserView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Controller: acting on model");
        model.loginUser();
    }
    
     public void addModel(UserModel m) {
        System.out.println("Controller: adding model");
        this.model = m;
    }

    public void addView(UserView v) {
        System.out.println("Controller: adding view");
        this.view = v;
    }
}

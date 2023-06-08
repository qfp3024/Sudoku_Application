/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Sudoku;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bewick
 */
public class ChangeBtnColourTest {
    
    private SudokuModel instance;
    private SudokuView view;
    public ChangeBtnColourTest() {
        int[][] userBoard = {{0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0}};
        String username = "User";
        view = new SudokuView(userBoard, username);
        instance = new SudokuModel(view);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changeBtn method, of class SudokuModel. test toggleBtn is selected and help user is false, return true
     */
    @Test
    public void testChangeBtnIsSelected() {
        System.out.println("changeBtn");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(true);
        boolean helpUser = false;
        boolean expResult = true;
        boolean result = instance.changeBtn(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of changeBtn method, of class SudokuModel. test toggleBtn is not selected and help user is false, return false
     */
    @Test
    public void testChangeBtnIsNotSelected() {
        System.out.println("changeBtn");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(false);
        boolean helpUser = false;
        boolean expResult = false;
        boolean result = instance.changeBtn(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of changeBtn method, of class SudokuModel. test toggleBtn is not selected and help user is true, return false
     */
    @Test
    public void testChangeBtnIsNotSelectedHelpUser() {
        System.out.println("changeBtn");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(false);
        boolean helpUser = true;
        boolean expResult = false;
        boolean result = instance.changeBtn(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of changeBtn method, of class SudokuModel. test toggleBtn is selected and help user is true, return true
     */
    @Test
    public void testChangeBtnIsSelectedHelpUser() {
        System.out.println("changeBtn");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(true);
        boolean helpUser = true;
        boolean expResult = true;
        boolean result = instance.changeBtn(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
}

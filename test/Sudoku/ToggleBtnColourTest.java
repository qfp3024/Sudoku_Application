/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Sudoku;

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
public class ToggleBtnColourTest {

    private SudokuModel instance;

    public ToggleBtnColourTest() {
        int[][] intArray = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        String username = "User";
        SudokuView view = new SudokuView(intArray, username);
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
     * Test of toggleBtnColour method, of class SudokuModel. test toggleBtn is
     * selected, return true
     */
    @Test
    public void testToggleBtnColourIsSelected() {
        System.out.println("toggleBtnColour");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(true);
        boolean helpUser = false;
        boolean expResult = true;
        boolean result = instance.toggleBtnColour(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }

    /**
     * Test of toggleBtnColour method, of class SudokuModel. test toggleBtn is not
     * selected, return false
     */
    @Test
    public void testToggleBtnColourIsNotSelected() {
        System.out.println("toggleBtnColour");
        JToggleButton toggleBtn = new JToggleButton();
        toggleBtn.setSelected(false);
        boolean helpUser = false;
        boolean expResult = false;
        boolean result = instance.toggleBtnColour(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of toggleBtnColour method, of class SudokuModel. test toggleBtn is not selected by default,
     * return false
     */
    @Test
    public void testToggleBtnColourDefault() {
        System.out.println("toggleBtnColour");
        JToggleButton toggleBtn = new JToggleButton();
        boolean helpUser = false;
        boolean expResult = false;
        boolean result = instance.toggleBtnColour(toggleBtn, helpUser);
        assertEquals(expResult, result);
    }
}

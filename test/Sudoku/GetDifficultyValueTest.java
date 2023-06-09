/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Sudoku;

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
public class GetDifficultyValueTest {
    
    private GameEndModel gameEndModel;
    
    public GetDifficultyValueTest() {
        String username = "User";
        this.gameEndModel = new GameEndModel(username);
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
     * Test of getDifficultyValue method, of class GameEndModel. test difficulty is "Beginner" get difficultyValue 1.0
     */
    @Test
    public void testGetDifficultyValueBeginner() {
        System.out.println("getDifficultyValue");
        String difficulty = "Beginner";
        double expResult = 1.0;
        double result = gameEndModel.getDifficultyValue(difficulty);
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getDifficultyValue method, of class GameEndModel. test difficulty is "Amateur" get difficultyValue 2.0
     */
    @Test
    public void testGetDifficultyValueAmateur() {
        System.out.println("getDifficultyValue");
        String difficulty = "Amateur";
        double expResult = 2.0;
        double result = gameEndModel.getDifficultyValue(difficulty);
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getDifficultyValue method, of class GameEndModel. test difficulty is "Intermediate" get difficultyValue 3.0
     */
    @Test
    public void testGetDifficultyValueIntermediate() {
        System.out.println("getDifficultyValue");
        String difficulty = "Intermediate";
        double expResult = 3.0;
        double result = gameEndModel.getDifficultyValue(difficulty);
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getDifficultyValue method, of class GameEndModel. test difficulty is "Expert" get difficultyValue 4.0
     */
    @Test
    public void testGetDifficultyValueExpert() {
        System.out.println("getDifficultyValue");
        String difficulty = "Expert";
        double expResult = 4.0;
        double result = gameEndModel.getDifficultyValue(difficulty);
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getDifficultyValue method, of class GameEndModel. test difficulty is "Master" get difficultyValue 5.0
     */
    @Test
    public void testGetDifficultyValueMaster() {
        System.out.println("getDifficultyValue");
        String difficulty = "Master";
        double expResult = 5.0;
        double result = gameEndModel.getDifficultyValue(difficulty);
        assertEquals(expResult, result, 0);
    }
}

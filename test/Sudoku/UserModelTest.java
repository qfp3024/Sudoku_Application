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
public class UserModelTest {
    
    private UserModel instance;
    public UserModelTest() {
        UserView view = new UserView();
        instance = new UserModel(view);
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
     * Test of getValidUsername method, of class UserModel. test username is only letters return true
     */
    @Test
    public void testGetValidUsernameLetters() {
        System.out.println("getValidUsername");
        String username = "Username";
        boolean expResult = true;
        boolean result = instance.getValidUsername(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getValidUsername method, of class UserModel. test username is only numbers return true
     */
    @Test
    public void testGetValidUsernameNumbers() {
        System.out.println("getValidUsername");
        String username = "8005";
        boolean expResult = true;
        boolean result = instance.getValidUsername(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getValidUsername method, of class UserModel. test username is numbers and letters return true
     */
    @Test
    public void testGetValidUsernameBoth() {
        System.out.println("getValidUsername");
        String username = "U5ername";
        boolean expResult = true;
        boolean result = instance.getValidUsername(username);
        assertEquals(expResult, result);
    }
    
      
    /**
     * Test of getValidUsername method, of class UserModel. test username contains invalid characters return false
     */
    @Test
    public void testGetValidUsernameInvalid() {
        System.out.println("getValidUsername");
        String username = "U5ern@me";
        boolean expResult = false;
        boolean result = instance.getValidUsername(username);
        assertEquals(expResult, result);
    }
}

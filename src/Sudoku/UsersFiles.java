/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Bewick
 */
public class UsersFiles {
      
    //Reads in user's name and time(minutes) from the Sudoku_Users file
    //Uses BufferedReader and FileReader to take in the name and time as a string
    // and then uses .split to seperate the name and time
    // stores the name and time in given hashMap, then returns updated hashmap
    public static HashMap readUsers(HashMap<String, String> userMap) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("./resources/Sudoku_Users.txt"))) {
                String input = "";

                while ((input = br.readLine()) != null) {
                    String[] splitInput = input.split(" ");
                    String nameInput = splitInput[0];
                    String scoreInput = splitInput[1];
                    userMap.put(nameInput, scoreInput);
                }
            }

        } catch (IOException ex) {
            System.err.println("IOException Error: " + ex.getMessage());
        }
        return userMap;
    }

    //Gets the userMap and uses bufferedWrite and fileWrite to write the users back into the Sudoku_Users file
    //the for loop iterates through each key, appending the key and value into the file
    public static void writeUsers(HashMap<String, String> userMap) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Sudoku_Users.txt"))) {
                for (Object key : userMap.keySet()) {
                    bw.append(key.toString());
                    String valueString = userMap.get(key);
                    bw.append(" ");
                    bw.append(valueString);
                    bw.append("\n");

                }
            }

        } catch (IOException ex) {
            System.err.println("IOException Error: " + ex.getMessage());
        }
    }
}

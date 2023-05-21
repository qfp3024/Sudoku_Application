/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Bewick
 */
public class Users {

    HashMap<String, String> userMap;
    String username;

    public Users() {
        userMap = new HashMap<>();
        username = "";
    }

    //Runs getValidUsername
    //Prints a welcome message using the set username
    //Runs readUsers to get all of the uses that already exist
    //Prints previous time (score) of the user if they already have a score
    public void initialiseUser() {
        if (username.equals("")) {
            getValidUsername();
            System.out.println("Welcome " + username);
            UsersFiles.readUsers(userMap);
            if (userMap.containsKey(username)) {
                System.out.println("You have a previous time of " + userMap.get(username) + " minutes\n");
            }
        }
    }

    //Asks user for a name
    //Validates input using .matches
    //if inputted string is not valid, loops untill a valid string is entered
    public void getValidUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name:");
        username = scanner.next();

        boolean isValid = username.matches("[a-zA-Z\\s]+");

        while (!isValid) {
            System.out.println("Please enter your name properly");
            System.out.print("Enter name:");
            username = scanner.next();
            isValid = username.matches("[a-zA-Z\\s]+");
        }
    }

    //Converts user's time to string, enters user's name and time into the userMap
    //Runs writeUsers with the parametre userMap to write the updated user score onto the file
    public void addUserMap(Integer timeInteger) {
        String userTimeString = timeInteger.toString();
        userMap.put(username, userTimeString);
        UsersFiles.writeUsers(userMap);
    }
}

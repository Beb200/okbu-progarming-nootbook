package edu.okbu;

import java.util.Scanner;

public class Player {
    String playerName;
    Choices choice;
    int wins;
    public Player(String name) {
 
    }

    public Choices getChoice() {
        String input;
        Scanner scnr = new Scanner(System.in);
        input = scnr.next();
        // Prompt the user for their play and return a value from the
        // Choices class.
        System.out.println("Enter p for Paper");
        System.out.println("Enter r for Rock");
        System.out.println("Enter s for Scissors");
        if(input.equals("p")){
            return Choices.PAPER;
        }
        else if(input.equals("r")){
            return Choices.ROCK;
        }
        else if(input.equals("s")){
            return Choices.SCISSORS;
        }
        else{
            return Choices.PAPER;
        }
    }
}

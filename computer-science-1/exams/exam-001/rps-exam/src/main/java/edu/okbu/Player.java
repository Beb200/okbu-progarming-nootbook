package edu.okbu;

import java.util.Scanner;

public class Player {
    String name;
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
    public String getName(){
        return name;
    }
    public void setName(String name){

    }
    public int getWins(){
        return 0;
    }
    public void addWins(){

    }
    public void resetWins(){

    }
    public String toString(){
        return "Player name:";
    }
}

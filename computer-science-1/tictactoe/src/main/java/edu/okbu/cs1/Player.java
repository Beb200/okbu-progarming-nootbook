package edu.okbu.cs1;

import java.util.Scanner;

public class Player {
    String name;
    int playerRow;
    int playerCol;

    Scanner scnr = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int choiceRow(){
        playerRow = scnr.nextInt();
        return playerRow;
    }

    public int choiceCol(){
        playerCol = scnr.nextInt();
        return playerCol;
    }
    
}

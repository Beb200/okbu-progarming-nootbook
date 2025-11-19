package edu.okbu.cs1;

import java.util.Scanner;

//import java.util.Scanner;

public class Game {
    int i;
    int j;
    int tracker1;
    int tracker2;
    int tracker3;
    Scanner scnr = new Scanner(System.in);
    int row;
    int col;

    public void play(String hidden[][], String player[][]){
        System.out.print("Type the row: ");
        row = (scnr.nextInt() - 1);
        System.out.print("Type the colem: ");
        col = (scnr.nextInt() - 1);

        if (hidden[row][col].equals("#")){
            hidden[row][col] = "M";
            player[row][col] = "M";
        }
        else{
            hidden[row][col] = "H";
            player[row][col] = "H";
        }
    }
    
    public void ship_1_sunk(String hidden[][]){
        tracker1 = 0;
        for(i = 0; i < hidden.length; i++){
            for(j = 0; j < hidden[i].length; j++){
                if (hidden[i][j].equals("1")){
                    tracker1 =+ 1;
                }
                
            }
        }
        if (tracker1 == 0){
            System.out.println("Ship 1 sunk");
        }
    }

    public void ship_2_sunk(String hidden[][]){
        tracker2 = 0;
        for(i = 0; i < hidden.length; i++){
            for(j = 0; j < hidden[i].length; j++){
                if (hidden[i][j].equals("2")){
                    tracker2 =+ 1;
                }
                
            }
        }
        if (tracker2 == 0){
            System.out.println("Ship 2 sunk");
        }
    }

    public void ship_3_sunk(String hidden[][]){
        tracker3 = 0;
        for(i = 0; i < hidden.length; i++){
            for(j = 0; j < hidden[i].length; j++){
                if (hidden[i][j].equals("3")){
                    tracker3 =+ 1;
                }
                
            }
        }
        if (tracker3 == 0){
            System.out.println("Ship 3 sunk");
        }
    }
}

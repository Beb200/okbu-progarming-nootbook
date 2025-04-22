package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game_Board  {
    int x;
    int y;
    int i;
    int j;
    int numGames;
    int numTurns;
    String test;
    String hidden[][];
    String player[][];

    FileInputStream fileIn = null;
    Scanner inFile = null;

    public void initialize() throws FileNotFoundException{
        fileIn = new FileInputStream("InFile.txt");
        inFile = new Scanner(fileIn);

        x = inFile.nextInt();
        y = inFile.nextInt();
        numGames = inFile.nextInt();
        numTurns = inFile.nextInt();
        
        hidden = new String[x][y];
        player = new String[x /*+ 1*/][y /*+ 1*/];
    }

    public void player(){
        for (i = 0; i < player.length; i++){
            for(j = 0; j < player[i].length; j++){
                player[i][j] = "#";
            }
        }
    }

    public void print_player(){
        for (i = 0; i < player.length; i++){
            System.out.print((i + 1) +" ");
            for(j = 0; j < player[i].length; j++){
                System.out.print(player[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (i = 0; i < player.length; i++){
            System.out.print((i + 1) + " ");
        }
        System.out.println();
    }

    public void hidden(){
        for(i = 0; i < hidden.length; i++){
            for(j = 0; j < hidden[i].length; j++){
                hidden[i][j] = inFile.next();
            }
        }
    }

    public String test_hidden(){
         for(i = 0; i < hidden.length; i++){
            for(j = 0; j < hidden[i].length; j++){
                test = hidden[i][j];
            }
            System.out.println();
        }
        return test;
    }
}

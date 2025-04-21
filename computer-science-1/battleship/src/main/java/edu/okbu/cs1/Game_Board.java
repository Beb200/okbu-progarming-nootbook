package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game_Board  {
    int x;
    int y;
    int i;
    int j;
    String test;
    String hidden[][] = new String[x][y];

    FileInputStream fileIn = null;
    Scanner inFile = null;

    public void initialize() throws FileNotFoundException{
        fileIn = new FileInputStream("InFile.txt");
        inFile = new Scanner(fileIn);

        x = inFile.nextInt();
        y = inFile.nextInt();   
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

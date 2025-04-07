package edu.okbu.cs1;

import java.io.FileNotFoundException;

/**

* Hello world!

*

*/

public class App

{

    public static void main( String[] args ) throws FileNotFoundException

    {

        int[] hzPlayers; // Declares an int array

        hzPlayers = new int[5];

 

        hzPlayers[0] = 123;

        hzPlayers[1] = 234;

        hzPlayers[2] = 567;

        hzPlayers[3] = 788;

        hzPlayers[4] = 76;

 

        System.out.println(hzPlayers[3]);

        System.out.println(hzPlayers.length);

 

        for (int idx = 0; idx < hzPlayers.length; idx++) {

            System.out.println(hzPlayers[idx]);

        }

 

        // Sum up the elements

        int sum = 0;

        for (int idx = 0; idx < hzPlayers.length; idx++) {

            sum = sum + hzPlayers[idx];

        }

        System.out.println(sum);

        System.out.println(((double)sum) / hzPlayers.length);

 

        int[] hzPLayer2 = {34, 55, 66, 77, 123, 678, 9, 4};

        System.out.println(hzPLayer2.length);


        String[][] board = new String[3][3];
        board[1][1] = "x";
        board[0][2] = "o";

        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                System.out.println("row " + row + " colum " + col + " is " + board[row][col]);
            }
        }

        ArrayPractice Prac = new ArrayPractice();
        Prac.initalize("inFile.txt","outFile.txt");
        System.out.print("Min:");
        System.out.println(Prac.findMin());
        System.out.print("Max:");
        System.out.println(Prac.findMax());
        System.out.println("Reversed:");
        Prac.reverse();
        System.out.print("Average:");
        System.out.println(Prac.findAverage());

    }

}
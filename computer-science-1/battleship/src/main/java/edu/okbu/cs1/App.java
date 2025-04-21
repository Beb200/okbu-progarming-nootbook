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
        Game_Board gb = new Game_Board();
        int i;
        int j;

        gb.initialize();
        gb.hidden();
        for(i = 0; i < gb.hidden.length; i++){
            for(j = 0; j < gb.hidden[i].length; j++){
                System.out.print(gb.hidden[i][j]);
            }
            System.out.println();
        }
    }
}

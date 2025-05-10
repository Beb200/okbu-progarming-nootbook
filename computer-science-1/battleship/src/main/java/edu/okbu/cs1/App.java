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
        Game g = new Game();
        int i;
        int j;

        gb.initialize();
        for (i = 0; i < gb.numGames; i++){
            gb.hidden();
            gb.player();
        /*for(i = 0; i < gb.hidden.length; i++){
            for(j = 0; j < gb.hidden[i].length; j++){
                System.out.print(gb.hidden[i][j]);
            }
            System.out.println();
        }*/
            for(i = 0; i < gb.numTurns; i++){
                gb.print_player();
                g.play(gb.hidden,gb.player);
                g.ship_1_sunk(gb.hidden);
                g.ship_2_sunk(gb.hidden);
                g.ship_3_sunk(gb.hidden);
            }
        }
    }
}

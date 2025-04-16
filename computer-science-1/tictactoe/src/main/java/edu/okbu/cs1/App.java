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
        gb.board();
        System.out.println(gb.Display());
        //gb.Display();
        //System.out.println(gb.board());

        The_Game tg = new The_Game();
        tg.initialize("inFile.txt");
        tg.winner(gb.game);
        tg.play(gb.game);
        while (tg.winner.equals("no winner")) {
            tg.player1Turn(gb.game);
            System.out.println(gb.Display());
            tg.player2Turn(gb.game);
            System.out.println(gb.Display());
            tg.winner(gb.game);
            System.out.println(tg.winner);
        }
    }
}

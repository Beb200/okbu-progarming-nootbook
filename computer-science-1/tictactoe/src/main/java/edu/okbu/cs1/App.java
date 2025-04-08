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
        tg.play(gb.game);
        System.out.println(tg.winner);
    }
}

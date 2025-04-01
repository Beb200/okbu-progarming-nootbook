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
        System.out.println(gb.Display());
        //gb.Display();

        The_Game tg = new The_Game();
        tg.initialize("inFile.txt");
    }
}

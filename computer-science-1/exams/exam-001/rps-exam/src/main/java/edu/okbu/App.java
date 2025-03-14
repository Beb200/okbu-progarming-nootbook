package edu.okbu;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        RPS myGame = new RPS();
        myGame.initialize("rps.in");
        myGame.play();
        myGame.Winner();
    }
}

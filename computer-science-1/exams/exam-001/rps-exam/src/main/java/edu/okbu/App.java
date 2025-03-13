package edu.okbu;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        RPS myGame = new RPS();
        myGame.initialize("rps.in");
        myGame.play();
    }
}

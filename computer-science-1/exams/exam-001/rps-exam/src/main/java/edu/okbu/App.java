package edu.okbu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RPS myGame = new RPS();
        myGame.initialize("rps.in");
        myGame.play();
    }
}

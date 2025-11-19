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
        Rainfall rf = new Rainfall();

        rf.Main();
        rf.average();
        rf.min();
        rf.max();
        rf.Tostring();
        rf.fileOut();
        rf.getRainFallOnDay();
        
        //rf.Print();
    }
}

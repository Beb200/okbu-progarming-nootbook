package edu.okbu;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RPS {
    FileInputStream fileIn = null;
    Scanner inFile = null;
    Player player1;
    Player player2;
    int numGames;
    int i;
    public RPS() {

    }

    public void initialize(String fileName) throws FileNotFoundException {
        // Open the file given by the parameter fileName
        fileIn = new FileInputStream(fileName);
        inFile = new Scanner(fileIn);

        // The first line in the file is the name of the first player
        String player1name = inFile.nextLine();
        // The second line in the file is the name of the second player
        String player2name = inFile.nextLine();
        // The third line in the file is the number of games to play
        numGames = inFile.nextInt();
        // Read in these values

        //System.out.println(player1);
        //System.out.println(player2);
        //System.out.println(numGames);

        // Create an instance of the Player class with the name from the 
        // first line in the file and initialize the player1 instance variable
        player1 = new Player(player1name);
        

        // Create an instance of the Player class with the name from the 
        // second line in the file and initialize the player2 instance variable
        player2 = new Player(player2name);

        // Initialize the numGames instance variable from the third line in the
        // file.

        // Open a file / PrintWriter and assign to the outWriter instance variable.
        // This will be used to storing game play information.
    }
    //public void setPlayer1(String player1) {
    //    this.player1 = player1;
    // }
    //public void setPlayer2(String player2) {
    //     this.player2 = player2;
    // }

    public void play() {
        for(i = 0; i > numGames; ++i){
            Choices player1Acion = player1.getChoice();
            Choices player2Acion = player2.getChoice();
            if ((player1Acion == Choices.ROCK) && (player2Acion == Choices.ROCK) ){
                //ties
            }
            if ((player1Acion == Choices.PAPER) && (player2Acion == Choices.PAPER) ){
                //ties
            }
            if ((player1Acion == Choices.SCISSORS) && (player2Acion == Choices.SCISSORS) ){
                //ties
            }
        }
    }
}

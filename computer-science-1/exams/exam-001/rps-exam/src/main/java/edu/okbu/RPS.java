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
    String player1name;
    String player2name;
    int player1Wins = 0;
    int player2Wins = 0;
    int ties = 0;
    public RPS() {

    }

    public void initialize(String fileName) throws FileNotFoundException {
        // Open the file given by the parameter fileName
        fileIn = new FileInputStream(fileName);
        inFile = new Scanner(fileIn);

        // The first line in the file is the name of the first player
        player1name = inFile.nextLine();
        // The second line in the file is the name of the second player
        player2name = inFile.nextLine();
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
    public void setPlayer1(Player player1name) {
        this.player1 = player1name;
     }
    public void setPlayer2(Player player2name) {
         this.player2 = player2name;
     }

    public void play() {
        System.out.println("This is Rock, Paper, Scissors");
        System.out.printf("The game is between %s and %s\n", player1name, player2name);
        System.out.printf("It will go for %d rounds\n",numGames);
        System.out.println();
        System.out.println("When promtid:");
        System.out.println("Enter p for Paper");
        System.out.println("Enter r for Rock");
        System.out.println("Enter s for Scissors");
        System.out.println();
        //System.out.print("Type start to begian:");
        for(i = 0; i < numGames; ++i){
            System.out.println();
            System.out.printf("%s turn:", player1name);
            Choices player1Acion = player1.getChoice();
            System.out.printf("%s turn:", player2name);
            Choices player2Acion = player2.getChoice();
            if ((player1Acion == Choices.ROCK) && (player2Acion == Choices.ROCK) ){
                ties += 1;
            }
            if ((player1Acion == Choices.PAPER) && (player2Acion == Choices.PAPER) ){
                ties += 1;
            }
            if ((player1Acion == Choices.SCISSORS) && (player2Acion == Choices.SCISSORS) ){
                ties += 1;
            }



            if ((player1Acion == Choices.ROCK) && (player2Acion == Choices.SCISSORS) ){
                player1Wins += 1;
            }
            if ((player1Acion == Choices.SCISSORS) && (player2Acion == Choices.PAPER) ){
                player1Wins += 1;
            }
            if ((player1Acion == Choices.PAPER) && (player2Acion == Choices.ROCK) ){
                player1Wins += 1;
            }



            if ((player2Acion == Choices.ROCK) && (player1Acion == Choices.SCISSORS) ){
                player2Wins += 1;
            }
            if ((player2Acion == Choices.PAPER) && (player1Acion == Choices.ROCK) ){
                player2Wins += 1;
            }
            if ((player2Acion == Choices.SCISSORS) && (player1Acion == Choices.PAPER) ){
                player2Wins += 1;
            }
        }
    }
    public void Winner(){
        System.out.println();
        if (player1Wins > player2Wins){
            System.out.printf("The winner is %s\n.",player1name);
        }
        else if(player1Wins < player2Wins){
            System.out.printf("The winner is %s\n", player2name);
        }
        else{
            System.out.println("It's a tie.");
        }
        System.out.printf("%s Wins: %d\n",player1name,player1Wins);
        System.out.printf("%s Wins: %d\n",player2name,player2Wins);
        System.out.printf("The Ties: %d\n", ties);
        
    }
}

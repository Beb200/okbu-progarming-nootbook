package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class The_Game {
    
    FileInputStream fileIn = null;
    Scanner inFile = null;
    int numGames;
    //String [][] game;
    String winner; 
    Player player1 = new Player();
    Player player2 = new Player(); 

    int loop = 1;

    int cat = 0;

    Game_Board gb = new Game_Board();

    
    public void initialize(String fileName) throws FileNotFoundException {
        fileIn = new FileInputStream(fileName);
        inFile = new Scanner(fileIn);

        numGames = inFile.nextInt();

        player1.name = inFile.next();
        player2.name = inFile.next();


        //System.out.println(numGames);
    }

    public void play(String[][] game){

        Scanner scnr = new Scanner(System.in);

        System.out.println("This is tic tac toe.");
        System.out.println("Rules are that each player take turns playing a X or an O on a 3x3 bourd.");
        System.out.printf("%s is X.\n", player1.name);
        System.out.printf("%s is O\n", player2.name);
    }
     
    public void player1Turn(String[][] game){
        System.out.printf("%s turn: \n", player1.name);
        System.out.print("Enter the row:");
        int player1Row = player1.choiceRow();
        System.out.print("Enter the colom:");
        int player1Col = player1.choiceCol();
        while (loop == 1) {
            if (game[player1Row][player1Col].equals("#")){
                game[player1Row][player1Col] = "X";
                break;
            }
            else{
                System.out.println("Not vible move.");
                System.out.print("Enter the row:");
                player1Row = player1.choiceRow();
                System.out.print("Enter the colom:");
                player1Col = player1.choiceCol();
            }
        }
    }

        
        //System.out.println(gb.Display());

    public void player2Turn(String[][] game){
        System.out.printf("%s turn: \n", player2.name);
        System.out.print("Enter the row:");
        int player2Row = player2.choiceRow();
        System.out.print("Enter the colom:");
        int player2Col = player2.choiceCol();

        while (loop == 1) {
            if (game[player2Row][player2Col].equals("#")){
                game[player2Row][player2Col] = "O";
                break;
            }
            else{
                System.out.println("Not vible move.");
                System.out.print("Enter the row:");
                player2Row = player2.choiceRow();
                System.out.print("Enter the colom:");
                player2Col = player2.choiceCol();
            }
        }
    }

            //move to player:
            //int player1Row = scnr.nextInt();
            //int player1Col = scnr.nextInt();

            //int player2Row = scnr.nextInt();
            //int player2Col = scnr.nextInt();


        
       
    public void winner(String[][] game){
            //checks 'x' rows
            if ((game[0][0].equals("X")) && (game[0][1].equals("X") && (game[0][2].equals("X")))){
                winner = "X";
                player1.win();
            }
            else if ((game[1][0].equals("X")) && (game[1][1].equals("X") && (game[1][2].equals("X")))){
                winner = "X";
                player1.win();
            }
            else if ((game[2][0].equals("X")) && (game[2][1].equals("X") && (game[2][2].equals("X")))){
                winner = "X";
                player1.win();
            }
            //checks 'x' col
            else if ((game[0][0].equals("X")) && (game[1][0].equals("X") && (game[2][0].equals("X")))){
                winner = "X";
                player1.win();
            }
            else if ((game[0][1].equals("X")) && (game[1][1].equals("X") && (game[2][1].equals("X")))){
                winner = "X";
                player1.win();
            }
            else if ((game[0][2].equals("X")) && (game[1][2].equals("X") && (game[2][2].equals("X")))){
                winner = "X";
                player1.win();
            }
            //checks 'x' dign
            else if ((game[0][0].equals("X")) && (game[1][1].equals("X") && (game[2][2].equals("X")))){
                winner = "X";
                player1.win();
            }
            else if ((game[0][2].equals("X")) && (game[1][1].equals("X") && (game[2][0].equals("X")))){
                winner = "X";
                player1.win();
            }

            //checks 'o' rows
            else if ((game[0][0].equals("O")) && (game[0][1].equals("O") && (game[0][2].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if ((game[1][0].equals("O")) && (game[1][1].equals("O") && (game[1][2].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if ((game[2][0].equals("O")) && (game[2][1].equals("O") && (game[2][2].equals("O")))){
                winner = "O";
                player2.win();
            }
            //checks 'o' col
            else if ((game[0][0].equals("O")) && (game[1][0].equals("O") && (game[2][0].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if ((game[0][1].equals("O")) && (game[1][1].equals("O") && (game[2][1].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if ((game[0][2].equals("O")) && (game[1][2].equals("O") && (game[2][2].equals("O")))){
                winner = "O";
                player2.win();
            }
            //checks 'O' dign
            else if ((game[0][0].equals("O")) && (game[1][1].equals("O") && (game[2][2].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if ((game[0][2].equals("O")) && (game[1][1].equals("O") && (game[2][0].equals("O")))){
                winner = "O";
                player2.win();
            }
            else if (!game[0][0].equals("#") && !game[0][1].equals("#") && !game[0][2].equals("#") && !game[1][0].equals("#") && !game[1][1].equals("#") && !game[1][2].equals("#") && !game[2][0].equals("#") && !game[2][1].equals("#") && !game[2][2].equals("#")){
                cat = cat + 1;
                winner = "Cat";
            }
            else{
                winner = "no winner";
            }
        }
    }

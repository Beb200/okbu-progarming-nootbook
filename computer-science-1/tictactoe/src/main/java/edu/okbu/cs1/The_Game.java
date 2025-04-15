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

       
            System.out.printf("%s turn: \n", player1.name);
            System.out.println("Enter the row:");
            int player1Row = player1.choiceRow();
            System.out.println("Enter the colom:");
            int player1Col = player1.choiceCol();

            game[player1Row][player1Col] = "X";
            System.out.println(gb.Display());

            System.out.printf("%s trun: \n", player2.name);
            System.out.println("Enter the row:");
            int player2Row = player2.choiceRow();
            System.out.println("Enter the colom:");
            int player2Col = player2.choiceCol();

            //move to player:
            //int player1Row = scnr.nextInt();
            //int player1Col = scnr.nextInt();

            //int player2Row = scnr.nextInt();
            //int player2Col = scnr.nextInt();


        }
       
    public void winner(String[][] game){
            //checks 'x' rows
            if ((game[0][0].equals("x")) && (game[0][1].equals("x") && (game[0][2].equals("x")))){
                winner = "x";
            }
            else if ((game[1][0].equals("x")) && (game[1][1].equals("x") && (game[1][2].equals("x")))){
                winner = "x";
            }
            else if ((game[2][0].equals("x")) && (game[2][1].equals("x") && (game[2][2].equals("x")))){
                winner = "x";
            }
            //checks 'x' col
            else if ((game[0][0].equals("x")) && (game[1][0].equals("x") && (game[2][0].equals("x")))){
                winner = "x";
            }
            else if ((game[0][1].equals("x")) && (game[1][1].equals("x") && (game[2][1].equals("x")))){
                winner = "x";
            }
            else if ((game[0][2].equals("x")) && (game[1][2].equals("x") && (game[2][2].equals("x")))){
                winner = "x";
            }
            //checks 'x' dign
            else if ((game[0][0].equals("x")) && (game[1][1].equals("x") && (game[2][2].equals("x")))){
                winner = "x";
            }
            else if ((game[0][2].equals("x")) && (game[1][1].equals("x") && (game[2][0].equals("x")))){
                winner = "x";
            }

            //checks 'o' rows
            else if ((game[0][0].equals("o")) && (game[0][1].equals("o") && (game[0][2].equals("o")))){
                winner = "o";
            }
            else if ((game[1][0].equals("o")) && (game[1][1].equals("o") && (game[1][2].equals("o")))){
                winner = "o";
            }
            else if ((game[2][0].equals("o")) && (game[2][1].equals("o") && (game[2][2].equals("o")))){
                winner = "o";
            }
            //checks 'o' col
            else if ((game[0][0].equals("o")) && (game[1][0].equals("o") && (game[2][0].equals("o")))){
                winner = "o";
            }
            else if ((game[0][1].equals("o")) && (game[1][1].equals("o") && (game[2][1].equals("o")))){
                winner = "o";
            }
            else if ((game[0][2].equals("o")) && (game[1][2].equals("o") && (game[2][2].equals("o")))){
                winner = "o";
            }
            //checks 'x' dign
            else if ((game[0][0].equals("o")) && (game[1][1].equals("o") && (game[2][2].equals("o")))){
                winner = "o";
            }
            else if ((game[0][2].equals("o")) && (game[1][1].equals("o") && (game[2][0].equals("o")))){
                winner = "o";
            }
            else{
                winner = "no winner";
            }
        }
    }

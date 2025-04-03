package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class The_Game {
    
    FileInputStream fileIn = null;
    Scanner inFile = null;
    int numGames;
    String [][] game;
    String winner;  
    
    public void initialize(String fileName) throws FileNotFoundException {
        fileIn = new FileInputStream(fileName);
        inFile = new Scanner(fileIn);

        numGames = inFile.nextInt();

        //String name1 = inFile.nextLine();
        //String name2 = inFile.nextLine();


        //System.out.println(numGames);
    }

    public void play(){
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
        }
    }

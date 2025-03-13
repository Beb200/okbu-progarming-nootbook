package edu.okbu.cs1;

import java.util.Scanner;

public class RPS{
    int p1wins;
    int p2wins;
    int ties;
    String p1Play;
    String p2Play;
    char n;
    int i;
    char r;
    char p;
    char s;

    public RPS(int n){

   }

    public void play(int n){
        try (Scanner p = new Scanner(System.in)) {
            System.out.println("RPS Game");
            System.out.println("rules:");
            System.out.println("when prompten enter r- rock, s-siser, p-paper");
            for (i=0; i < n; ++i){
                System.out.print("Player 1: ");
                String player1val = p.next();//mabye chr or int
                System.out.print("Player 2: ");
                String player2val = p.next();
                //get player val 

                if ((player1val.equals("r")) && (player2val.equals("r"))){//output: Tie 
                    ties += 1;
                }
                else if ((player1val.equals("p")) && (player1val.equals("p"))){
                    ties += 1;
                }
                else if ((player1val.equals("s")) && (player1val.equals("s"))){
                    ties += 1;
                }
                else if ((player1val.equals("r")) && (player1val.equals("s"))){//output: player1 win
                    p1wins += 1;
                }
                else if ((player1val.equals("p")) && (player1val.equals("r"))){
                    p1wins += 1;
                }
                else if((player1val.equals("s")) && (player1val.equals("p"))){
                    p1wins += 1;
                }
                else if((player1val.equals("p")) && (player1val.equals("r"))){//output: player2 win
                    p2wins += 1;
                }
                else if((player1val.equals("r")) && (player1val.equals("s"))){
                    p2wins += 1;
                }
                else if((player1val.equals("p")) && (player1val.equals("s"))){
                    p2wins += 1;
                }


            }
        }
    }

   // public String getWinner(String p1Play, String p2Play){

   // }

   // public void updateScores(String getWinner){

    //}

    public void printScores(){
        System.out.println("Player 1: " + p1wins);
        System.out.println("Palyer 2: " + p2wins);
        System.out.println("Ties: " + ties);
    }

    public static void main(String[] args) {

        RPS myGame = new RPS(10);

        myGame.play(3);

       myGame.printScores();

    }

}
package edu.okbu.cs1;

public class RPS(){
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

    public void play(int n, int i, int p1wins, int p2wins,int ties, char r, char p, char s){
        for (i=0; i < n; ++i){
            char player1val; //mabye chr or int
            char player2val;
            //get player val

            if ((player1val == r) && (player2val == r)){//output: Tie 
                ties += 1;
            }
            else if ((player1val == p) && (player2val == p)){
                ties += 1;
            }
            else if ((player1val == s) && (player2val == s)){
                ties += 1;
            }
            else if ((player1val == r) && (player2val == s)){//output: player1 win
                p1wins += 1;
            }
            else if ((player1val == p) && (player2val == r)){
                p1wins += 1;
            }
            else if((player1val == s) && (player2val == p)){
                p1wins += 1;
            }
            else if((player1val == s) && (player2val == r)){//output: player2 win
                p2wins += 1;
            }
            else if((player1val == r) && (player2val == s)){
                p2wins += 1;
            }
            else if((player1val == p) && (player2val == s)){
                p2wins += 1;
            }


        }
    }

    public String getWinner(String p1Play, String p2Play){

    }

    public void updateScores(String getWinner){

    }

    public void printScores(int p1wins, int p2wins, int ties){
        System.out.println("Player 1: " + p1wins);
        System.out.println("Palyer 2: " + p2wins);
        System.out.println("Ties: " + ties);
    }

    public static void main(String[] args) {

        RPS myGame = new RPS(10);

        myGame.play();

        myGame.printScores();

    }

}
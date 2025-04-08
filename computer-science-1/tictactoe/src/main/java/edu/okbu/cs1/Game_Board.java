package edu.okbu.cs1;

public class Game_Board {
    int i;
    int j;
    String [][] game = new String[3][3];
    
    public void board(){
        for (i = 0; i < game.length; i++){
            for (j = 0; j < game[i].length; j++){
               game[i][j] = "#";
               
            }
        }
        //return game[i][j];
    }

    public String Display(){
        return (game[0][0] + "|" + game[0][1] + "|" + game[0][2] + "\n" + "-----\n" + game[1][0] + "|" + game[1][1] + "|" + game[1][2] +"\n" +"-----\n"+ game[2][0] +"|" + game[2][1] + "|" + game[2][2] +"\n");
    }

    
}

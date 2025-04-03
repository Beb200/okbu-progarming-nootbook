package edu.okbu.cs1;

public class Game_Board {
    int i;
    int j;
    String [][] game;
    
    public String Display(){
        return ("#|#|#\n" + "-----\n" + "#|#|#\n" +"-----\n" +"#|#|#\n");
    }

    public String board(){
        for (i = 0; i < game.length; i++){
            for (j = 0; j < game[i].length; j++){
               game[i][j] = "#";
               
            }
        }
        return game[i][j];
    }
}

package edu.okbu.cs1;
import java.util.Random;

public class Computer_Player {
    int ran_row;
    int ran_col;


    public void random(int x, int y){
        Random random = new Random();

        ran_row = random.nextInt(x-1);
        ran_col = random.nextInt(y-1);
        
    }

    public void com_player(int ran_row, int ran_col, String player[][], String hidden[][]){
        if (player[ran_row][ran_col].equals("#")){
            if (hidden[ran_row][ran_col].equals("#")){
                hidden[ran_row][ran_col] = "M";
                player[ran_row][ran_col] = "M";
            }
        }
        else if ()
    }
}

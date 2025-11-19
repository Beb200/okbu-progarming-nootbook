package edu.okbu.cs1;
import java.util.Random;

public class Computer_Player {
    int ran_row;
    int ran_col;
    boolean hit = false;
    int leftSidePoint_row;
    int leftSidePoint_col;
    int topPoint_row;
    int topPoint_col;
    int rightSidePoint_row;
    int rightSidePoint_col;
    int bottemSide_row;
    int bottemSide_col;


    public void random(int x, int y){
        Random random = new Random();

        ran_row = random.nextInt(x-1);
        ran_col = random.nextInt(y-1);

        leftSidePoint_row = ran_row;
        leftSidePoint_col = ran_col - 1;

        topPoint_row = ran_row + 1;
        topPoint_col = ran_col;


        
        
    }

    public void com_player(int ran_row, int ran_col, String player[][], String hidden[][]){
        if (player[ran_row][ran_col].equals("#") && hit == false){
            if (hidden[ran_row][ran_col].equals("#")){
                hidden[ran_row][ran_col] = "M";
                player[ran_row][ran_col] = "M";
                hit = false;
            }
            else {
                hidden[ran_row][ran_col] = "H";
                player[ran_row][ran_col] = "H";
                hit = true;
            }
        }
        else if(hit == true){
            if (player[ran_row][ran_col - 1].equals("#")){
                
            }
        }
    }
}

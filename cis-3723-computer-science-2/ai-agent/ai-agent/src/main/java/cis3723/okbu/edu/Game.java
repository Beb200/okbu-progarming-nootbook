package cis3723.okbu.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game{

    public void human_turn(GameState state, Move move1, Game game){
        Scanner scanner = new Scanner(System.in);
        
        
        //System.out.println("hi test");
        move1.player = 1;
        
        boolean reset = false;
        //while (reset == false) {
            //System.out.println("hi test2");
            //System.out.println(move1.player);
            if (move1.player == 1){
                System.out.print("Player 1 what is your move:");
            }
            else if (move1.player == 2){
                System.out.print("Player 2 what is your move:");
            }
            //System.out.println("test 2");
            int the_move = 0;
            
           try{
                the_move = scanner.nextInt();
                System.out.println(the_move);
                //scanner.close();
            } catch(Exception e){
                System.out.println("not a int try agin");
                //reset = false;
            }
            //System.out.println("test 3");
            move1.colomn = the_move-1;
            /* 
            if (the_move < 1 && the_move > 7 ){
                System.out.print("not the right move(1,2,3,4,5,6,7) try agin");
                reset = false;
            }
            else{
                reset = true;
            }
            System.out.print(reset);
            */
        //}
        state = state.move(state, move1);
        
        state.display(state);
        /* to switch if two player
        if (move1.player == 1){
            move1.player = 2;

        }
        else if (move1.player == 2){
            move1.player = 1;
        }
        */
        System.out.println(move1.player);
    
    
    }

    public void ai_turn(GameState state,Move move,Game game){
        move.player = 2;
        Random random = new Random();
        ArrayList<Move> moves = state.get_legal_moves(state);
        int random_index = random.nextInt(moves.size());
        int random_move = moves.get(random_index).colomn;

        move.colomn = random_move;
        state = state.move(state, move);

        
        state.display(state);
        //System.out.println(move.player);
    }

    public static void main(String[] args){
        GameState state = new GameState();
        Game game = new Game();
        Move move1 = new  Move();
        move1.player = 2;
        state.display(state);

        while (state.is_terminal(state) == false){
            game.ai_turn(state, move1, game);
            game.human_turn(state,move1,game);
        }
        String winner = state.check_winner(state);
        System.out.println(winner);
    }
    
    
    
    
    
    
    
    //<S, M> implements Interface<S, M> {
    /* 
    public int turn;
    //GameState gameState = new state();

    @Override
    public S get_legal_moves(S state){
        return state;
    }

    @Override
    public S apply_move(S state, M move){
        return state;
    }

    @Override
    public boolean is_terminal(S state){
        return false;
    }

    @Override
    public int get_result(S state){
        return 0;
    }

    @Override
    public String get_current_player(int turn){
    //    return get_current_player();
        if (turn == 1) {
            return "player1";
        }
        if (turn == 2){
            return "player2";
        }
        return "unknown";

    }

    @Override
    public void display(S[][] state){
        for (i= 0; i < state.length; i++){
            for (j = 0; j < state[i].length; j++){
                System.out.print(state[i][j]+ " ");
            }

        }
       //System.out.print(Arrays.deepToString(state));
        }
       */
    }

package cis3723.okbu.edu;

import java.util.ArrayList;

public class GameState implements Interface<GameState, Move>{
    
        int[][] board = {
        {0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0}
        };

        ArrayList<Move> legal_moves;
        int curent_winner= 0;
        int curent_player = 1;

        public GameState move(GameState S, Move M){
            for(int i= 0; i < board.length; i++){
                if (board[i][M.colomn] == 0){
                    board[i][M.colomn]= M.player;
                    if (M.player == 1){
                        curent_player = 2;
                        break;
                    }
                    if (M.player == 2){
                        curent_player = 1;
                        break;
                    }
                }
            }
            return S;
        }

        public void display(GameState S){
            for (int i =board.length -1 ; i >= 0; i--){
                for (int j= 0; j< board[i].length; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");
        }
        public static void main(String[] args){
            GameState game= new GameState();
            game.display(game);
            Move move1 = new  Move();
            move1.colomn = 1;
            move1.player = 1;
            game.move(game, move1);
            game.display(game);
            move1.player = 2;
            game.move(game, move1);
            game.display(game);
        }

        @Override
        public ArrayList<Move> get_legal_moves(GameState state) {
            legal_moves = new ArrayList<Move>();
            for(int i = 0; i < state.board[0].length; i++){
                if (state.board[0][i] == 0){
                    Move move = new Move();
                    move.colomn = i;
                    move.player = state.curent_player;
                    legal_moves.add(move);
                }
            }
            return legal_moves;
        }

        @Override
        public boolean is_terminal(GameState state) {
            boolean terminal = false;
            curent_winner = 0;
            int move_to_check;
            //this is the diagonal down-right
            for (int i =board.length -1 ; i >= 0; i--){
                for (int j= 0; j< board[i].length; j++){
                    if ((board[i][j] == 1) || (board[i][j]== 2)){
                        move_to_check = board[i][j];
                    }
                    else{
                        break;
                    }
                    
                    if((i-1 >= 0) && (j+1 < board[i].length) && (board[i-1][j+1] != move_to_check)){
                        break;
                    }
                    if((i-2 >= 0) && (j+2 < board[i].length) && (board[i-2][j+2] != move_to_check)){
                        break;
                    }
                    if((i-3 >= 0) && (j+3 < board[i].length) && (board[i-3][j+3] != move_to_check)){
                        break;
                    }
                    curent_winner = move_to_check;
                    return true;
                }
            }
            //this is the diagonal up-right
            for (int i =board.length -1 ; i >= 0; i--){
                for (int j= 0; j< board[i].length; j++){
                    if ((board[i][j] == 1) || (board[i][j]== 2)){
                        move_to_check = board[i][j];
                    }
                    else{
                        break;
                    }
                    
                    if((i+1 >= 0) && (j+1 < board[i].length) && (board[i+1][j+1] != move_to_check)){
                        break;
                    }
                    if((i+2 >= 0) && (j+2 < board[i].length) && (board[i+2][j+2] != move_to_check)){
                        break;
                    }
                    if((i+3 >= 0) && (j+3 < board[i].length) && (board[i+3][j+3] != move_to_check)){
                        break;
                    }
                    curent_winner = move_to_check;
                    return true;
                }
            }
            //this is the horizantal right
            for (int i =board.length -1 ; i >= 0; i--){
                for (int j= 0; j< board[i].length; j++){
                    if ((board[i][j] == 1) || (board[i][j]== 2)){
                        move_to_check = board[i][j];
                    }
                    else{
                        break;
                    }
                    
                    if((i >= 0) && (j+1 < board[i].length) && (board[i][j+1] != move_to_check)){
                        break;
                    }
                    if((i >= 0) && (j+2 < board[i].length) && (board[i][j+2] != move_to_check)){
                        break;
                    }
                    if((i >= 0) && (j+3 < board[i].length) && (board[i][j+3] != move_to_check)){
                        break;
                    }
                    curent_winner = move_to_check;
                    return true;
                }
            }
            //this is the vertical up
            for (int i =board.length -1 ; i >= 0; i--){
                for (int j= 0; j< board[i].length; j++){
                    if ((board[i][j] == 1) || (board[i][j]== 2)){
                        move_to_check = board[i][j];
                    }
                    else{
                        break;
                    }
                    
                    if((i+1 >= 0) && (j < board[i].length) && (board[i+1][j] != move_to_check)){
                        break;
                    }
                    if((i+2 >= 0) && (j < board[i].length) && (board[i+2][j] != move_to_check)){
                        break;
                    }
                    if((i+3 >= 0) && (j < board[i].length) && (board[i+3][j] != move_to_check)){
                        break;
                    }
                    curent_winner = move_to_check;
                    return true;
                }
            }
            if (get_legal_moves(state) == null){
                return true;
            }

            return terminal;
        }

        public String check_winner(GameState state){
            if (curent_winner == 1){
                return "Player 1 Wins";
            }
            else if (curent_winner == 2){
                return "Player 2 Wins";
            }
            else {
                return "No Winner";
            }
        }

        @Override
        public int get_result(GameState state) {
            if (state.curent_winner == 0){
                return 0;
            }
            else if(state.get_current_player(state) == curent_winner){
                return 1;
            }
            else {
                return -1;
            }
        }

        @Override
        public int get_current_player(GameState state) {
            return state.curent_player;
        }
    
}

package cis3723.okbu.edu;

public class Game<S, M> implements Interface {
    int turn;
     
    public S get_legal_moves(S state){
        return state;
    }

    public S apply_move(S state, M move){

    }

    public boolean is_termianl(S state){

    }

    public int get_result(S state){

    }

    public S get_current_player(S state){
        if turn == 1 {
            return "player1";
        }
        if turn == 2{
            return "palyer2";
        }

    }
    public String display(S state){

    }
}

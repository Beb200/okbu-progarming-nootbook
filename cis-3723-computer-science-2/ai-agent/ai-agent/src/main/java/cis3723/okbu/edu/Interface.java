package cis3723.okbu.edu;

public interface Interface<S, M> {
    S get_leagal_moves(S state);
    S apply_move(S state,M  move);
    boolean is_terminal(S state);
    int get_result(S state);
    S get_current_player(S state);
    String dispaly(S state);
    
} 
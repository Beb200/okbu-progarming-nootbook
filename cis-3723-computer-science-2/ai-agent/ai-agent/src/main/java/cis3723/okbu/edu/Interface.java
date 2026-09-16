package cis3723.okbu.edu;

public interface Interface<S, M> {
    S get_legal_moves(S state);
    S apply_move(S state, M move);
    boolean is_terminal(S state);
    int get_result(S state);
    String get_current_player(int turn);
    String display(S state);
    
} 
package cis3723.okbu.edu;

public interface Interface<S, M> {
    S get_legal_moves(S state);
    S move(S state, M move);
    boolean is_terminal(S state);
    int get_result(S state);
    int get_current_player(S state);
    void display(S state);
    
} 
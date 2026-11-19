package cis3723.okbu.edu;

import java.util.ArrayList;

public interface Interface<S, M> {
    ArrayList<M> get_legal_moves(S state);
    S move(S state, M move);
    boolean is_terminal(S state);
    int get_result(S state);
    int get_current_player(S state);
    void display(S state);
    
} 
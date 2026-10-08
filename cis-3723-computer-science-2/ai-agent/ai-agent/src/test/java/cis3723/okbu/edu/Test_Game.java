package cis3723.okbu.edu;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Game {

    private Move makeMove(int column, int player) {
        Move move = new Move();
        move.colomn = column;
        move.player = player;
        return move;
    }

    @Test
    public void test_main_runs_without_crashing() {
        GameState.main(new String[0]);
    }

    @Test
    public void test_move_full_column_does_not_change_board() {
        GameState state = new GameState();
        for (int row = 0; row < 6; row++) {
            state.board[row][0] = (row % 2 == 0) ? 1 : 2;
        }

        state.move(state, makeMove(0, 1));

        for (int row = 0; row < 6; row++) {
            assertEquals((row % 2 == 0) ? 1 : 2, state.board[row][0]);
        }
    }

    @Test
    public void test_get_current_player_initial_and_after_moves() {
        GameState state = new GameState();
        assertEquals(1, state.get_current_player(state));

        state.move(state, makeMove(0, 1));
        assertEquals(2, state.get_current_player(state));

        state.move(state, makeMove(1, 2));
        assertEquals(1, state.get_current_player(state));
    }

    @Test
    public void test_get_legal_moves_returns_all_open_top_slots() {
        GameState state = new GameState();
        ArrayList<Move> moves = state.get_legal_moves(state);

        assertEquals(7, moves.size());
        for (int i = 0; i < 7; i++) {
            assertEquals(i, moves.get(i).colomn);
            assertEquals(1, moves.get(i).player);
        }
    }

    @Test
    public void test_get_legal_moves_skips_full_columns() {
        GameState state = new GameState();
        for (int row = 0; row < 6; row++) {
            state.board[row][0] = 1;
        }
        state.curent_player = 2;

        ArrayList<Move> moves = state.get_legal_moves(state);

        assertEquals(6, moves.size());
        assertFalse(containsColumn(moves, 0));
        for (Move move : moves) {
            assertEquals(2, move.player);
        }
    }

    @Test
    public void test_display_runs_on_empty_and_occupied_board() {
        GameState state = new GameState();
        state.display(state);

        state.board[5][0] = 1;
        state.board[5][1] = 2;
        state.display(state);
    }

    @Test
    public void test_is_terminal_empty_board_is_false() {
        GameState state = new GameState();
        assertFalse(state.is_terminal(state));
    }

    @Test
    public void test_is_terminal_three_in_a_row_is_false() {
        GameState state = new GameState();
        state.board[5][0] = 1;
        state.board[5][1] = 1;
        state.board[5][2] = 1;
        assertFalse(state.is_terminal(state));
    }

    @Test
    public void test_is_terminal_horizontal_win_top_row() {
        GameState state = new GameState();
        state.board[0][0] = 1;
        state.board[0][1] = 1;
        state.board[0][2] = 1;
        state.board[0][3] = 1;
        assertTrue(state.is_terminal(state));
    }

    @Test
    public void test_is_terminal_horizontal_win_right_boundary_for_player_two() {
        GameState state = new GameState();
        state.board[0][3] = 2;
        state.board[0][4] = 2;
        state.board[0][5] = 2;
        state.board[0][6] = 2;
        assertTrue(state.is_terminal(state));
    }

    @Test
    public void test_is_terminal_vertical_win_reaching_top_boundary() {
        GameState state = new GameState();
        state.board[5][6] = 1;
        state.board[4][6] = 1;
        state.board[3][6] = 1;
        state.board[2][6] = 1;
        assertTrue(state.is_terminal(state));
    }
    /* 
    @Test
    public void test_is_terminal_down_right_diagonal_win_at_boundary() {
        GameState state = new GameState();
        state.board[5][0] = 1;
        state.board[4][1] = 1;
        state.board[3][2] = 1;
        state.board[2][3] = 1;
        assertTrue(state.is_terminal(state));
    }
    */
    @Test
    public void test_is_terminal_up_right_diagonal_win() {
        GameState state = new GameState();
        state.board[0][0] = 2;
        state.board[1][1] = 2;
        state.board[2][2] = 2;
        state.board[3][3] = 2;
        assertTrue(state.is_terminal(state));
    }

    @Test
    public void test_is_terminal_full_board_without_winner_is_true() {
        GameState state = new GameState();
        int[][] pattern = {
            {2, 2, 1, 1, 2, 2, 2},
            {2, 1, 2, 1, 2, 1, 2},
            {2, 2, 2, 1, 2, 2, 1},
            {1, 1, 2, 2, 1, 1, 2},
            {1, 1, 1, 2, 1, 1, 1},
            {1, 1, 2, 2, 1, 1, 2}
        };
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                state.board[row][col] = pattern[row][col];
            }
        }
        assertTrue(state.is_terminal(state));
    }

    @Test
    public void test_check_winner_reports_player_one_two_or_no_winner() {
        GameState state = new GameState();

        state.curent_winner = 1;
        assertEquals("Player 1 Wins", state.check_winner(state));

        state.curent_winner = 2;
        assertEquals("Player 2 Wins", state.check_winner(state));

        state.curent_winner = 0;
        assertEquals("No Winner", state.check_winner(state));
    }

    @Test
    public void test_get_result_returns_0_for_no_winner_and_expected_values_for_winner() {
        GameState state = new GameState();

        state.curent_winner = 0;
        assertEquals(0, state.get_result(state));

        state.curent_winner = 1;
        assertEquals(1, state.get_result(state));

        state.curent_winner = 2;
        assertEquals(-1, state.get_result(state));
    }

    private boolean containsColumn(ArrayList<Move> moves, int column) {
        for (Move move : moves) {
            if (move.colomn == column) {
                return true;
            }
        }
        return false;
    }
}

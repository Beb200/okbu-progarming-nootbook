package cis3723.okbu.edu;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test_Game {
    Game game = new Game();
    //GameState gameState = new GameState<>();

    @Test 
    public void test_get_current_player(){
        //assertEquals("player1", game.get_current_player(1));
        //assertEquals("player2", game.get_current_player(2));
    }

    @Test 
    public void test_disply(){
        //assertEquals("0", game.display(gameState()));
    }

    @Test
    public void test_horizontalWinAtTopBoundary() {
        GameState state = new GameState();
        for (int col = 0; col < 4; col++) {
            state.board[5][col] = 1;
        }
        assertTrue(state.is_terminal(state));
        assertEquals("Player 1 Wins", state.check_winner(state));
    }

    @Test
    public void test_horizontalWinAtRightBoundaryForPlayerTwo() {
        GameState state = new GameState();
        for (int col = 3; col < 7; col++) {
            state.board[0][col] = 2;
        }
        assertTrue(state.is_terminal(state));
        assertEquals("Player 2 Wins", state.check_winner(state));
    }

    @Test
    public void test_verticalWinReachingTopBoundary() {
        GameState state = new GameState();
        for (int row = 2; row < 6; row++) {
            state.board[row][6] = 1;
        }
        assertTrue(state.is_terminal(state));
        assertEquals("Player 1 Wins", state.check_winner(state));
    }

    @Test
    public void testDownRightDiagonalWinAtBoundary() {
        GameState state = new GameState();
        for (int offset = 0; offset < 4; offset++) {
            state.board[5 - offset][offset] = 1;
        }
        assertTrue(state.is_terminal(state));
        assertEquals("Player 1 Wins", state.check_winner(state));
    }

    @Test
    public void testUpRightDiagonalWinAtBoundary() {
        GameState state = new GameState();
        for (int offset = 0; offset < 4; offset++) {
            state.board[2 + offset][offset] = 2;
        }
        assertTrue(state.is_terminal(state));
        assertEquals("Player 2 Wins", state.check_winner(state));
    }

    @Test
    public void testThreeInARowIsNotTerminal() {
        GameState state = new GameState();
        state.board[0][0] = 1;
        state.board[0][1] = 1;
        state.board[0][2] = 1;
        assertFalse(state.is_terminal(state));
    }

    @Test
    public void testPiecesAcrossHorizontalBoundaryAreNotAWin() {
        GameState state = new GameState();
        state.board[0][0] = 1;
        state.board[0][1] = 1;
        state.board[0][5] = 1;
        state.board[0][6] = 1;
        assertFalse(state.is_terminal(state));
    }

    @Test
    public void testFullBoardWithoutWinnerIsDraw() {
        GameState state = new GameState();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                state.board[row][col] = ((col / 2 + row) % 2) + 1;
            }
        }
        assertFalse(hasFourInARow(state.board));
        assertTrue(state.is_terminal(state));
        assertEquals("No Winner", state.check_winner(state));
    }

    private boolean hasFourInARow(int[][] board) {
        int[] rowDirections = {0, 1, 1, -1};
        int[] colDirections = {1, 0, 1, 1};
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int player = board[row][col];
                if (player == 0) {
                    continue;
                }
                for (int direction = 0; direction < rowDirections.length; direction++) {
                    int endRow = row + 3 * rowDirections[direction];
                    int endCol = col + 3 * colDirections[direction];
                    if (endRow < 0 || endRow >= board.length || endCol < 0 || endCol >= board[row].length) {
                        continue;
                    }
                    boolean match = true;
                    for (int offset = 1; offset < 4; offset++) {
                        if (board[row + offset * rowDirections[direction]][col + offset * colDirections[direction]] != player) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}

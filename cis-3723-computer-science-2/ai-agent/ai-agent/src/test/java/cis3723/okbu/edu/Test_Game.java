package cis3723.okbu.edu;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test_Game {
    Game game = new Game();

    @Test 
    public void test_get_current_player(){
        assertEquals("player1", game.get_current_player(1));
        assertEquals("player2", game.get_current_player(2));
    }
}

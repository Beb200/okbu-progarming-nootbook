package edu.okbu.cs1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test_HS_001(){
        HailstoneSequence h = new HailstoneSequence(25);
        assertEquals(h.computeHailstoneSequence(),1);
    }
}

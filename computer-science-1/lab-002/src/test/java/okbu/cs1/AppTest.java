package okbu.cs1;

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
    public void test_001(){
        HotelReservation r = new HotelReservation();
        r.setHotelRate(50);
        assertEquals(r.getHotelRate(),50.0,0.01);
    }

    @Test
    public void test_002(){
        HotelReservation r = new HotelReservation();
        r.setNumYears(30);
        assertEquals(r.getNumYears(),30.0,0.01);
    }

    @Test 
    public void test_003(){
        HotelReservation r = new HotelReservation();
        r.setNumYears(50);
        assertEquals(r.getHotelRate(), 75.0,0.01);
    }

    @Test
    public void test_A_001(){
        AnniversaryMessage a = new AnniversaryMessage(50);
        assertEquals(a.Message(),"Your golden anniversary -- amazing.");
    }

    @Test
    public void test_I_001(){
        Insurance i = new Insurance(24, 0);
        assertEquals(i.getPrice(),4800,0.01);
    }

    @Test
    public void test_I_002(){
        Insurance i = new Insurance(26, 0);
        assertEquals(i.getPrice(),2350,0.01);
    }

    @Test 
    public void test_i_003(){
        Insurance i = new Insurance(41, 0);
        assertEquals(i.getPrice(),2100,0.01);
    }
}

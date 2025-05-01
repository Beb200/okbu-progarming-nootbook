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

    @Test
    public void test_TV_001(){
        CableTVchannels tv = new CableTVchannels(5, 0);
        assertEquals(tv.getChannelType(), 1);
    }

    @Test
    public void test_TV_002(){
        CableTVchannels tv = new CableTVchannels(1100, 0);
        assertEquals(tv.getChannelType(),2);
    }

    @Test
    public void test_TV_003(){
        CableTVchannels tv = new CableTVchannels(600, 0);
        assertEquals(tv.getChannelType(),3);
    }

    @Test
    public void test_MOVIE_001(){
        MovieTicketPrice m = new MovieTicketPrice(10, 0);
        assertEquals(m.getMovieTicketPrice(),11);
    }

    @Test
    public void test_MOVIE_002(){
        MovieTicketPrice m = new MovieTicketPrice(19, 0);
        assertEquals(m.getMovieTicketPrice(),14);
    }

    @Test
    public void test_MOVIE_003(){
        MovieTicketPrice m = new MovieTicketPrice(67, 0);
        assertEquals(m.getMovieTicketPrice(),12);
    }

    @Test
    public void test_T_001(){
        Toll t = new Toll(5,00,0,1);
        assertEquals(t.getTollAmount(),1.55, 0.01);
    }

    @Test
    public void test_T_002(){
        Toll t = new Toll(8,00,0,1);
        assertEquals(t.getTollAmount(),4.65, 0.01);
    }

    @Test
    public void test_T_003(){
        Toll t = new Toll(12,00,0,1);
        assertEquals(t.getTollAmount(),2.35, 0.01);
    }

    @Test
    public void test_T_004(){
        Toll t = new Toll(19,00,0,1);
        assertEquals(t.getTollAmount(),1.55, 0.01);
    }

    @Test
    public void test_T_005(){
        Toll t = new Toll(6,00,1,1);
        assertEquals(t.getTollAmount(),1.55, 0.01);
    }

    @Test
    public void test_T_006(){
        Toll t = new Toll(11,00,1,1);
        assertEquals(t.getTollAmount(),3.05, 0.01);
    }

    @Test
    public void test_T_007(){
        Toll t = new Toll(15,00,1,1);
        assertEquals(t.getTollAmount(),3.45, 0.01);
    }

    @Test
    public void test_T_008(){
        Toll t = new Toll(18,00,1,1);
        assertEquals(t.getTollAmount(),3.60, 0.01);
    }

    @Test
    public void test_T_009(){
        Toll t = new Toll(21,00,1,1);
        assertEquals(t.getTollAmount(),3.05, 0.01);
    }

    @Test
    public void test_T_010(){
        Toll t = new Toll(23,00,1,1);
        assertEquals(t.getTollAmount(),1.55, 0.01);
    }

    @Test
    public viod test_y_001(){
        Year y = new Year(1600);
        assertEquals(y.theYear(),1);
    }

    @Test
    public viod test_y_002(){
        Year y = new Year(1712);
        assertEquals(y.theYear(),1);
    }
}

package edu.okbu.cs1;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.okbu.HealthData;
import edu.okbu.Lab2_25;
import edu.okbu.Lab2_27;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void test001()
    {
        C1 c1 = new C1(1,2,3);
        assertEquals(3, c1.add2());
    }

    @Test
    public void test_002(){
        C1 c1 = new C1(1,2,3);
        assertEquals(6, c1.add3());
    }

    @Test
    public void test_003(){
        C1 c1 = new C1(1,2,3);
        assertEquals((-1), c1.sup1());
    }

    @Test
    public void test_004(){
        C1 c1 = new C1(1,2,3);
        assertEquals(c1.add(10,20),30);
    }

    @Test
    public void test_healthData(){
        HealthData d1 = new HealthData(10, 20);
        assertEquals(d1.AgeDays(),3650);
    }

    @Test 
    public void test_Lab2_25(){
        Lab2_25 l1 = new Lab2_25(100, 2);
        assertEquals(l1.do1x(),50);
        assertEquals(l1.do2x(),25);
        assertEquals(l1.do3x(), 12);
    }

    @Test
    public void test_lab2_27(){
        Lab2_27 l27 = new Lab2_27(1.0, 2.0, 3.0);
        assertEquals(l27.x_power_of_z(),(double)1.0, (double) 0.0001);
        assertEquals(l27.x_power_of_y_power_to_z(),(double) 1.0, (double) 0.0001);
        assertEquals(l27.Absolute_of_y(),(double) 2.0, (double) 0.0001);
        assertEquals(l27.Square_of_xy_to_power_z(), (double)2.8, (double) 0.1);
    }
}

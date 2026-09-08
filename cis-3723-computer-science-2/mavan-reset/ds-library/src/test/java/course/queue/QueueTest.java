package course.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QueueTest{
    private LinkedStackQueue<Integer> intQueue = new LinkedStackQueue<>();


    @Test 
    public void test1(){
        assertTrue(intQueue.isEmpty());
        assertEquals(0, intQueue.size());
    }

    @Test 
    public void test2(){
        intQueue.Enqueue(5);
        intQueue.Enqueue(8);

        assertEquals(2, intQueue.size());
        //assertEquals(5, intQueue.peek());
    }
}
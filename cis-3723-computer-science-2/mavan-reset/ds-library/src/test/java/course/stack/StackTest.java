package course.stack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackTest {
    private LinkStack<Integer> stack = new LinkStack<>();

    @Test
    public void test1(){
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test 
    public void test2(){
        stack.push(3);

        assertEquals(1, stack.size);
        assertFalse(stack.isEmpty());

    }

    @Test 
    public void test3(){
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.peek().intValue());
        assertEquals(3, stack.size);
    }

    @Test 
    public void test4(){
        stack.push(55);
        stack.push(45);

        assertEquals(2, stack.size);
        assertEquals(45, stack.pop().intValue());
        assertEquals(1, stack.size);

        assertEquals(55, stack.pop().intValue());
        assertEquals(0, stack.size);
    }

     @Test 
    public void teststring(){
        LinkStack<String> stringstack = new LinkStack<>();
        stringstack.push("hi");
        stringstack.push("hello world");

        assertEquals("hello world", stringstack.peek());
    }
}
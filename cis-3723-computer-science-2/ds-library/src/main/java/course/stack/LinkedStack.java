public class LinkedStack<T> {
    private class Node {
        T stack;
        Node next;

        Node(T stack) {
            this.stack = stack;
            this.next = null;
        }    
    }

    private Node top;
    private int size;

    public void EntStack(){ //this creates the stack
        this.top = null; //this make the top item nothing
        this.size = 0;  //this makes nothing be in the stack
    }

    public void push(T stack){ //this adds somthing to the stack
        Node newNode = new Node(stack); //this
        newNode.next = top; //this moves the top item under the new one
        top = newNode;
        size++; //this incresce the size
    }

    public T pop(){ //this removes the top item
        if (isEmpty()){ //this throws an error if the stack is empty
            throw new IllegalStateException("Stack is empty");
        }
        T stack = top.stack; //this gets the top item
        top = top.next; //
        size--;
        return stack;
    }

    public T peek(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return top.stack;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int size(){
        return size;
    }
}

//stack.push("hello world")
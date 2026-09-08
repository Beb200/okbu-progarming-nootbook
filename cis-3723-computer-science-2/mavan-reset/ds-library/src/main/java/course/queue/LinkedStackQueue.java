package course.queue;

public class LinkedStackQueue<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node end;
    private int size;

    public void entQueue(){
        this.front = null;
        this.end = null;
        this.size = 0;
    }

    public void Enqueue(T item){
        Node newNode = new Node(item);

        if (isEmpty()){
            front = end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    public T dequeue(){
        if (isEmpty()){
            System.out.println("queue is empty");
            return null;
        }
        T item = front.data;
        front = front.next;

        if (front == null){
            end = null;
        }

        size--;
        return item;
    }

    public T peek(){
        if(isEmpty()){
            return null;
        }
        return front.data;
    }
    public  boolean isEmpty(){
        return front == null;
    }

    public int size(){
        return this.size;
    }

    public void clear(){
        if(isEmpty()){
            System.out.print("is empty. notheing to clear");
        }else {
            int i;
            for(i =0; i < size; ++i){
                dequeue();
            }
        }
    }
}
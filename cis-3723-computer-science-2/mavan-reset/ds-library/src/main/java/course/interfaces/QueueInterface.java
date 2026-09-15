package course.interfaces;

public interface QueueInterface<T> {

    void entQueue();
    void Enqueue(T item);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
    void clear();
}

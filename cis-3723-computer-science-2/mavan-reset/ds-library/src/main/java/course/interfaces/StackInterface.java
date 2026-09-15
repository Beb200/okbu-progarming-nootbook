package course.interfaces;

public interface StackInterface<T> {
    void EntStack();
    void push(T stack);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
    
}
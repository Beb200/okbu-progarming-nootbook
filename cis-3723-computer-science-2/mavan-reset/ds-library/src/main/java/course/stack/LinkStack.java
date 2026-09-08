package course.stack;

public class LinkStack<T> {
   private class Node {
      T stack;
      Node next;
      
      Node(T stack){
         this.stack = stack;
      }
      
   }
   private Node top;
   public  int size;

   public void EntStack() {
      this.top = null;
      this.size = 0;
   }

   public void push(T stack) {
      Node newNode = new Node(stack);
      newNode.next = this.top;
      top = newNode;
      ++size;
   }

   public T pop() {
      if (isEmpty()) {
         throw new IllegalStateException("Stack is empty");
      } else {
         T stack = top.stack;
         top = top.next;
         --size;
         return stack;
      }
   }

   public T peek() {
      if (isEmpty()) {
         throw new IllegalStateException("Stack is empty");
      } else {
         return top.stack;
      }
   }

   public boolean isEmpty() {
      return top == null;
   }

   public int size() {
      return this.size;
   }
}

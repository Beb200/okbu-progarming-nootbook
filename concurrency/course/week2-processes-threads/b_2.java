public class b_2 {
    // 1. Subclass Thread and override run()
    class Greeter extends Thread {
        @Override
        public void run() { System.out.println("hello from " + getName()); }
    }
    Thread a = new Greeter();

    // 2. Implement Runnable and give it to a Thread as its "target" (usually the better design:
    //    it separates the work from the thread that runs it)
    class Countdown implements Runnable {
        public void run() { /* ... */ }
    }
    Thread b = new Thread(new Countdown());

    // 3. A lambda (Runnable has one method, so a lambda can be one)
    Thread c = new Thread(() -> System.out.println("hi"));

    // 4. A virtual thread (Java 21+)
    Thread d = Thread.ofVirtual().unstarted(() -> System.out.println("hi"));

    }

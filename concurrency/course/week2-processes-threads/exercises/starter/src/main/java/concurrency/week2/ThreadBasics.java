package concurrency.week2;

import java.util.concurrent.ThreadLocalRandom;

/*
 * Week 2, Exercise J1: Four ways to start a thread
 *
 *     mvn -q compile exec:exec -Dmain=ThreadBasics
 *
 * Complete the TODOs so that four threads named "greeter", "countdown",
 * "lambda" and "virtual" each print four lines using say(...), all at the
 * same time. Then main prints "all done" only after all four have finished.
 * Answer the questions in exercises/README.md.
 */
public class ThreadBasics {

    // Prints a message, then pauses briefly so the interleaving is visible.
    static void say(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5, 30));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Way 1: subclass Thread and override run().
    static class Greeter extends Thread {
        Greeter(String name) {
            super(name);                       // sets the thread's name
        }

        // TODO: override run() so it calls say("hello " + i) for i = 1..4
    }

    // Way 2: implement Runnable.
    // TODO: make Countdown implement Runnable; its run() says "count 4" down to "count 1"
    static class Countdown {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Greeter("greeter");

        // TODO: Thread b = a new Thread whose target is a Countdown, named "countdown"

        // TODO: Thread c = a new Thread whose target is a LAMBDA that says
        //       "letter a" .. "letter d", named "lambda"

        // TODO: Thread d = a VIRTUAL thread named "virtual" that says "virtual 1".."virtual 4"
        //       Hint: Thread.ofVirtual().name("virtual").unstarted(() -> { ... })

        // TODO: start all four threads, then wait for all four to finish (join)

        System.out.println(Thread.currentThread().getName() + ": all done");
    }
}

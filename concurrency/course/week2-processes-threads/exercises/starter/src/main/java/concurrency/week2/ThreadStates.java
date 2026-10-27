package concurrency.week2;

/*
 * Week 2, Exercise J2: Watching a thread's life cycle
 *
 *     mvn -q compile exec:exec -Dmain=ThreadStates
 *
 * A "watcher" loop in main (already written) samples worker.getState() every
 * millisecond and prints each change. Your job: write the worker's body so it
 * passes through RUNNABLE, TIMED_WAITING and WAITING (in that order) before
 * the given synchronized block makes it BLOCKED.
 *
 * Expected output (times will vary):
 *     0 ms  NEW
 *     0 ms  RUNNABLE
 *   200 ms  TIMED_WAITING
 *   500 ms  WAITING
 *   800 ms  BLOCKED
 *   950 ms  RUNNABLE
 *  1050 ms  TERMINATED
 *
 * An extra RUNNABLE line between two states (for example between TIMED_WAITING
 * and WAITING) is normal: the thread runs briefly between steps and the watcher
 * sometimes catches it. Run it a few times; most runs show the exact sequence.
 */
public class ThreadStates {

    static final Object door = new Object();   // a lock, used to force BLOCKED

    // Keeps the CPU busy (no waiting) for about `millis` milliseconds.
    static void busy(long millis) {
        long end = System.nanoTime() + millis * 1_000_000;
        while (System.nanoTime() < end) { /* spin */ }
    }

    // The first three stages of the worker's life.
    static void workerSteps(Thread helper) throws InterruptedException {
        // TODO 1 (RUNNABLE):      compute for about 200 ms           (hint: busy)
        // TODO 2 (TIMED_WAITING): pause for 300 ms                   (hint: Thread.sleep)
        // TODO 3 (WAITING):       start helper, then wait for it to
        //                         finish, with no time limit         (hint: join)
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helper = new Thread(() -> busy(300), "helper");

        Thread worker = new Thread(() -> {
            try {
                workerSteps(helper);
            } catch (InterruptedException e) {
                return;
            }
            // Given: entering a lock that main is holding makes this thread BLOCKED.
            // (Locks and synchronized are Week 4 topics.)
            synchronized (door) {
                busy(100);
            }
        }, "worker");

        Thread.State last = worker.getState();
        System.out.printf("%5d ms  %s%n", 0, last);      // NEW: created, not started

        long t0 = System.nanoTime();
        worker.start();

        boolean holdingDoor = true;
        synchronized (door) {                            // main grabs the lock first...
            while (worker.isAlive() && holdingDoor) {
                last = report(worker, last, t0);
                if (last == Thread.State.BLOCKED) {      // ...and lets go a bit after worker blocks
                    Thread.sleep(150);
                    holdingDoor = false;
                }
                Thread.sleep(1);
            }
        }
        while (last != Thread.State.TERMINATED) {
            last = report(worker, last, t0);
            Thread.sleep(1);
        }
    }

    // Prints the state if it changed; returns the current state.
    static Thread.State report(Thread t, Thread.State last, long t0) {
        Thread.State now = t.getState();
        if (now != last) {
            System.out.printf("%5d ms  %s%n", (System.nanoTime() - t0) / 1_000_000, now);
        }
        return now;
    }
}

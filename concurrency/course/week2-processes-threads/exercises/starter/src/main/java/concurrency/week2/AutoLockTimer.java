package concurrency.week2;

/*
 * Week 2, Exercise J3: From state diagram to thread (phone auto-lock)
 *
 * A phone locks its screen after T seconds without a touch. Every touch
 * restarts the countdown. Implement this state diagram:
 *
 *   (see figures/autolock-named.png; shown for T = 3)
 *
 *   3 s LEFT --tick--> 2 s LEFT --tick--> 1 s LEFT --tick--> 0 s LEFT --lock--> LOCKED
 *   touch, from ANY "s LEFT" state (including 0 s LEFT), goes back to 3 s LEFT.
 *   LOCKED has no way out: the program ends.
 *
 *     mvn -q compile exec:exec -Dmain=AutoLockTimer
 *         // locks after 5 seconds; press Enter to "touch" the screen
 *     mvn -q compile exec:exec -Dmain=AutoLockTimer -Dargs="3"
 *
 * Complete the three TODOs. Expected behaviour (T = 5, Enter pressed once):
 *     Screen locks after 5 s. Press Enter to touch the screen.
 *     5 4 3 (touch) 5 4 3 2 1
 *     LOCKED
 *     main: done
 *
 * Before coding, write down which part of the diagram each TODO implements.
 * Compare with the COUNTDOWN example in the tutorial (section B.6): the big
 * difference is that here interrupt() means "wake up and start over", not "stop".
 */
public class AutoLockTimer implements Runnable {

    private final int timeout;
    private Thread timer;

    public AutoLockTimer(int timeout) {
        this.timeout = timeout;
    }

    // The screen turns on and the countdown begins.
    public void start() {
        timer = new Thread(this, "auto-lock");
        timer.start();
    }

    // touch: back to "T s LEFT"
    public void touch() {
        // TODO 1: wake the timer thread so that it restarts its countdown (hint: interrupt)
    }

    public boolean isLocked() {
        return timer != null && !timer.isAlive();
    }

    // Waits for the timer thread (if any) to terminate.
    public void join() throws InterruptedException {
        if (timer != null) timer.join();
    }

    @Override
    public void run() {
        int i = timeout;
        // TODO 2: implement the "i s LEFT" states with a loop.
        //  - while i > 0: print i followed by a space, sleep one second, then decrement i.
        //    If the sleep is interrupted, that was a touch: print "(touch) " and set i back to timeout.
        //  - when i reaches 0: print "\nLOCKED" and return.
        //
        // TODO 3: the diagram also allows touch when i == 0 (the user touches the screen
        //  just as it is about to lock). Before locking, check whether an interrupt has
        //  arrived that no sleep has caught yet (hint: Thread.interrupted()) and, if so,
        //  treat it as a touch instead of locking.
        System.out.println("\nLOCKED");
    }

    public static void main(String[] args) throws Exception {
        int t = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        System.out.println("Screen locks after " + t + " s. Press Enter to touch the screen.");

        AutoLockTimer phone = new AutoLockTimer(t);
        phone.start();

        // The main thread is the "user": each Enter is a touch.
        while (!phone.isLocked()) {
            if (System.in.available() > 0) {
                while (System.in.available() > 0) System.in.read();   // consume the whole line
                phone.touch();
            }
            Thread.sleep(50);
        }
        phone.join();
        System.out.println("main: done");
    }
}

package concurrency.week2.demos;

/*
 * Week 2 demo (lecture slide 29, tutorial section B.6): From model to thread
 *
 * A console countdown timer (think: microwave, quiz timer, rocket launch)
 * implementing this state diagram (figures/countdown-named.png, shown for N = 3;
 * after Magee & Kramer, ch. 2):
 *
 *   READY --start--> 3 LEFT --tick--> 2 LEFT --tick--> 1 LEFT --tick--> 0 LEFT --beep--> DONE
 *   stop, from any "LEFT" state, goes straight to DONE.  DONE has no way out.
 *
 *     mvn -q compile exec:exec -Dmain=CountdownTimer
 *         // 10-second countdown; press Enter to stop early
 *     mvn -q compile exec:exec -Dmain=CountdownTimer -Dargs="5"
 *
 * Diagram -> code mapping (COUNTDOWN[i] = the "i LEFT" state):
 *   start                    -> start(): create and start the counter thread
 *   COUNTDOWN[i] (recursion) -> the while loop in run(), with i as a variable
 *   when(i>0) tick           -> if (i > 0) { tick(i); ... i--; }
 *   when(i==0) beep -> STOP  -> if (i == 0) { beep(); return; }   (run() returns = STOP)
 *   stop -> STOP             -> stop(): interrupt the counter thread; run() returns
 */
public class CountdownTimer implements Runnable {

    private final int n;
    private Thread counter;

    public CountdownTimer(int n) {
        this.n = n;
    }

    // start -> COUNTDOWN[N]
    public void start() {
        counter = new Thread(this, "counter");
        counter.start();
    }

    // stop -> STOP. We ask the thread to stop; it decides when (cooperative cancellation).
    // (The old Thread.stop() is unsafe and, since JDK 20, throws UnsupportedOperationException.)
    public void stop() {
        counter.interrupt();
    }

    // Waits for the counter thread (if any) to terminate.
    public void join() throws InterruptedException {
        if (counter != null) counter.join();
    }

    public boolean isRunning() {
        return counter != null && counter.isAlive();
    }

    @Override
    public void run() {
        int i = n;
        try {
            while (true) {
                if (i > 0) {                     // when(i>0) tick -> COUNTDOWN[i-1]
                    tick(i);
                    Thread.sleep(1000);          // throws InterruptedException if stop() is called
                    i--;
                }
                if (i == 0) {                    // when(i==0) beep -> STOP
                    beep();
                    return;
                }
            }
        } catch (InterruptedException e) {       // stop -> STOP
            System.out.println("\nstopped at " + i);
        }
    }

    private void tick(int i) {
        System.out.print(i + " ");
    }

    private void beep() {
        System.out.println("\nBEEP! \u0007");
    }

    public static void main(String[] args) throws Exception {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        System.out.println("Counting down from " + n + ". Press Enter to stop.");

        CountdownTimer timer = new CountdownTimer(n);
        timer.start();

        // The main thread stays responsive to the user while the counter thread runs.
        while (timer.isRunning()) {
            if (System.in.available() > 0) {     // Enter pressed?
                System.in.read();
                timer.stop();
                break;
            }
            Thread.sleep(50);
        }
        timer.join();
        System.out.println("main: done");
    }
}

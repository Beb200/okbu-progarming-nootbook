package concurrency.week1;

import java.util.ArrayList;
import java.util.List;

/*
 * Week 1, Exercise 3: Why bother with concurrency?
 *
 * Part 1: a phone app downloads five files. Each "download" mostly WAITS (for
 * the network), simulated with Thread.sleep.
 * Part 2: "crunch" tasks that COMPUTE instead of wait.
 *
 *     mvn -q compile exec:exec -Dmain=Downloads              // 2 x cores crunch tasks
 *     mvn -q compile exec:exec -Dmain=Downloads -Dargs="1"   // 1 crunch task
 *
 * The helper runAllConcurrently(...) is already written. It starts every task
 * on its own thread and waits until they have all finished. You will see how
 * it works in Week 2; for now, treat it as a black box.
 *
 * TODO (one line of code; see exercises/README.md for the questions):
 *   In main, time the SAME downloads again, this time using runAllConcurrently.
 *   The crunch experiment (Part 2) is already written for you.
 */
public class Downloads {

    static Runnable download(String file, int millis) {
        return () -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("  finished " + file);
        };
    }

    static Runnable crunch(String name) {
        return () -> {
            double x = 0;
            for (long i = 1; i < 150_000_000L; i++) x += Math.sqrt(i);
            System.out.printf("  finished %s (%.0f)%n", name, x);
        };
    }

    static void runSequentially(List<Runnable> tasks) {
        for (Runnable task : tasks) task.run();
    }

    static void runAllConcurrently(List<Runnable> tasks) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) threads.add(Thread.ofVirtual().start(task));
        for (Thread t : threads) t.join();
    }

    // Small helper so each experiment is one line.
    static long timeMillis(String label, List<Runnable> tasks, boolean concurrent)
            throws InterruptedException {
        System.out.println(label + ":");
        long start = System.nanoTime();
        if (concurrent) runAllConcurrently(tasks); else runSequentially(tasks);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("%s time: %d ms%n%n", label, ms);
        return ms;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Runnable> downloads = List.of(
                download("song.mp3", 1200),
                download("photo.jpg", 400),
                download("video.mp4", 2000),
                download("podcast.mp3", 1500),
                download("notes.pdf", 300));

        timeMillis("Sequential downloads", downloads, false);

        // TODO: time the same downloads concurrently.
        //       Hint: copy the line above and change two things.

        int cores = Runtime.getRuntime().availableProcessors();
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 2 * cores;
        System.out.println("This machine reports " + cores + " cores; using " + n + " crunch tasks.\n");

        List<Runnable> crunches = new ArrayList<>();
        for (int i = 1; i <= n; i++) crunches.add(crunch("crunch-" + i));

        long seq = timeMillis("Sequential crunch", crunches, false);
        long con = timeMillis("Concurrent crunch", crunches, true);
        System.out.printf("Speed-up: %.1fx (at most min(tasks, cores) = %d)%n",
                (double) seq / con, Math.min(n, cores));
    }
}

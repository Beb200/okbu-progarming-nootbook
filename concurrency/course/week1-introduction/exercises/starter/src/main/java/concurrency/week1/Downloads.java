package concurrency.week1;

import java.util.ArrayList;
import java.util.List;

/*
 * Week 1, Exercise 3: Why bother with concurrency?
 *
 * A phone app downloads several files. Each "download" is simulated by a
 * task that mostly WAITS (for the network), using Thread.sleep.
 *
 *     mvn -q compile exec:exec -Dmain=Downloads
 *
 * The helper runAllConcurrently(...) is already written for you. It starts
 * every task on its own thread and waits until they have all finished.
 * You will see how it works in Week 2; for now, treat it as a black box.
 *
 * TODO (see exercises/README.md for the questions to answer):
 *   1. Run the program as given and record the sequential time.
 *   2. In main, add a second timing block that runs the SAME downloads using
 *      runAllConcurrently(...). Record the time.
 *   3. Do the same for the CPU-bound "crunch" tasks (they compute instead of wait).
 *      Try 1, 2, cores, and 2 x cores crunch tasks.
 */
public class Downloads {

    // A task that simulates waiting for the network for `millis` milliseconds.
    static Runnable download(String file, int millis) {
        return () -> {
            try {
                Thread.sleep(millis);           // waiting, not computing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("  finished " + file);
        };
    }

    // A task that keeps a CPU core busy for a while (pure computation, no waiting).
    static Runnable crunch(String name) {
        return () -> {
            double x = 0;
            for (long i = 1; i < 150_000_000L; i++) x += Math.sqrt(i);
            System.out.printf("  finished %s (%.0f)%n", name, x);
        };
    }

    // Runs the tasks one after another on the current thread.
    static void runSequentially(List<Runnable> tasks) {
        for (Runnable task : tasks) task.run();
    }

    // Runs every task on its own thread, then waits for all of them to finish.
    static void runAllConcurrently(List<Runnable> tasks) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) threads.add(Thread.ofVirtual().start(task));
        for (Thread t : threads) t.join();
    }

    public static void main(String[] args) throws InterruptedException {
        List<Runnable> downloads = List.of(
                download("song.mp3", 1200),
                download("photo.jpg", 400),
                download("video.mp4", 2000),
                download("podcast.mp3", 1500),
                download("notes.pdf", 300));

        System.out.println("Sequential downloads:");
        long start = System.nanoTime();
        runSequentially(downloads);
        System.out.printf("Sequential time: %d ms%n%n", (System.nanoTime() - start) / 1_000_000);

        // TODO 2: time the same downloads with runAllConcurrently(downloads)

        // TODO 3: build a list of crunch(...) tasks and time them both ways.
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("This machine reports " + cores + " cores.");
    }
}

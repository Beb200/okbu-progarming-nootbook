package concurrency.week2.demos;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Week 2 demo: platform threads vs virtual threads (Java 21+, JEP 444)
 *
 * Each task models a web request that spends 1 second waiting on I/O.
 *
 *     mvn -q compile exec:exec -Dmain=VirtualThreadsDemo
 *         // 10,000 tasks, virtual threads
 *     mvn -q compile exec:exec -Dmain=VirtualThreadsDemo -Dargs="2000 platform"
 *         // 2,000 tasks, pool of 200 OS threads (~10 s)
 *     mvn -q compile exec:exec -Dmain=VirtualThreadsDemo -Dargs="100000"
 *         // 100,000 virtual threads
 *
 * Talking points:
 *  - A platform thread is a thin wrapper around an operating-system thread:
 *    it has a large stack and is expensive to create, so servers use a fixed pool.
 *  - A virtual thread is managed by the JVM. While it waits (sleep, I/O) it is
 *    unmounted from its carrier OS thread, so a few OS threads can serve
 *    hundreds of thousands of waiting virtual threads.
 *  - Virtual threads make WAITING cheap; they do not make COMPUTING faster.
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) {
        int tasks = args.length > 0 ? Integer.parseInt(args[0]) : 10_000;
        boolean platform = args.length > 1 && args[1].equals("platform");

        long start = System.nanoTime();
        try (ExecutorService executor = platform
                ? Executors.newFixedThreadPool(200)               // 200 OS threads, shared
                : Executors.newVirtualThreadPerTaskExecutor()) {  // one virtual thread per task
            for (int i = 0; i < tasks; i++) {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));          // "waiting for the database"
                    return null;
                });
            }
        }   // close() waits for every submitted task to finish
        long ms = (System.nanoTime() - start) / 1_000_000;

        System.out.printf("%,d tasks on %s threads finished in %,d ms%n",
                tasks, platform ? "200 platform" : "virtual", ms);
    }
}

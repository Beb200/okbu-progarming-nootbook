package concurrency.week1;

/*
 * Week 1, Exercise 2: Lost Likes
 *
 * A viral post is "liked" by many users at the same time. Each user is
 * simulated by a thread that calls like() many times. All of the threads
 * share ONE counter.
 *
 * Run it from this project folder (needs Java 21+ and Maven):
 *
 *     mvn -q compile exec:exec -Dmain=LikeCounter
 *         // 2 users, 1,000,000 likes each
 *     mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="4 500000"
 *         // 4 users, 500,000 likes each
 *
 * You do NOT need to change this file for parts (a)-(d) of the exercise.
 * Just run it, record what you see, and explain it. See exercises/README.md.
 */
public class LikeCounter {

    // The shared counter. Every thread reads and writes this one variable.
    // ("volatile" only stops the Java compiler from optimizing the loop into
    //  a single addition, which would hide the problem. It does NOT fix it.)
    static volatile int likes = 0;

    // One "like". It looks like a single step, but the computer does three:
    //   1. read likes   2. add one   3. write likes
    static void like() {
        likes = likes + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        int users = args.length > 0 ? Integer.parseInt(args[0]) : 2;
        int likesPerUser = args.length > 1 ? Integer.parseInt(args[1]) : 1_000_000;
        int trials = 5;

        System.out.printf("%d users x %,d likes each = %,d expected%n%n",
                users, likesPerUser, (long) users * likesPerUser);

        for (int trial = 1; trial <= trials; trial++) {
            likes = 0;

            // Create one thread per user. (Threads are covered properly in Week 2.)
            Thread[] threads = new Thread[users];
            for (int u = 0; u < users; u++) {
                threads[u] = new Thread(() -> {
                    for (int k = 0; k < likesPerUser; k++) {
                        like();
                    }
                });
            }

            for (Thread t : threads) t.start();   // run all users at the same time
            for (Thread t : threads) t.join();    // wait until every user is done

            long expected = (long) users * likesPerUser;
            System.out.printf("trial %d: counted %,11d   lost %,11d%n",
                    trial, likes, expected - likes);
        }
    }
}

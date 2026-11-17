package concurrency.week3;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Week 3, Exercise J2: Which outputs are possible?
 *
 *     mvn -q compile exec:exec -Dmain=TwoBaristas
 *
 * Two baristas call out drinks as they finish them. Ana (one thread) makes a
 * latte, then a mocha. Ben (another thread) makes a chai, then a cocoa. Each
 * trial records the order of the four calls, and the program counts how often
 * each order appears over many trials.
 *
 * Write down your prediction BEFORE you run it. See exercises/README.md.
 */
public class TwoBaristas {

    static final int TRIALS = 2000;

    // Waits a tiny random time, so that the baristas overlap more.
    static void pause() {
        try {
            Thread.sleep(0, ThreadLocalRandom.current().nextInt(1_000_000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Adds a finished drink to the pickup board.
    static void callOut(Queue<String> board, String drink) {
        // TODO: for part (c), call pause() here, then run the program again.
        board.add(drink);
    }

    // One barista: makes two drinks, calling each one out when it is finished.
    static class Barista implements Runnable {
        private final Queue<String> board;
        private final String firstDrink;
        private final String secondDrink;

        Barista(Queue<String> board, String firstDrink, String secondDrink) {
            this.board = board;
            this.firstDrink = firstDrink;
            this.secondDrink = secondDrink;
        }

        @Override
        public void run() {
            callOut(board, firstDrink);
            callOut(board, secondDrink);
        }
    }

    static String runOneTrial() throws InterruptedException {
        Queue<String> board = new ConcurrentLinkedQueue<>();
        Thread ana = new Thread(new Barista(board, "latte", "mocha"));
        Thread ben = new Thread(new Barista(board, "chai", "cocoa"));
        ana.start();
        ben.start();
        ana.join();
        ben.join();
        return String.join(" ", List.copyOf(board));
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> tally = new TreeMap<>();
        for (int trial = 0; trial < TRIALS; trial++) {
            String order = runOneTrial();
            tally.put(order, tally.getOrDefault(order, 0) + 1);
        }
        System.out.println(tally.size() + " different orders in " + TRIALS + " trials:");
        for (String order : tally.keySet()) {
            System.out.printf("  %-24s %5d%n", order, tally.get(order));
        }
    }
}

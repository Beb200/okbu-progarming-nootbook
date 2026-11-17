package concurrency.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Week 3, Exercise J1: Fare quotes with an ExecutorService
 *
 * A ride-sharing app has five ride requests waiting. Before it can show the
 * riders their prices, it asks a pricing service for a quote for each ride.
 * quoteSequentially asks for one quote at a time. Your job is to ask for all
 * of them concurrently, with one task per ride request.
 *
 *     mvn -q compile exec:exec -Dmain=FareQuotes
 *
 * Complete quoteConcurrently. See exercises/README.md for the questions.
 */
public class FareQuotes {

    record RideRequest(String rider, double miles) {}

    static final List<RideRequest> requests = List.of(
            new RideRequest("Ava", 2.0),
            new RideRequest("Ben", 5.6),
            new RideRequest("Cam", 3.2),
            new RideRequest("Dee", 8.0),
            new RideRequest("Eli", 1.4));

    // Asks the pricing service for one ride's fare, in cents.
    // The sleep stands in for waiting on the network.
    static int quoteFare(RideRequest request) throws InterruptedException {
        Thread.sleep(800);
        return 250 + (int) Math.round(request.miles() * 175);
    }

    // One quote as a task: a Callable that returns the fare (already written for you).
    static class FareQuote implements Callable<Integer> {
        private final RideRequest request;

        FareQuote(RideRequest request) {
            this.request = request;
        }

        @Override
        public Integer call() throws Exception {
            return quoteFare(request);
        }
    }

    // One request after another, on the main thread.
    static int quoteSequentially(List<RideRequest> requests) throws Exception {
        int total = 0;
        for (RideRequest request : requests) {
            total += quoteFare(request);
        }
        return total;
    }

    static int quoteConcurrently(List<RideRequest> requests) throws Exception {
        try (ExecutorService pricing = Executors.newFixedThreadPool(4)) {
            // TODO 1: for each request, make a task and submit it to the pool:
            //             FareQuote quote = new FareQuote(request);
            //         Keep every Future<Integer> that submit returns in a list.

            // TODO 2: AFTER all the tasks are submitted, go through the list, call get()
            //         on each Future and add up the fares. Return the total.

            return 0;
        }
    }

    static String dollars(int cents) {
        return String.format("$%d.%02d", cents / 100, cents % 100);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(requests.size() + " ride requests");

        long start = System.nanoTime();
        int sequential = quoteSequentially(requests);
        System.out.printf("Sequential: fares total %s in %d ms%n", dollars(sequential), (System.nanoTime() - start) / 1_000_000);

        start = System.nanoTime();
        int concurrent = quoteConcurrently(requests);
        System.out.printf("Concurrent: fares total %s in %d ms%n", dollars(concurrent), (System.nanoTime() - start) / 1_000_000);
    }
}

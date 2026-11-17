package concurrency.week3.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Week 3 live demo: an ExecutorService, and the classic Future.get() mistake.
 * The code matches tutorial sections B.3 and B.4.
 *
 * Five drinks each take one second to make, and the shop has five baristas.
 *
 *     mvn -q compile exec:exec -Dmain=CafeOrders                 // take every order, then collect: about 1 s
 *     mvn -q compile exec:exec -Dmain=CafeOrders -Dargs="trap"   // get() right after submit: about 5 s
 */
public class CafeOrders {

    static final List<String> orders = List.of("latte", "mocha", "chai", "espresso", "cappuccino");

    // Making a drink takes one second, then the barista hands it back.
    static String makeDrink(String drink) throws InterruptedException {
        Thread.sleep(1000);
        return drink + " made by " + Thread.currentThread().getName();
    }

    // One order: a task that returns a result (the drink).
    static class DrinkOrder implements Callable<String> {
        private final String drink;

        DrinkOrder(String drink) {
            this.drink = drink;
        }

        @Override
        public String call() throws Exception {
            return makeDrink(drink);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean trap = args.length > 0 && args[0].equals("trap");
        long start = System.nanoTime();

        try (ExecutorService baristas = Executors.newFixedThreadPool(5)) {
            if (trap) {
                // The cashier waits for each drink before taking the next order.
                for (String drink : orders) {
                    DrinkOrder order = new DrinkOrder(drink);
                    System.out.println(baristas.submit(order).get());   // don't do this
                }
            } else {
                // Take every order first; then collect the drinks.
                List<Future<String>> tickets = new ArrayList<>();
                for (String drink : orders) {
                    DrinkOrder order = new DrinkOrder(drink);
                    tickets.add(baristas.submit(order));
                }
                for (Future<String> ticket : tickets) {
                    System.out.println(ticket.get());
                }
            }
        }
        System.out.printf("All drinks done in %d ms%n", (System.nanoTime() - start) / 1_000_000);
    }
}

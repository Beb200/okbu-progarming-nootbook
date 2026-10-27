package concurrency.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Week 1, optional challenge (recursion + concurrency): Enumerating interleavings
 *
 * Two processes share a variable x. Each process is a list of ATOMIC steps.
 * The scheduler may run the steps in any order that keeps each process's own
 * steps in order. This program should list every such order (interleaving)
 * and the final value of x it produces.
 *
 * Your job: implement interleave(...) and simulate(...). Everything else is done.
 * Check part (a) against the table in the tutorial, and part (b) against your
 * answer to Exercise 1.
 *
 *     mvn -q compile exec:exec -Dmain=Interleavings
 */
public class Interleavings {

    enum Kind { READ, WRITE }

    // One atomic step: process `proc` either READs x into its private register,
    // or WRITEs (register + delta) back to x.
    record Step(String proc, Kind kind, int delta) {
        @Override public String toString() {
            return (kind == Kind.READ ? "R" : "W") + proc;
        }
    }

    // All ways to merge a and b that keep each list's own order.
    // Base case: if either list is empty, the only interleaving is the other list.
    // Recursive case: the first step is either a's first step or b's first step.
    static List<List<Step>> interleave(List<Step> a, List<Step> b) {
        // TODO: implement recursively. Hints:
        //  - copy lists before you modify them (subList views are shared!)
        //  - a.subList(1, a.size()) is "a without its first step"
        return new ArrayList<>();
    }

    // Executes one interleaving, starting from x = initial, and returns final x.
    static int simulate(List<Step> schedule, int initial) {
        // TODO: walk through the schedule.
        //  READ  step: copy x into that process's private register
        //  WRITE step: set x to (that process's register + delta)
        return initial;
    }

    static void explore(String title, List<Step> p1, List<Step> p2, int initial) {
        System.out.println(title);
        List<List<Step>> all = interleave(p1, p2);
        TreeMap<Integer, Integer> outcomes = new TreeMap<>();
        for (List<Step> schedule : all) {
            int result = simulate(schedule, initial);
            outcomes.merge(result, 1, Integer::sum);
            System.out.printf("  %-28s x = %d%n", schedule, result);
        }
        System.out.println("  " + all.size() + " interleavings; final value -> how often: " + outcomes);
        System.out.println();
    }

    public static void main(String[] args) {
        // Part (a): the slide example. Process 1: x := x + 1, Process 2: x := x - 1
        explore("x := x + 1  ||  x := x - 1   (x starts at 0)",
                List.of(new Step("1", Kind.READ, 0), new Step("1", Kind.WRITE, +1)),
                List.of(new Step("2", Kind.READ, 0), new Step("2", Kind.WRITE, -1)), 0);

        // Part (b): two likes at the same time
        explore("x := x + 1  ||  x := x + 1   (x starts at 0)",
                List.of(new Step("1", Kind.READ, 0), new Step("1", Kind.WRITE, +1)),
                List.of(new Step("2", Kind.READ, 0), new Step("2", Kind.WRITE, +1)), 0);

        // Part (c): each process increments twice. Predict the count BEFORE running!
        explore("(x := x + 1; x := x + 1)  ||  (x := x + 1; x := x + 1)",
                List.of(new Step("1", Kind.READ, 0), new Step("1", Kind.WRITE, +1),
                        new Step("1", Kind.READ, 0), new Step("1", Kind.WRITE, +1)),
                List.of(new Step("2", Kind.READ, 0), new Step("2", Kind.WRITE, +1),
                        new Step("2", Kind.READ, 0), new Step("2", Kind.WRITE, +1)), 0);
    }
}

package concurrency.week3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Week 3, optional challenge: Stepping two state diagrams together
 *
 *     mvn -q compile exec:exec -Dmain=ComposeDiagrams
 *
 * The rules for running two diagrams side by side:
 *   - an action only ONE diagram knows: that diagram takes it alone;
 *   - an action BOTH diagrams know: they must take it at the same moment,
 *     so it can happen only when both are ready for it.
 *
 * Complete compose. `mvn test` checks your work; the expected numbers were
 * checked with LTSA, the textbook's tool.
 */
public class ComposeDiagrams {

    record Transition(String action, int target) {}

    static class Diagram {
        final Map<Integer, List<Transition>> out = new TreeMap<>();

        // Adds  from --action--> to.  Returns this so calls can be chained.
        Diagram add(int from, String action, int to) {
            if (!out.containsKey(from)) out.put(from, new ArrayList<>());
            if (!out.containsKey(to)) out.put(to, new ArrayList<>());
            out.get(from).add(new Transition(action, to));
            return this;
        }

        List<Transition> enabled(int state) {
            return out.getOrDefault(state, List.of());
        }

        int stateCount() {
            return out.size();
        }

        int transitionCount() {
            int n = 0;
            for (List<Transition> ts : out.values()) n += ts.size();
            return n;
        }

        Set<String> actions() {
            Set<String> labels = new TreeSet<>();
            for (List<Transition> ts : out.values())
                for (Transition t : ts) labels.add(t.action());
            return labels;
        }

        // States with no way out.
        Set<Integer> stuckStates() {
            Set<Integer> stuck = new TreeSet<>();
            for (var entry : out.entrySet())
                if (entry.getValue().isEmpty()) stuck.add(entry.getKey());
            return stuck;
        }
    }

    // A state of the combined diagram: where each of the two diagrams is.
    record Pair(int a, int b) {}

    // Builds the combined diagram of a and b. Both start in state 0, and the pair
    // (0, 0) becomes state 0 of the result. Only reachable pairs are included.
    static Diagram compose(Diagram a, Diagram b) {
        // TODO: explore the combined diagram, breadth-first, starting from Pair(0, 0).
        //  - give each new Pair the next state number (Pair(0, 0) is state 0 of the result);
        //    a Map<Pair, Integer> and an ArrayDeque<Pair> work well;
        //  - from a pair (x, y), for each transition of a out of x:
        //      if b does not know the action, a moves alone: the next pair is (a's target, y);
        //      if b knows it, the action can only happen if b also has it enabled in y:
        //      the next pair is (a's target, b's target);
        //  - then, for each transition of b out of y that a does not know, b moves alone;
        //  - record each move with result.add(...), and visit each new pair once.
        Diagram result = new Diagram();
        result.out.put(0, new ArrayList<>());
        return result;
    }

    static void show(String name, Diagram d) {
        System.out.printf("%s: %d states, %d transitions, stuck states: %s%n",
                name, d.stateCount(), d.transitionCount(), d.stuckStates());
    }

    public static void main(String[] args) {
        Diagram barista = new Diagram().add(0, "make", 1).add(1, "ready", 0);
        Diagram customer = new Diagram().add(0, "ready", 1).add(1, "pick_up", 0);
        show("barista + customer", compose(barista, customer));

        Diagram talker = new Diagram().add(0, "think", 1).add(1, "talk", 2);
        Diagram itch = new Diagram().add(0, "scratch", 1);
        show("talker + itch", compose(talker, itch));

        // Each insists on a different action first, and they share both actions.
        Diagram p = new Diagram().add(0, "a", 1).add(1, "b", 0);
        Diagram q = new Diagram().add(0, "b", 1).add(1, "a", 0);
        show("p + q (each waits for the other)", compose(p, q));
    }
}

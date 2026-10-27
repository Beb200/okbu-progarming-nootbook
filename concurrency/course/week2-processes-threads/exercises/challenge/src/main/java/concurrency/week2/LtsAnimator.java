package concurrency.week2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Week 2, optional challenge: a state diagram in Java
 *
 * A Labelled Transition System is a set of states (numbered 0..n-1, normally
 * starting in state 0) and labelled transitions  from --action--> to.  This
 * class stores one, and "animates" it by repeatedly choosing an enabled action
 * at random, the way a scheduler might.
 *
 * Animation SAMPLES behaviour: one random trace says nothing about the paths it
 * did not take, so it is not a safety check. That needs an exhaustive search
 * (part (d)) or a real model checker such as LTSA, the textbook's tool.
 *
 * TODO (a):  enabled, alphabet, isDeterministic, randomTrace and countdown.
 * TODO (c):  fromAut, to load a state diagram saved in a file (models/countdown.aut);
 * TODO (d):  deadlockStates, an exhaustive search for stuck states.
 *
 *     mvn -q compile exec:exec -Dmain=LtsAnimator
 *         // built-in examples
 *     mvn -q compile exec:exec -Dmain=LtsAnimator -Dargs="../../models/countdown.aut"
 *         // part (c): load a state diagram from a file
 */
public class LtsAnimator {

    record Transition(String action, int target) {}

    static class Lts {
        final String name;
        private final Map<Integer, List<Transition>> out = new TreeMap<>();
        private int startState = 0;   // fromAut sets this from the file's header

        Lts(String name) {
            this.name = name;
        }

        // Adds  from --action--> to.  Returns this so calls can be chained.
        Lts add(int from, String action, int to) {
            out.computeIfAbsent(from, k -> new ArrayList<>()).add(new Transition(action, to));
            out.computeIfAbsent(to, k -> new ArrayList<>());   // make sure `to` exists
            return this;
        }

        int startState() {
            return startState;
        }

        // The transitions that may happen next in `state` (empty = no way out).
        List<Transition> enabled(int state) {
            // TODO: the transitions out of `state` (an empty list if there are none).
            //       Return a READ-ONLY view (hint: Collections.unmodifiableList) so a
            //       caller cannot change the model by changing the list it gets back.
            return List.of();
        }

        int stateCount() {
            return out.size();
        }

        // Every action label that appears on some transition.
        Set<String> alphabet() {
            // TODO: collect every action label (a TreeSet keeps them sorted)
            return new TreeSet<>();
        }

        // Deterministic = no state offers the same action twice.
        boolean isDeterministic() {
            // TODO: false if any state has two transitions with the same action
            return true;
        }

        // Walks from the start state, choosing uniformly among enabled transitions.
        // Stops after maxSteps actions, or earlier if no action is enabled.
        List<String> randomTrace(int maxSteps, Random random) {
            // TODO: start in startState (normally 0); repeatedly pick a random enabled transition,
            //       record its action and move to its target. If nothing is
            //       enabled, add "STOP" and finish.
            return new ArrayList<>();
        }

        // Stretch (d): an EXHAUSTIVE search. Return every state reachable from the
        // start state that has no outgoing transitions (what LTSA calls a
        // "potential DEADLOCK"). Unlike randomTrace, this must visit EVERY reachable state.
        Set<Integer> deadlockStates() {
            // TODO (stretch d): breadth-first search from startState.
            //  - keep a Set of visited states and a queue (ArrayDeque) of states to visit
            //  - for each state taken from the queue: if enabled(state) is empty, it is stuck;
            //    otherwise add each not-yet-visited target to visited and to the queue
            //  - return the stuck states (a TreeSet keeps them sorted)
            throw new UnsupportedOperationException("deadlockStates is not implemented yet");
        }

        // Stretch (c): reads the Aldebaran (.aut) format that LTSA exports:
        //   des(0,4,3)          start state, number of transitions, number of states
        //   (0,toss,2)          or (0,"toss",2)
        static Lts fromAut(Path file) throws IOException {
            // TODO (stretch c): load the file, or throw an IOException with a helpful
            //  message. Never return a half-loaded model. Requirements:
            //  - the first non-blank line must be the header des(start,transitions,states);
            //    set startState, and create every state 0..states-1 (some may have no
            //    transitions at all)
            //  - every other non-blank line must be a transition; reject anything else
            //  - labels must not be empty; strip the quotes from "quoted" labels
            //  - reject state numbers outside 0..states-1
            //  - the number of transitions read must equal the header's count
            //  Hints: Files.readAllLines(file);
            //         Pattern.compile("des\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)")
            //         Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\"[^\"]+\"|[^\",()\\s]+)\\s*,\\s*(\\d+)\\s*\\)")
            return new Lts(file.getFileName().toString());
        }
    }

    // SWITCH = (on->off->SWITCH).
    static Lts lightSwitch() {
        return new Lts("SWITCH").add(0, "on", 1).add(1, "off", 0);
    }

    // TRAFFICLIGHT = (red->orange->green->orange->TRAFFICLIGHT).
    static Lts trafficLight() {
        return new Lts("TRAFFICLIGHT")
                .add(0, "red", 1).add(1, "orange", 2).add(2, "green", 3).add(3, "orange", 0);
    }

    // COIN = (toss->HEADS|toss->TAILS), HEADS=(heads->COIN), TAILS=(tails->COIN).
    static Lts coin() {
        return new Lts("COIN")
                .add(0, "toss", 1).add(0, "toss", 2)
                .add(1, "heads", 0).add(2, "tails", 0);
    }

    // COUNTDOWN(N=3): states 0 = before start, 1..4 = COUNTDOWN[3..0], 5 = STOP
    static Lts countdown() {
        // TODO: build the LTS for COUNTDOWN(3). Draw it on paper first!
        //       Suggested numbering: 0 = before start, 1..4 = COUNTDOWN[3..0], 5 = STOP
        return new Lts("COUNTDOWN(3)");
    }

    static void animate(Lts lts, Random random) {
        System.out.printf("%s: %d states, alphabet %s, %s%n", lts.name, lts.stateCount(),
                lts.alphabet(), lts.isDeterministic() ? "deterministic" : "NON-deterministic");
        for (int run = 1; run <= 3; run++) {
            System.out.println("  trace: " + String.join(" -> ", lts.randomTrace(10, random)));
        }
        try {
            Set<Integer> stuck = lts.deadlockStates();
            System.out.println("  reachable states with no way out: " + (stuck.isEmpty() ? "none" : stuck));
        } catch (UnsupportedOperationException e) {
            System.out.println("  reachable states with no way out: (stretch (d) not implemented yet)");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        if (args.length > 0) {
            animate(Lts.fromAut(Path.of(args[0])), random);
            return;
        }
        for (Lts lts : List.of(lightSwitch(), trafficLight(), coin(), countdown())) {
            animate(lts, random);
        }
    }
}

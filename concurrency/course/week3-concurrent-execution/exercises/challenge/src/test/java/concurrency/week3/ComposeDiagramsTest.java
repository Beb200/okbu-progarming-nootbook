package concurrency.week3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import concurrency.week3.ComposeDiagrams.Diagram;
import concurrency.week3.ComposeDiagrams.Transition;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/*
 * Tests for the optional challenge. They fail until compose is done.
 * The expected state and transition counts were checked with the textbook's LTSA tool.
 *
 *     mvn test
 */
class ComposeDiagramsTest {

    static Diagram barista() { return new Diagram().add(0, "make", 1).add(1, "ready", 0); }
    static Diagram customer() { return new Diagram().add(0, "ready", 1).add(1, "pick_up", 0); }

    static Diagram compose(Diagram a, Diagram b) { return ComposeDiagrams.compose(a, b); }

    @Test
    void independentDiagramsInterleave() {
        Diagram talker = new Diagram().add(0, "think", 1).add(1, "talk", 2);
        Diagram itch = new Diagram().add(0, "scratch", 1);
        Diagram both = compose(talker, itch);
        assertEquals(6, both.stateCount());
        assertEquals(7, both.transitionCount());
    }

    @Test
    void twoSeparateTwoStepProcessesGiveNineStates() {
        Diagram printer = new Diagram().add(0, "print", 1).add(1, "eject", 2);
        Diagram scanner = new Diagram().add(0, "scan", 1).add(1, "save", 2);
        Diagram both = compose(printer, scanner);
        assertEquals(9, both.stateCount());
        assertEquals(12, both.transitionCount());
    }

    @Test
    void aSharedActionHappensOnceJointly() {
        Diagram cafe = compose(barista(), customer());
        assertEquals(4, cafe.stateCount());
        assertEquals(5, cafe.transitionCount());
        assertEquals(Set.of("make", "ready", "pick_up"), cafe.actions());
    }

    @Test
    void aSharedActionMustWaitForBothSides() {
        Diagram cafe = compose(barista(), customer());
        List<String> actions = new ArrayList<>();
        for (Transition t : cafe.enabled(0)) {
            actions.add(t.action());
        }
        assertEquals(List.of("make"), actions,
                "at the start only make is possible: the customer cannot pick up, and ready needs the barista");
    }

    @Test
    void eachWaitingForTheOtherGetsStuckImmediately() {
        Diagram p = new Diagram().add(0, "a", 1).add(1, "b", 0);
        Diagram q = new Diagram().add(0, "b", 1).add(1, "a", 0);
        Diagram both = compose(p, q);
        assertEquals(1, both.stateCount());
        assertEquals(0, both.transitionCount());
        assertEquals(Set.of(0), both.stuckStates());
    }

    @Test
    void theCafeNeverGetsStuck() {
        assertTrue(compose(barista(), customer()).stuckStates().isEmpty());
    }
}

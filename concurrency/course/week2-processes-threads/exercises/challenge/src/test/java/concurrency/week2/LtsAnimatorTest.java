package concurrency.week2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import concurrency.week2.LtsAnimator.Lts;
import concurrency.week2.LtsAnimator.Transition;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/*
 * Tests for the optional challenge, part (a). They fail until the TODOs are done.
 *
 *     mvn test
 */
class LtsAnimatorTest {

    @Test
    void enabledListsTheOutgoingTransitions() {
        Lts coin = LtsAnimator.coin();
        assertEquals(2, coin.enabled(0).size());
        assertEquals(List.of(new Transition("heads", 0)), coin.enabled(1));
    }

    @Test
    void enabledIsReadOnlySoCallersCannotChangeTheModel() {
        Lts coin = LtsAnimator.coin();
        List<Transition> fromStart = coin.enabled(0);
        assertEquals(2, fromStart.size());
        assertThrows(UnsupportedOperationException.class, () -> fromStart.add(new Transition("cheat", 0)),
                "enabled() should return a read-only view");
        assertEquals(2, coin.enabled(0).size(), "the model must be unchanged");
    }

    @Test
    void enabledIsEmptyForAStateWithNoWayOut() {
        Lts oneshot = new Lts("ONESHOT").add(0, "once", 1);
        assertTrue(oneshot.enabled(1).isEmpty());
    }

    @Test
    void alphabetContainsEveryLabelOnce() {
        assertEquals(Set.of("heads", "tails", "toss"), LtsAnimator.coin().alphabet());
        assertEquals(Set.of("red", "orange", "green"), LtsAnimator.trafficLight().alphabet());
    }

    @Test
    void coinIsNonDeterministicTheOthersAreNot() {
        assertFalse(LtsAnimator.coin().isDeterministic());
        assertTrue(LtsAnimator.trafficLight().isDeterministic());
        assertTrue(LtsAnimator.lightSwitch().isDeterministic());
    }

    @Test
    void randomTraceOfADeterministicCycleIsPredictable() {
        List<String> trace = LtsAnimator.trafficLight().randomTrace(6, new Random(42));
        assertEquals(List.of("red", "orange", "green", "orange", "red", "orange"), trace);
    }

    @Test
    void randomTraceEndsWithStopWhenNothingIsEnabled() {
        Lts oneshot = new Lts("ONESHOT").add(0, "once", 1);
        assertEquals(List.of("once", "STOP"), oneshot.randomTrace(10, new Random(1)));
    }

    @Test
    void randomTraceOnlyUsesEnabledTransitions() {
        Lts coin = LtsAnimator.coin();
        for (int seed = 0; seed < 20; seed++) {
            List<String> trace = coin.randomTrace(10, new Random(seed));
            assertEquals(10, trace.size());
            for (int i = 0; i < trace.size(); i++) {
                String expected = i % 2 == 0 ? "toss" : (trace.get(i).equals("heads") ? "heads" : "tails");
                assertEquals(expected, trace.get(i));
            }
        }
    }

    @Test
    void countdownHasTheRightShape() {
        Lts c = LtsAnimator.countdown();
        assertEquals(6, c.stateCount(), "COUNTDOWN(3) has 6 states");
        assertEquals(Set.of("beep", "start", "stop", "tick"), c.alphabet());
        assertTrue(c.isDeterministic());
        assertEquals(List.of("start"), c.enabled(0).stream().map(Transition::action).toList());
    }

    @Test
    void everyCountdownTraceStartsWithStartAndEndsInStop() {
        Lts c = LtsAnimator.countdown();
        for (int seed = 0; seed < 50; seed++) {
            List<String> trace = c.randomTrace(20, new Random(seed));
            assertFalse(trace.isEmpty(), "randomTrace returned no actions for COUNTDOWN(3)");
            assertEquals("start", trace.get(0));
            assertEquals("STOP", trace.get(trace.size() - 1));
            long ticks = trace.stream().filter("tick"::equals).count();
            assertTrue(ticks <= 3, "at most 3 ticks");
            if (trace.contains("beep")) assertEquals(3, ticks, "beep only after 3 ticks");
        }
    }
}

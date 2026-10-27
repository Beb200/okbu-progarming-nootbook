package concurrency.week2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import concurrency.week2.LtsAnimator.Lts;
import java.util.Set;
import org.junit.jupiter.api.Test;

/*
 * Tests for the optional challenge, part (d) (deadlockStates). These fail until you do part (d).
 *
 *     mvn test -Dtest=LtsAnimatorSearchTest
 */
class LtsAnimatorSearchTest {

    @Test
    void cyclicModelsHaveNoStuckStates() {
        assertEquals(Set.of(), LtsAnimator.trafficLight().deadlockStates());
        assertEquals(Set.of(), LtsAnimator.coin().deadlockStates());
    }

    @Test
    void findsTheStopStateOfOneshot() {
        assertEquals(Set.of(1), new Lts("ONESHOT").add(0, "once", 1).deadlockStates());
    }

    @Test
    void findsEveryStuckStateNotJustTheOneARandomWalkHits() {
        // From 0 the walk goes to 1 OR to 2; both are stuck.
        Lts lts = new Lts("FORK").add(0, "left", 1).add(0, "right", 2);
        assertEquals(Set.of(1, 2), lts.deadlockStates());
    }

    @Test
    void findsAStuckStateHiddenBehindALongPath() {
        // A random walk may or may not reach state 4 (it can keep going round the
        // 0-1 loop); an exhaustive search must always find it.
        Lts lts = new Lts("RARE").add(0, "loop", 1).add(1, "back", 0)
                .add(1, "a", 2).add(2, "b", 3).add(3, "c", 4);
        assertEquals(Set.of(4), lts.deadlockStates());
    }

    @Test
    void ignoresStuckStatesThatCannotBeReached() {
        // State 2 is stuck, but nothing leads to it from the start state 0.
        Lts lts = new Lts("ISLAND").add(0, "spin", 0).add(1, "jump", 2);
        assertEquals(Set.of(), lts.deadlockStates());
    }
}

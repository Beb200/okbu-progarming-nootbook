package concurrency.week1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import concurrency.week1.Interleavings.Kind;
import concurrency.week1.Interleavings.Step;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/*
 * Tests for Exercise 4. They fail until interleave(...) and simulate(...) are done.
 *
 *     mvn test
 */
class InterleavingsTest {

    // A process that does `x := x + delta` `times` times: R, W, R, W, ...
    static List<Step> process(String name, int delta, int times) {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            steps.add(new Step(name, Kind.READ, 0));
            steps.add(new Step(name, Kind.WRITE, delta));
        }
        return steps;
    }

    static Set<Integer> outcomes(List<Step> a, List<Step> b) {
        Set<Integer> results = new HashSet<>();
        for (List<Step> schedule : Interleavings.interleave(a, b)) {
            results.add(Interleavings.simulate(schedule, 0));
        }
        return results;
    }

    @Test
    void twoStepsEachGivesSixInterleavings() {
        assertEquals(6, Interleavings.interleave(process("1", +1, 1), process("2", -1, 1)).size());
    }

    @Test
    void fourStepsEachGivesSeventyInterleavings() {
        assertEquals(70, Interleavings.interleave(process("1", +1, 2), process("2", +1, 2)).size());
    }

    @Test
    void interleavingWithAnEmptyProcessIsTheOtherProcess() {
        List<Step> a = process("1", +1, 1);
        assertEquals(List.of(a), Interleavings.interleave(a, List.of()));
        assertEquals(List.of(a), Interleavings.interleave(List.of(), a));
    }

    @Test
    void everyInterleavingIsDifferentAndKeepsEachProcessInOrder() {
        List<Step> a = process("1", +1, 2), b = process("2", -1, 2);
        List<List<Step>> all = Interleavings.interleave(a, b);
        assertEquals(all.size(), new HashSet<>(all).size(), "duplicate interleavings");
        for (List<Step> schedule : all) {
            assertEquals(a.size() + b.size(), schedule.size());
            assertEquals(a, schedule.stream().filter(s -> s.proc().equals("1")).toList());
            assertEquals(b, schedule.stream().filter(s -> s.proc().equals("2")).toList());
        }
    }

    @Test
    void serialScheduleGivesTheExpectedAnswer() {
        Step r1 = new Step("1", Kind.READ, 0), w1 = new Step("1", Kind.WRITE, +1);
        Step r2 = new Step("2", Kind.READ, 0), w2 = new Step("2", Kind.WRITE, -1);
        assertEquals(0, Interleavings.simulate(List.of(r1, w1, r2, w2), 0));
        assertEquals(10, Interleavings.simulate(List.of(r1, w1, r2, w2), 10));
    }

    @Test
    void bothReadBeforeEitherWritesLosesAnUpdate() {
        Step r1 = new Step("1", Kind.READ, 0), w1 = new Step("1", Kind.WRITE, +1);
        Step r2 = new Step("2", Kind.READ, 0), w2 = new Step("2", Kind.WRITE, -1);
        assertEquals(-1, Interleavings.simulate(List.of(r1, r2, w1, w2), 0));
        assertEquals(1, Interleavings.simulate(List.of(r1, r2, w2, w1), 0));
    }

    @Test
    void lectureExampleHasThreePossibleResults() {
        assertEquals(Set.of(-1, 0, 1), outcomes(process("1", +1, 1), process("2", -1, 1)));
    }

    @Test
    void twoLikesAtOnceCanLoseOne() {
        assertEquals(Set.of(1, 2), outcomes(process("1", +1, 1), process("2", +1, 1)));
    }

    @Test
    void twoIncrementsEachNeverDropsBelowTwo() {
        Set<Integer> results = outcomes(process("1", +1, 2), process("2", +1, 2));
        assertEquals(Set.of(2, 3, 4), results);
        assertTrue(results.contains(4), "the serial schedule must give 4");
    }
}

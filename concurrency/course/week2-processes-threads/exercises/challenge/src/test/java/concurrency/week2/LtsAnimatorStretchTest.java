package concurrency.week2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import concurrency.week2.LtsAnimator.Lts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/*
 * Tests for the optional challenge, part (c) (fromAut). These fail until you do part (c).
 * To run only the part (a) tests:   mvn test -Dtest=LtsAnimatorTest
 */
class LtsAnimatorStretchTest {

    static Path write(Path dir, String text) throws IOException {
        Path aut = dir.resolve("model.aut");
        Files.writeString(aut, text);
        return aut;
    }

    @Test
    void readsAnAutFileExportedFromLtsa(@TempDir Path dir) throws Exception {
        Lts coin = Lts.fromAut(write(dir, """
                des(0,4,3)
                (0,toss,2)
                (0,toss,1)
                (1,tails,0)
                (2,heads,0)
                """));
        assertEquals(3, coin.stateCount());
        assertEquals(Set.of("heads", "tails", "toss"), coin.alphabet());
        assertFalse(coin.isDeterministic());
    }

    @Test
    void acceptsQuotedLabels(@TempDir Path dir) throws Exception {
        assertEquals(Set.of("on", "off"), Lts.fromAut(write(dir, "des(0,2,2)\n(0,\"on\",1)\n(1,\"off\",0)\n")).alphabet());
    }

    @Test
    void usesTheStartStateAndAllStatesFromTheHeader(@TempDir Path dir) throws Exception {
        // start in state 1; state 2 has no transitions at all but still exists
        Lts lts = Lts.fromAut(write(dir, "des(1,2,3)\n(1,go,0)\n(0,back,1)\n"));
        assertEquals(1, lts.startState());
        assertEquals(3, lts.stateCount());
        assertEquals(List.of("go", "back", "go"), lts.randomTrace(3, new Random(7)));
    }

    @Test
    void rejectsAFileWithoutAHeader(@TempDir Path dir) throws Exception {
        Path aut = write(dir, "(0,toss,1)\n(1,heads,0)\n");
        assertThrows(IOException.class, () -> Lts.fromAut(aut), "a missing des(...) header must be rejected");
    }

    @Test
    void rejectsALineThatIsNotATransition(@TempDir Path dir) throws Exception {
        Path aut = write(dir, "des(0,2,2)\n(0,on,1)\nthis is not a transition\n(1,off,0)\n");
        assertThrows(IOException.class, () -> Lts.fromAut(aut), "unreadable lines must not be skipped silently");
    }

    @Test
    void rejectsATransitionCountThatDisagreesWithTheHeader(@TempDir Path dir) throws Exception {
        Path aut = write(dir, "des(0,3,2)\n(0,on,1)\n(1,off,0)\n");
        assertThrows(IOException.class, () -> Lts.fromAut(aut), "header says 3 transitions but there are 2");
    }

    @Test
    void rejectsAnEmptyLabel(@TempDir Path dir) throws Exception {
        Path aut = write(dir, "des(0,1,2)\n(0,,1)\n");
        assertThrows(IOException.class, () -> Lts.fromAut(aut));
    }

    @Test
    void rejectsAStateNumberOutsideTheDeclaredRange(@TempDir Path dir) throws Exception {
        Path aut = write(dir, "des(0,1,2)\n(0,on,5)\n");
        assertThrows(IOException.class, () -> Lts.fromAut(aut), "state 5 does not exist in a 2-state model");
    }
}

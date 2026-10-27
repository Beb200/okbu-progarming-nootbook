# Week 2 Exercises: State Diagrams and Java Threads

**Time:** about 60 minutes in class, after the talk. Work in pairs if you like. The times below are a guide.
**Setup:** Java 21+ and Maven (see [SETUP.md](../../SETUP.md)).
**Hand in:** your answers (notes or photos of paper work are fine) and your edited `.java` files.

| # | Exercise | Kind | Time |
|---|---|---|---|
| D1 | A music player's state diagram | paper | 10 min |
| J1 | Four ways to start a thread | Java | 15 min |
| J2 | Watching a thread's life | Java | 15 min |
| J3 | From state diagram to thread: a phone's auto-lock | Java | 20 min |

The programs are in the Maven project `starter/` (sources in `starter/src/main/java/concurrency/week2/`). Run them from that folder:

| Program | Command |
|---|---|
| ThreadBasics | `mvn -q compile exec:exec -Dmain=ThreadBasics` |
| ThreadStates | `mvn -q compile exec:exec -Dmain=ThreadStates` |
| AutoLockTimer | `mvn -q compile exec:exec -Dmain=AutoLockTimer -Dargs="5"` |

---

## D1: A music player's state diagram (10 min, paper)

A music player starts STOPPED. From STOPPED you can only `play`. While PLAYING you can `pause`, `skip` (it keeps playing) or `stop`. While PAUSED you can `play` or `stop`.

**(a)** Draw its state diagram: circles for the three states, arrows labelled with actions. Mark the start state.

**(b)** Which of these are traces? Explain each in a few words.
(i) `play pause play stop play`  (ii) `pause play`  (iii) `play skip skip pause stop`  (iv) `play stop pause`

## J1: Four ways to start a thread (15 min, `ThreadBasics.java`)

Complete the TODOs so that four threads named `greeter`, `countdown`, `lambda` and `virtual` each print four lines at the same time, and `main` prints `all done` only after all four have finished. Tutorial section B.2 shows all four ways.

**(a)** Run it three times. Is the output in the same order each time? Why or why not?

**(b)** Change `start()` to `run()` (keep the joins) and run it. What happens to the order, and to the thread names printed? Why?

## J2: Watching a thread's life (15 min, `ThreadStates.java`)

The watcher in `main` (already written) prints each state change of a "worker" thread. Complete `workerSteps` so the output shows, in order: `NEW, RUNNABLE, TIMED_WAITING, WAITING, BLOCKED, RUNNABLE, TERMINATED`. The hints in the code tell you which method causes each state.

An extra `RUNNABLE` line between two states is normal: the thread runs briefly between steps and the watcher sometimes catches it. `getState()` is a *snapshot*, not a log of every change. You are assessed on your code and your explanations, not on matching an exact printout.

Then fill in this table, using the life-cycle diagram in tutorial section B.4:

| `Thread.State` printed | What line of code caused it | State in the life-cycle diagram |
|---|---|---|

## J3: From state diagram to thread (20 min, `AutoLockTimer.java`)

A phone locks its screen after `T` seconds without a touch, and every touch restarts the countdown. Pressing Enter is a touch. Here is the diagram for `T = 3`:

![State diagram for the phone auto-lock: 5 states, starting in 3 s LEFT. Transitions: in 3 s LEFT, action touch leads back to 3 s LEFT; from 3 s LEFT, action tick leads to 2 s LEFT; from 2 s LEFT, action touch leads to 3 s LEFT; from 2 s LEFT, action tick leads to 1 s LEFT; from 1 s LEFT, action touch leads to 3 s LEFT; from 1 s LEFT, action tick leads to 0 s LEFT; from 0 s LEFT, action touch leads to 3 s LEFT; from 0 s LEFT, action lock leads to LOCKED. LOCKED has no outgoing transitions (the process stops).](../figures/autolock-named.png)

Complete the three TODOs: `touch()`, the countdown loop in `run()`, and a touch that arrives just as the countdown reaches 0. The countdown example in tutorial section B.6 shows the approach. The difference is that here `interrupt()` means "wake up and start over", not "stop". Test both endings: let it lock without touching it, then run it again and press Enter a few times part-way through.

**(a)** Which thread runs the countdown? Which thread notices that you pressed Enter, and how does it tell the other one?

**(b)** In the diagram, "0 s LEFT" has both a `lock` arrow and a `touch` arrow. What does your program do if Enter is pressed at the exact moment the count reaches 0? What happens to a touch that arrives just *after* the screen has locked?

---

## Optional extras (not needed in class)

- **J1 extra:** remove the `join()` loop and run `ThreadBasics` again. What changes, and why? (Hint: virtual threads don't keep a program alive.)
- **Challenge: a state diagram in Java.** The Maven project `challenge/` asks you to store a state diagram as a data structure, generate random traces from it, load one from a file (`models/countdown.aut`), and search it for states with no way out. `mvn test` in `challenge/` checks your work.
- **Virtual threads at scale.** In the Maven project `../demos/`, compare `mvn -q compile exec:exec -Dmain=VirtualThreadsDemo -Dargs="2000"` with `-Dargs="2000 platform"` (the same 2,000 one-second waits on virtual threads and on a pool of 200 ordinary threads). Explain the difference.

# Week 1 Tutorial: What Is Concurrency, and Why Is It Hard?

*Handout for CS II, Week 1. Follow along during the short talk, then use it as a reference for the exercises. Based on Magee & Kramer, Concurrency: State Models & Java Programs, ch. 1 [MK06]. Keys in brackets refer to [REFERENCES.md](../REFERENCES.md).*

**This week (about 100 minutes in class):** a 30-minute talk, then about 60 minutes of exercises (`exercises/README.md`).

**By the end you should be able to:**

1. Tell a sequential program from a concurrent one, and concurrency from parallelism.
2. Give four reasons programs are written concurrently.
3. Explain what a race condition is, and find every possible result of a small race by listing interleavings.
4. Draw a simple state diagram of something real.

---

## 1. One thing at a time, or many?

Every program you have written so far has been **sequential**: there is a single *thread of control*. Execution starts at `main`, and at any moment you can point to exactly one line that is "running now."

Most of those programs were also **deterministic**: given the same inputs, they produce the same result every time. That is a separate property. A sequential program can still behave differently from run to run if it reads the clock, uses random numbers, or depends on files, the network or the user. Concurrency adds a new source of variation that is not an input at all: the order in which threads happen to run.

A **concurrent** program has **several threads of control** in progress at once, each with its own "current line," often sharing data. You use them all day: a music app plays audio, downloads the next song and responds to your taps at the same time; a web server handles thousands of users at once.

**Concurrency vs parallelism** [Pik12]:

- **Concurrency** is about *structure*: several activities are *in progress* at the same time. On one CPU core they take turns very quickly. This taking of turns is called **interleaving**.
- **Parallelism** is about *execution*: several things happen at the same instant, on several cores.

```
One core (concurrency by interleaving):    Two cores (parallelism):
core 1: A A B B A B A A B                  core 1: A A A A A
                                           core 2: B B B B B
```

A concurrent program must be correct under *every* interleaving, whether it runs on one core or many.

## 2. Why write concurrent programs?

1. **Performance.** Since about 2005, processors have gained more *cores* rather than much more speed [Sut05]. A sequential program uses only one.
2. **Throughput.** While one task waits (for the network, the disk or the user), another can make progress.
3. **Responsiveness.** An app's screen must keep responding while slow work happens in the background.
4. **Structure.** Some systems really are several independent activities; one thread per activity can be the clearest design.

## 3. Why concurrency is hard: race conditions

Here is the simplest concurrent program [MK06]. A shared variable `x` starts at 0:

```
process 1:   x := x + 1
process 2:   x := x - 1
```

`x := x + 1` is **not one step** for the computer. It is two **atomic** steps (steps that cannot be interrupted part-way):

- **R**: *read* `x` into a private register
- **W**: *write* the register's value plus one back to `x`

Each process does its own R before its own W, but the two processes can interleave in any way. There are six possible orders [MK06]:

| | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| step 1 | R1 | R1 | R1 | R2 | R2 | R2 |
| step 2 | W1 | R2 | R2 | R1 | R1 | W2 |
| step 3 | R2 | W1 | W2 | W1 | W2 | R1 |
| step 4 | W2 | W2 | W1 | W2 | W1 | W1 |
| **final x** | **0** | **−1** | **1** | **−1** | **1** | **0** |

In order 2, process 1 reads 0, process 2 reads 0, process 1 writes 1, and process 2 writes −1. Process 1's update is **lost**.

This is a **race condition**: the result depends on the timing of the threads. The program is **non-deterministic**: same program, same input, different results.

**Why testing is not enough.** The bad orders may be rare. A test can pass a thousand times and fail the next, or only on a machine with more cores. Adding print statements or using a debugger changes the timing, so the bug can disappear while you look for it. And the number of possible orders grows explosively with program size: two processes of 20 steps each have about 138 billion interleavings.

### When it goes wrong

These are simplified summaries; each accident had several causes, and a concurrency bug was one of them.

- **Therac-25 (1985–1987).** A radiation-therapy machine gave six patients massive overdoses; some died. One of several software faults was a race between concurrent tasks: when an experienced operator edited the settings quickly (within about 8 seconds), the task setting up the machine did not notice the edit [LT93].
- **Mars Pathfinder (1997).** The spacecraft kept resetting itself because a high-priority task was stuck waiting for a lock held by a low-priority task. Engineers fixed it with a patch sent from Earth [Ree97].
- **Northeast blackout (2003).** About 50 million people lost power. Among several causes, the control-room alarm system failed silently [TF04]; the vendor later traced that failure to a race condition [Reg04].

## 4. Thinking in states

Engineers often sketch a simplified picture of a system before building it, leaving out everything except what matters for the question they are asking. For concurrency the question is usually *"what can happen next, and in what order?"*, and a good picture for that is a **state diagram**:

- **states** (circles): the situations the system can be in; one is the start state;
- **transitions** (arrows): each labelled with the **action** that moves the system from one state to the next.

Here is a ride-share driver's status, as the dispatch system sees it:

![State diagram for a ride-share driver: 4 states, starting in OFFLINE. Transitions: from OFFLINE, action go_online leads to AVAILABLE; from AVAILABLE, action go_offline leads to OFFLINE; from AVAILABLE, action accept_ride leads to EN_ROUTE; from EN_ROUTE, action rider_cancels leads to AVAILABLE; from EN_ROUTE, action pick_up leads to ON_TRIP; from ON_TRIP, action drop_off leads to AVAILABLE.](figures/rideshare-driver.png)

A **trace** is any sequence of actions you can follow from the start state, for example `go_online accept_ride pick_up drop_off`. The diagram also shows what *cannot* happen: `go_online pick_up` is impossible, because a driver must accept a ride first.

Notice what the diagram leaves out: location, fare, ratings, the car. For deciding *what a driver can do next*, none of that matters. Drawing the diagram also forces decisions the code would otherwise make by accident. In this one, for example, a driver cannot go offline in the middle of a trip. Is that what we want?

The textbook goes further and writes such diagrams in a small language that a computer can check automatically, trying every possible order of events. In this course we stay with diagrams drawn by hand, which are enough to reason about the programs we write.

---

## Key terms

| Term | Meaning |
|---|---|
| sequential program | one thread of control |
| concurrent program | several threads of control in progress at once |
| parallelism | doing several things at the same instant, on several cores |
| deterministic | always gives the same result from the same inputs (including the clock, random numbers, files and user input) |
| non-deterministic | may give different results from the same inputs, for example because threads interleave differently |
| interleaving | the order in which the atomic steps of different threads happen to run |
| atomic action | a step that happens completely or not at all |
| race condition | a bug where the result depends on the timing of threads |
| state diagram | states (circles) joined by transitions (arrows) labelled with actions |
| trace | a sequence of actions that can happen, starting from the start state |

## Check your understanding

1. Can a single-core laptop run a concurrent program? A parallel one?
2. Two threads each run `count = count + 1` once, starting from 5. What final values are possible?
3. Why might a race condition never show up on your laptop but show up on a server?
4. In the ride-share diagram, is `go_online accept_ride rider_cancels go_offline` a trace? Why or why not?

<details>
<summary>Answers</summary>

1. Concurrent, yes (the threads interleave on the one core). Parallel, no.
2. 6 or 7. It is 7 only if one thread finishes both its read and its write before the other reads.
3. The bad interleavings may need timing that your laptop rarely produces; a server has more cores, a different load and different scheduling.
4. Yes: OFFLINE → AVAILABLE → EN_ROUTE → AVAILABLE → OFFLINE, and every step follows an arrow.

</details>

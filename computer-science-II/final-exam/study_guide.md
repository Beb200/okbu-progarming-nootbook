# CIS 3723 - Computer Science II: Final Exam Study Guide (Under Construction)

> **Under construction.** This guide is still being written, and more will be added before the final. Check back for the latest version. Sections marked *(to be added)* are coming.

---

## The exam

- **Format:** pencil and paper, in class, **2 hours**.
- **Notes:** you may bring **one page of notes** (front and back). No other materials or devices.
- **Questions:** you trace algorithms by hand, read short Java programs and explain what they do, list possible orders and outputs, and draw and read state diagrams. **You will not be asked to write code.**
- **Explain your work:** show each step, not just the final answer. Partial credit is given for correct steps.
- **Practice:** the in-class **practice exam** has the same kinds of questions as the final, with different data. Its answers are at the end of `practice_exam.md`.

### Conventions on the exam

The cover page of the final lists these, the same as the practice exam:

- **Game trees:** MAX nodes are triangles pointing up, MIN nodes are triangles pointing down, and leaves are squares. Every score is from **MAX's point of view**. Children are searched **left to right**. Nodes are named A, B, C, ... level by level; leaves are numbered L1, L2, ... from left to right.
- **Alpha-beta:** cross out every leaf that is never examined, and every node that is never called (with everything below it). Write **≥** in front of the value of a MAX node that stopped early, and **≤** for a MIN node.
- **Tic-tac-toe:** X is MAX and O is MIN. A finished game scores **+1** if X wins, **0** for a draw and **−1** if O wins. Squares are numbered 0–8, left to right and top to bottom.
- **Shared variables:** an assignment such as `x := x + 1` is two atomic steps: **R** (read `x` into a private register), then **W** (write the new value back).
- **State diagrams:** circles are states, arrows are labelled with actions, and an arrow from nowhere marks the start state. An action in two processes' diagrams is **shared**: both must do it at the same moment.
- **Java:** the code is Java 21. Make no assumptions about how fast any thread runs.


## Part 1: Game-tree search

### Minimax

**You should be able to:**

- Compute the value of every node in a game tree, from the bottom up: a MAX node takes the **largest** of its children's values, a MIN node the **smallest**.
- Say which move the player at the root should make, and mark the **principal variation**: the path both players follow if both play perfectly.
- Explain why MAX usually can't reach the largest leaf: MIN chooses too, and minimax assumes MIN plays its best.
- Give the order in which nodes **return** their values. Minimax is depth-first, so a node returns only after all its children have.
- Handle a tree whose root is a **MIN** node. The algorithm doesn't change, and scores are still from MAX's point of view.
- Draw and score a small **tic-tac-toe** game tree, and explain why the best move works (for example, a move that makes two threats at once).

**Common mistakes:**

- Taking the max (or the min) at every level. Check which player chooses at each level.
- Listing nodes in the order they are *named* (A, B, C, ...) instead of the order they *return*.
- Scoring a tic-tac-toe position +1 because X *could* win there. A position's value assumes O also plays its best reply.

**Review:** the minimax in-class practice, Problems 1–8.

### Alpha-beta pruning

**You should be able to:**

- Explain **α** (the best value MAX is already guaranteed on the path from the root; it only goes up) and **β** (the best, lowest value MIN is already guaranteed; it only goes down).
- Apply the cutoff rule: as soon as **α ≥ β** at a node, skip its remaining children.
- Run alpha-beta on a tree: cross out every leaf that is never examined and every node that is never called, and write each node's value, with ≥ or ≤ for any node that stopped early.
- Fill in a table of the **α and β each node receives** from its parent. A child receives its parent's *current* α and β, which may have changed since the parent was called.
- Explain a cutoff in words: which player already has a choice at least as good, and where.
- Explain why skipping is **safe**: no value of a skipped leaf could change the root's value. Alpha-beta always returns the same root value as minimax.
- Say where cutoffs happen when MIN is at the root (at MAX nodes, caused by β).
- Explain how **move order** affects pruning: searching the best move first prunes the most.

**Common mistakes:**

- Pruning under the first child of the root. Nothing can be skipped until a player has a guaranteed value.
- Writing a node that stopped early as if its value were exact. It is only a bound (≥ or ≤).
- Stopping a MAX node on a *small* value. A MAX node stops only on a value ≥ β; a MIN node stops only on a value ≤ α.

**Review:** the alpha-beta slides (`alphabeta.pptx`) and the alpha-beta in-class practice, Problems 1–9.


## Part 2: Concurrency (weeks 1–3)

### Week 1: what concurrency is, and why it is hard

**You should be able to:**

- Tell a **sequential** program (one thread of control) from a **concurrent** one (several in progress at once), and **concurrency** from **parallelism**. Concurrency is about structure: several activities are *in progress* at the same time. On one core they take turns (**interleave**); on several cores some of them may also run in parallel. Parallelism means actually doing several things at the same instant, on several cores.
- Give the four reasons for writing concurrent programs: performance, throughput, responsiveness and structure.
- Split an assignment such as `x := x + 1` into its atomic **R** and **W** steps.
- List **every interleaving** of two processes' steps (each keeps its own order), and the final value of a shared variable for each.
- Explain a **race condition**: the result depends on the timing of the threads, that is, on the order their steps happen to run in.
- Explain a **lost update**, the race we studied: both processes read the same old value, so the second write overwrites the first.
- Explain why testing can't prove there is no race: the bad orders may be rare, and may show up only on another machine or under a different load.
- Read and draw a simple **state diagram**, and decide whether a sequence of actions is a **trace** (and if not, where it gets stuck).

**Practise:** two processes of two steps each have **6** orders. Practise listing them in a fixed pattern so you don't miss one: start with every order that begins R1, then every order that begins R2.

**Review:** Week 1 tutorial, sections 1–4; Week 1 exercises 1, 2 and 4.

<!-- pagebreak -->

### Week 2: processes and threads

**You should be able to:**

- Describe a **process** as a sequence of actions, and read its state diagram.
- Recognize **non-deterministic choice**: two arrows with the **same** action leave one state. The diagram doesn't say which way it goes; in the course's words, the process itself decides, and nobody outside can control it. (Arrows with the same label leaving *different* states are not a choice.)
- Explain that an operating-system process has **one stack per thread**, and all its threads **share the heap**.
- Recognize the four ways to give a thread the code it runs: subclass `Thread` and override `run()`; pass a `Runnable` object to `new Thread(...)`; pass a lambda (a short way to write a `Runnable`); or create a virtual thread with `Thread.ofVirtual()`.
- Explain the difference between `t.start()` (creates a new thread, which calls `run()`) and `t.run()` (an ordinary method call on the current thread: nothing runs concurrently).
- Explain what `t.join()` does: the calling thread waits until `t` has finished.
- Give a thread's `Thread.State` at a point in a program:

| `Thread.State` | When |
|---|---|
| `NEW` | created, not yet started |
| `RUNNABLE` | running or ready to run |
| `BLOCKED` | waiting to enter a `synchronized` block |
| `WAITING` | waiting with no time limit, for example in `join()` |
| `TIMED_WAITING` | waiting with a time limit, for example in `sleep(ms)` |
| `TERMINATED` | `run()` has returned |

- Explain `interrupt()`: a **request**, not a forced stop. It sets a flag in the thread. If the thread is sleeping in `sleep()` or waiting in `join()`, its wait ends with an `InterruptedException`. What happens next is up to the thread's own `catch` code: the countdown timer (Week 2 tutorial, B.6) stops and returns, but the auto-lock timer (Week 2 exercise J3) starts its count over.
- Explain what **virtual threads** are good for: they make *waiting* cheap, but they don't make *computing* faster.

**Review:** Week 2 tutorial, Parts A and B; Week 2 exercises D1, J1 and J2.


### Week 3: concurrent execution

**You should be able to:**

- List every possible order of the actions of two **independent** threads, and decide whether a given output is possible.
- Use `join()` when deciding what can be printed when. Code after `t.join()` runs only after `t` has finished; code before it may run at any point.
- Explain why we make **no assumptions about speed**: an order you never saw in testing is not an impossible order.
- Step two state diagrams together when they share an action: a **shared** action needs both processes ready at the same moment, and whoever is ready first waits. Decide whether a sequence can happen, and where it gets stuck if not.
- Explain **tasks** (`Runnable`, or `Callable` if it returns a result) versus the **threads** that run them, and what an `ExecutorService` does.
- Explain what `submit` returns (a `Future`, an "order ticket" for the result) and what `future.get()` does (waits for that task's result).
- Predict roughly how long a batch of tasks takes. With a pool of *n* threads, at most *n* tasks run at once, so the tasks run in rounds. For example, 5 one-second tasks on a pool of 2 threads take about 3 seconds (2 + 2 + 1).
- Spot the classic mistake: calling `get()` right after each `submit()` makes the tasks run one at a time. The rule is **submit everything first, then collect**.
- Choose an executor: a fixed pool for computing tasks; one **virtual thread per task** for tasks that mostly wait (network, disk).

**Review:** Week 3 tutorial, Parts A and B; Week 3 exercises D1, D2, J1 and J2.

---

## Not on the final

- Writing code. You read, trace and explain Java, but you don't write it.
- Running times or big-O notation.
- The FSP modelling language and the LTSA tool.
- Concurrency weeks 4–6: `synchronized`, locks, atomic variables, monitors and deadlock.

## How to study

- Work the **practice exam** on paper first, then check the answers at the end of `practice_exam.md`.
- Redo the in-class practice problems for minimax and alpha-beta, and the paper exercises from concurrency weeks 1–3, without looking at your earlier answers.
- Answer the "Check your understanding" questions at the end of each week's concurrency tutorial.
- Build your page of notes as you study. Good candidates: the cutoff rule and what α and β mean, the `Thread.State` table, and the "submit everything first, then collect" rule.

## Still to come *(to be added)*

- Review session details.
- More practice problems.
- Answers to questions students ask while studying.

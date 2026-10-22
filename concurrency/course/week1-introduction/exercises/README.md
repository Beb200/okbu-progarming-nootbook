# Week 1 Exercises: Races, Interleavings and Models

**Estimated time:** 2–3 hours. **Core** parts are required; **stretch** parts are optional challenges.
**Setup:** Java 21+ and Maven (see [SETUP.md](../../SETUP.md)).
**Submit:** your `starter/` project (zip it after `mvn clean`) and a short document (PDF or Markdown) with your answers, tables and drawings.

**How to run:** every program is in the Maven project in `starter/`. From that folder:

| Program | Command |
|---|---|
| LikeCounter | `mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="4 500000"` |
| Downloads | `mvn -q compile exec:exec -Dmain=Downloads` |
| Interleavings | `mvn -q compile exec:exec -Dmain=Interleavings` |
| Tests for Exercise 4 | `mvn test` |

The source files are in `starter/src/main/java/concurrency/week1/`. You can also open `starter/` in your IDE as a Maven project. See [SETUP.md](../../SETUP.md).

---

## Exercise 1: Interleavings by hand (core, paper)

Shared variable `x` starts at 0. Each line `x := x ± 1` is two atomic steps: **R** (read `x` into a private register) then **W** (write register ± 1 back to `x`).

**(a)** Process 1 does `x := x + 1`; process 2 does `x := x - 1`. Without looking at the tutorial, list all interleavings of R1, W1, R2, W2 and the final value of `x` for each. How many are there?

**(b)** Now both processes do `x := x + 1`. What final values are possible? Which interleavings give each?

**(c)** Suppose process 1 has *m* atomic steps and process 2 has *n*. Explain why the number of interleavings is the binomial coefficient C(*m*+*n*, *m*). (Hint: an interleaving is determined by choosing *which* of the *m*+*n* time slots belong to process 1.) Compute it for *m* = *n* = 4 and for *m* = *n* = 20.

**(d)** Three processes each do `x := x + 1` once, starting from 0. What is the smallest possible final value? Describe an interleaving that produces it.

## Exercise 2: Lost likes (core: run and explain)

`LikeCounter` simulates users liking a post at the same time. Each user is a thread that runs `likes = likes + 1` many times.

**(a)** Run `LikeCounter` with no arguments (2 users × 1,000,000 likes): `mvn -q compile exec:exec -Dmain=LikeCounter`. Copy the output into your answers. How many likes were lost?

**(b)** Run it with `-Dargs="1 2000000"`, `"2 1000000"`, `"4 500000"` and `"8 250000"`. The expected total is always 2,000,000. Record the results in a table:

| users | likes per user | counted (trial 1) | counted (trial 2) | lost (average) |
|---|---|---|---|---|

**(c)** Why is nothing lost with 1 user? Using the R/W steps from Exercise 1, explain *exactly* how one like gets lost when there are 2 users.

**(d)** Your friend says, "I ran it with 2 users and got exactly 2,000,000, so the code is fine." What is wrong with this reasoning?

**(e) (stretch)** Make the counter correct. Look up `java.util.concurrent.atomic.AtomicInteger` and change `likes` and `like()` to use it. Run part (b) again. (We study why this works, and other fixes, in Week 4.)

## Exercise 3: Why bother? Concurrent downloads (core)

`Downloads` simulates a phone app downloading five files. Each download mostly *waits* (it calls `Thread.sleep`). The helper `runAllConcurrently` starts each task on its own thread and waits for all of them.

**(a)** Run it as given and record the sequential time.

**(b)** Complete **TODO 2**: time the same downloads with `runAllConcurrently`. Record the time. Explain why the sequential time is close to the **sum** of the download times and the concurrent time is close to the **maximum**.

**(c)** Complete **TODO 3**: make a `List<Runnable>` of `crunch(...)` tasks (these *compute* instead of wait) and time them both ways. Try 1, 2, *cores* and 2 × *cores* tasks, where *cores* is printed by the program. Record a table of sequential time, concurrent time and speed-up (sequential ÷ concurrent).

**(d)** Why is there almost no speed-up for 1 crunch task? Why does the speed-up stop growing once the number of tasks exceeds the number of cores? Use the words *concurrency* and *parallelism* in your answer.

## Exercise 4: All the interleavings, in Java (core)

`Interleavings` represents each process as a list of atomic `Step`s. Implement:

- `interleave(a, b)`: returns **every** interleaving of lists `a` and `b` that keeps each list's own order. Use recursion: the first step of an interleaving is either `a`'s first step or `b`'s first step.
- `simulate(schedule, initial)`: runs one interleaving and returns the final `x`.

**(a)** Run `mvn test`: all 9 tests in `InterleavingsTest` should pass. Then run the program and check that part (a) of the output matches the table in the tutorial (six interleavings, final values −1, 0, 1).

**(b)** Before running part (c) of `main` (each process increments **twice**), predict how many interleavings there are using Exercise 1(c). Then run it. What final values are possible, and how often does each occur?

**(c) (stretch)** Add a third process and a method `interleave3`, or generalize to a list of processes. Use it to check your answer to Exercise 1(d).

## Exercise 5: Abstraction: model a food-delivery order (core, paper)

In class we modelled a ride-share driver. Now model something new. A food-delivery app tracks each order. A customer places an order. The restaurant either accepts or rejects it. An accepted order is prepared until the food is ready, then picked up by a courier and delivered. The customer may cancel at any time **before** the courier picks the food up. Once an order is delivered, rejected or cancelled, it is finished.

**(a)** Draw a state machine (circles = states, labelled arrows = actions) for the life of one order. Name each state and number the start state 0.

**(b)** List at least five details of the real system that your model leaves out. Why is it OK to leave them out *for the purpose of deciding what can happen next to an order*?

**(c)** Write down two **traces** (sequences of actions from the start state) that your model allows, and one sequence of actions that it does **not** allow.

**(d)** Your model has a state with no way out. What does it mean for an order? Is that a problem here? (Next week LTSA will call this a "potential deadlock".)

**(e)** Can a customer cancel after the courier has picked up the food? Should they be able to? This is a *requirements* question: building the model forces you to decide it.

## Exercise 6: Concurrency around you (core, short writing)

Pick an app or device you use (not one from the tutorial table). In 150–250 words: list at least three activities in it that must happen concurrently, say which reason(s) for concurrency from the tutorial apply, and describe one thing that could go wrong if two of those activities interfered with each other.

# Week 1 Exercises: Races and State Diagrams

**Time:** about 60 minutes in class, after the talk. Work in pairs if you like. The times below are a guide.
**Setup:** Java 21+ and Maven (see [SETUP.md](../../SETUP.md)). The first Maven run downloads some files; start Exercise 2's first command while you work on Exercise 1.
**Hand in:** your answers (a page of notes, a photo of paper work is fine) and your edited `Downloads.java`.

| # | Exercise | Kind | Time |
|---|---|---|---|
| 1 | Interleavings by hand | paper | 15 min |
| 2 | Lost likes | run and explain | 20 min |
| 3 | Concurrent downloads | one line of code | 15 min |
| 4 | A state diagram | paper | 10 min |

The programs are in the Maven project `starter/`. Run them from that folder:

```
cd starter
mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="4 500000"
```

---

## Exercise 1: Interleavings by hand (15 min, paper)

A shared variable `x` starts at 0, and **both** processes do `x := x + 1`. Each one is two atomic steps: **R** (read `x` into a register) then **W** (write register + 1 back to `x`). Use R1, W1 for process 1 and R2, W2 for process 2.

**(a)** List all six orders of R1, W1, R2, W2 (each process keeps its own R before W) and the final value of `x` for each. Which final values are possible?

**(b)** In words: in the orders that give the "wrong" answer, what happened to one of the updates?

**(c)** *If you have time:* three processes each do `x := x + 1` once. What is the *smallest* possible final value? Give an order that produces it.

## Exercise 2: Lost likes (20 min, run and explain)

`LikeCounter` simulates users liking a post at the same time. Each user is a thread that runs `likes = likes + 1` many times. The expected total is always 2,000,000.

**(a)** Run it with each setting below and fill in the table (the program runs 5 trials; copy two):

```
mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="1 2000000"
mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="2 1000000"
mvn -q compile exec:exec -Dmain=LikeCounter -Dargs="4 500000"
```

| users | counted (trial 1) | counted (trial 2) | roughly how many lost? |
|---|---|---|---|

**(b)** Why is nothing lost with 1 user? Using R and W steps, explain exactly how one like gets lost with 2 users.

**(c)** A friend says: "I ran it once and got exactly 2,000,000, so the code is fine." What is wrong with that reasoning?

## Exercise 3: Concurrent downloads (15 min, one line of code)

`Downloads` simulates a phone app downloading five files. Each download mostly *waits*. Its helper `runAllConcurrently` runs every task on its own thread (you'll see how in Week 2).

**(a)** Complete the one `TODO` in `main` so the same downloads are also timed using `runAllConcurrently`. Run it:

```
mvn -q compile exec:exec -Dmain=Downloads
```

Why is the sequential time close to the **sum** of the five download times, and the concurrent time close to the **longest** one?

**(b)** The second part of the output times "crunch" tasks, which *compute* instead of wait. Run it with one task, then with the default (twice as many tasks as your computer has cores):

```
mvn -q compile exec:exec -Dmain=Downloads -Dargs="1"
mvn -q compile exec:exec -Dmain=Downloads
```

Why is there almost no speed-up with one crunch task? Use the words *concurrency* and *parallelism* in your answer.

## Exercise 4: A state diagram (10 min, paper)

A food-delivery app tracks each order. A customer places an order. The restaurant either accepts or rejects it. An accepted order is prepared until the food is ready, then picked up by a courier and delivered. The customer may cancel at any time **before** the courier picks the food up. A delivered, rejected or cancelled order is finished.

**(a)** Draw a state diagram for one order: circles for states (name them), arrows labelled with actions. Mark the start state.

**(b)** Write one trace your diagram allows, and one sequence of actions it does **not** allow.

**(c)** Can a customer cancel after pickup in your diagram? Should they be able to? (There is no single right answer; say what you decided.)

---

## Optional extras (not needed in class)

- **Challenge: all the interleavings, in Java.** The Maven project `challenge/` asks you to write a recursive method that lists every interleaving of two processes and computes each result. It checks your Exercise 1 answers automatically. Run `mvn test` in `challenge/` to check your work.
- **Fix the lost likes.** Look up `java.util.concurrent.atomic.AtomicInteger` and change `LikeCounter` so no likes are lost. (We study why this works in Week 4.)

# Minimax: In-Class Practice

**Name(s):** ______________________________  **Date:** ____________

Work with a partner, using pencil and paper. Plan on about 60–75 minutes. Watch the minimax video first: **minimax.mp4**, posted with the course videos. Problems 1–5 cover the algorithm, 6–7 apply it to tic-tac-toe, and 8–10 go further. **Show your work:** write each node's value next to it as you go.

---

## Conventions (read these first)

- **MAX** nodes are drawn as ▲ triangles pointing up, **MIN** nodes as ▼ triangles pointing down, and leaves as squares.
- Every score is from **MAX's point of view**: MAX wants it high, MIN wants it low.
- Children are searched **left to right**. Nodes are named A, B, C, ... level by level; leaves are numbered L1, L2, ... from left to right.
- **Tic-tac-toe:** X is MAX and O is MIN. A finished game scores **+1** if X wins, **0** for a draw and **−1** if O wins. Squares are numbered 0–8, left to right and top to bottom.

## Reference: minimax

```
minimax(node, isMax):
  if node is a leaf:
    return score(node)
  if isMax:
    best = −∞
    for each child c (left to right):
      best = max(best, minimax(c, false))
  else:
    best = +∞
    for each child c (left to right):
      best = min(best, minimax(c, true))
  return best
```

<!-- pagebreak -->

## Reference: alpha-beta pruning (Problem 9)

Alpha-beta returns the same value as minimax but skips branches that can't change the answer. **alpha** is the best value MAX is already guaranteed on the path from the root; **beta** is the best (lowest) value MIN is already guaranteed. As soon as alpha ≥ beta at a node, the rest of that node's children can be skipped.

```
alphabeta(node, isMax, alpha, beta):      start with alpha = −∞, beta = +∞
  if node is a leaf: return score(node)
  if isMax:
    best = −∞
    for each child c (left to right):
      best = max(best, alphabeta(c, false, alpha, beta))
      alpha = max(alpha, best)
      if alpha >= beta: stop (skip the remaining children)
  else:
    best = +∞
    for each child c (left to right):
      best = min(best, alphabeta(c, true, alpha, beta))
      beta = min(beta, best)
      if alpha >= beta: stop (skip the remaining children)
  return best
```

<!-- pagebreak -->

## Problem 1: True or false?

Mark each statement true or false, and give a one-line reason.

| Statement | True / false | Why |
|---|---|---|
| Every leaf score is written from MAX's point of view, even at levels where MIN moves. | | |
| A MIN node's value is the score MAX can guarantee if play reaches that node. | | |
| Minimax always steers toward the largest leaf in the tree. | | |
| Minimax explores the tree breadth-first, one level at a time. | | |
| If both players play perfectly, the game's result is the root's minimax value. | | |

## Problem 2: A two-level tree

![game tree](../media/minimax_figures/hand_001.svg)

a. Write the value of every MIN node and of the root.
b. Which move should MAX make, and what score is MAX guaranteed?

> **Hint:** the middle branch has the biggest leaf. Is that the branch MAX wants?

<!-- workspace:1.2:Work space -->

<!-- pagebreak -->

## Problem 3: A three-level tree

![game tree](../media/minimax_figures/hand_002.svg)

a. Write the value of every internal node (MAX nodes at the bottom level first, then MIN, then the root).
b. Which move should MAX make at the root? What is the root's value?
c. Mark the **principal variation**: the path both players follow if both play perfectly.
d. Where is the largest leaf? Why can't MAX reach it?

> **Hint:** work from the bottom up, one level at a time, and check which player chooses at each level.

<!-- workspace:fill:Work space -->

<!-- pagebreak -->

## Problem 4: Tracing the search

Use the Problem 3 tree. Minimax visits nodes depth-first, children left to right.

a. In what order do the internal nodes (A to J) **return** their values? (The first is the leftmost bottom MAX node.)
b. Write the call stack at the moment leaf L11 is evaluated, from the first call (the root) to the current call.
c. Which node returns last, and why?

> **Hint:** a node returns only after **all** of its children have returned.

<!-- workspace:2.4:Work space -->

<!-- pagebreak -->

## Problem 5: Why not chase the biggest leaf?

![game tree](../media/minimax_figures/hand_003.svg)

a. A greedy player moves toward the biggest leaf (100). What happens if MIN plays well?
b. What does minimax choose, and what score does it guarantee?
c. When might the greedy choice actually work? Why doesn't minimax take that chance?

<!-- workspace:1.6:Work space -->

<!-- pagebreak -->

## Problem 6: Tic-tac-toe: find the winning move

X to move. The small numbers mark the empty squares.

![board](../media/minimax_figures/hand_004.svg)

a. Draw the game tree: X's three possible moves, O's replies, and so on until each game ends.
b. Score every finished game (+1, 0 or −1) and pass the values up the tree.
c. Which move should X play? Explain in one sentence why it works.

> **Hint:** if a move lets O complete three in a row on the next turn, that branch ends there.

<!-- workspace:fill:Work space -->

<!-- pagebreak -->

## Problem 7: Tic-tac-toe: the best you can do

X to move.

![board](../media/minimax_figures/hand_005.svg)

a. Find the minimax value of each of X's three moves.
b. Which move should X play, and what is the result with best play?
c. X can't win here. Does minimax still pick a sensible move? Explain.

<!-- workspace:fill:Work space -->

<!-- pagebreak -->

## Problem 8: When MIN moves first

![game tree](../media/minimax_figures/hand_006.svg)

a. Write the value of every node. (The root is a MIN node this time.)
b. Which move should MIN make, and what is the root's value?
c. What changes in the algorithm when MIN moves first, and what stays the same?

<!-- workspace:1.8:Work space -->

## Problem 9: Alpha-beta pruning

![game tree](../media/minimax_figures/hand_007.svg)

a. Run alpha-beta (children left to right). Cross out every leaf that is never examined. What is the root's value?
b. For each leaf you crossed out, explain in one sentence why it can't change the answer.
c. Rearrange the order of the branches (and the leaves inside each MIN node) so that alpha-beta skips as many leaves as possible. How many can be skipped?

> **Hint:** nothing can be skipped under the first MIN node, because MAX doesn't have a guaranteed value yet.

<!-- workspace:fill:Work space -->

<!-- pagebreak -->

## Problem 10: The cost of looking ahead

Real programs usually can't search to the end of the game, so they stop at a **depth limit**: they count down the moves left, and when the count reaches 0 they **estimate** the score instead. A finished game still gets its true score. Once estimates are involved, the value minimax returns is an estimate too, not a guaranteed result.

```
minimax(node, depth, isMax):
  if the game is over at node:
    return score(node)          the true result
  if depth == 0:
    return estimate(node)       a heuristic guess (part d)
  if isMax:
    best = −∞
    for each child c (left to right):
      best = max(best, minimax(c, depth − 1, false))
  else:
    best = +∞
    for each child c (left to right):
      best = min(best, minimax(c, depth − 1, true))
  return best
```

a. A game has about b moves available in each position. When minimax searches d moves ahead, roughly how many **leaf positions** (positions at the depth limit, which must be scored) does it evaluate? Calculate it for Connect 4 (b = 7) at d = 8.
b. With perfect move ordering, alpha-beta evaluates about b^(d/2 rounded up) + b^(d/2 rounded down) − 1 leaf positions (for even d, both exponents are just d/2). Calculate this for the same search. What does the difference mean in practice?
c. Why can plain minimax search tic-tac-toe all the way to the end from the empty board, but not chess or Connect 4? (How many move sequences can tic-tac-toe have at most?)
d. When the search stops at a depth limit, the position isn't finished. How could a program estimate its score? Propose a scoring rule for Connect 4, and make sure a real win or loss always counts for more than any estimate.

<!-- workspace:fill:Work space -->

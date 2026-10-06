# Week 2 Defect Index — Caleb Payn

**Assignment:** Week 2 — Game Tree Construction & Traversal (F2.1–F2.4)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-002`, commit `12d9a42` (2026-09-29)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`, **game:** Connect Four  
**Your tests:** 3 passed, 0 failed (there are no Week 2 tests; see defect 6).

`TreeNode.java` hasn't changed since this commit, so its line numbers still match your current branch. The `GameState.java` line numbers refer to commit `12d9a42`; your branch has moved on since, so use `git show 12d9a42:cis-3723-computer-science-2/ai-agent/ai-agent/<path>` if a line doesn't match. All paths are relative to your project folder. The **Requirement** column shows which Week 2 requirement (F2.1–F2.4) each item relates to.

**The good news:** your `TreeNode` is well designed. `generate_children` skips terminal states, stores the children and links each child to its parent, and BFS, DFS and `count_nodes` have the right shape. Almost everything below comes from one problem in `GameState.move` (defect 1). When that was fixed in a test copy, `generate_children` produced 7 correct children.

## Defects

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/GameState.java:20-35` (`:21-23`, `:34`) | `move(S, M)` places the piece in **`this.board`** (not a copy) and returns the same object. Every "child" is therefore the root itself, so `generate_children` keeps only **1 child** (`TreeNode.java:109-112` removes the duplicates), and the root's bottom row fills with Player 1 pieces: `1111111`. | Build a new `GameState`: copy each row of `S.board`, place the piece on the copy, set the copy's `curent_player`, and return the copy. | F2.1 (Week 1 code) |
| 2 | `src/main/java/cis3723/okbu/edu/TreeNode.java:44-58` | `count_nodes` returns **7 at every depth** from 1 to 4 (expected 7, 56, 399, 2800 when the root isn't counted), because every move lands on the same shared board. The recursion itself is correct. | Fix defect 1, then test the counts. | F2.2 |
| 3 | `src/main/java/cis3723/okbu/edu/TreeNode.java:60-82` | BFS prints **no boards** (expected 49). After the first level, the shared board's bottom row looks like a win, so nothing is expanded further. | Fix defect 1 and defect 5. | F2.3 |
| 4 | `src/main/java/cis3723/okbu/edu/TreeNode.java:84-99` | DFS returns `true` on a position that can't occur in a real game: seven Player 1 pieces and no Player 2 pieces, all created by a single call to `generate_children`. | Fix defect 1; then DFS will follow one real game until it ends. | F2.4 |
| 5 | `src/main/java/cis3723/okbu/edu/GameState.java:37-45`; `src/main/java/cis3723/okbu/edu/GameState.java:74-172` (`:88-98`) | `display(S)` and `is_terminal(state)` read the field `board` instead of the state they're given (`S.board` / `state.board`). `is_terminal` also reports a win for any piece in the bottom row, because each neighbour check is skipped when it would go off the board. Even with defect 1 fixed in a test copy, `count_nodes` gave 7, 43, 193 (expected 7, 56, 399) and BFS printed 36 boards (expected 49). | Use `state.board` / `S.board` throughout. Rewrite the win scan; your Week 3 defect index has the details. | Week 1/3 code (affects F2.2–F2.4) |
| 6 | `src/test/java/cis3723/okbu/edu/Test_Game.java:12-21` | There are no Week 2 tests, and the two test methods have their bodies commented out. | Add tests: 7 children from the start; 0 children from a won board; `count_nodes` = 7 / 56 / 399 for depths 1–3; 49 boards from BFS; DFS's result is terminal. | F2.1 – F2.4 |

## How your code did on the probe checks

Each check called **your** functions on a fresh starting position. For the won position, the board had four Player 1 pieces in a row.

| Check | Expected | Yours |
|---|---|---|
| `generate_children` on the starting position | 7 children | **1 child (the root itself) ✗** |
| `generate_children` on a position that is already won | 0 children | 0 ✓ |
| `count_nodes(root, 1…4)` on a fresh root (your version doesn't count the root) | 7, 56, 399, 2800 | **7, 7, 7, 7 ✗** |
| Root board after `generate_children` | unchanged (empty) | **bottom row `1111111` ✗** |
| BFS: states printed at depth 2 | 49 boards | **0 ✗** |
| DFS: state found | a genuinely terminal state | **an impossible board (seven Player 1 pieces) ✗** |

Known Connect Four counts (cumulative, including the root): depth 0–6 = 1, 8, 57, 400, 2801, 19608, 137257. Without the root: 0, 7, 56, 399, 2800, 19607.

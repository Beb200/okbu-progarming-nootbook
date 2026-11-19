# Week 1 Defect Index — Caleb Payn

**Assignment:** Week 1 — Core Abstractions: Game Interface & State Representation (F1.1–F1.9)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `c99127a` (2026-10-08)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`, **game:** Connect Four  
**Your tests:** 16 passed, 0 failed.

All file paths below are relative to your project folder, and the line numbers refer to that commit. The **Requirement** column shows which Week 1 requirement each item relates to.

**What's working:** `get_legal_moves` (now correctly checking the top row), the player switch, `is_terminal` and `get_result` on the basic cases, and your `TreeNode` (state, parent, children) all passed. Most of what's below is one issue in `move` (defect 1), the same one that affects your Week 2 and Week 4 code.

## Defects

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/GameState.java:20-35` (`:21-23`, `:34`) | `move(S, M)` changes **`this.board`** and returns the **same object**, so the state you pass in is modified. Every game tree, search and "what if" move needs the original state left alone. | Create a new `GameState`, copy each row of `S.board` into it, place the piece on the copy, switch the copy's `curent_player`, and `return` the copy. | F1.2 |
| 2 | `src/main/java/cis3723/okbu/edu/GameState.java:20-35` | Illegal moves aren't rejected: a full column is silently ignored (nothing placed, no error), and column 7 crashes with `ArrayIndexOutOfBoundsException`. | Check `0 <= column < 7` and that the column isn't full, and `throw new IllegalArgumentException(...)` otherwise. | F1.2 |
| 3 | `src/main/java/cis3723/okbu/edu/GameState.java:37-45` | `display` prints rows of digits (e.g. `0002000`) with no position labels, so a player can't see which number is which column. It also prints `this.board` instead of the `S` it was given. | Print column numbers (e.g. `1 2 3 4 5 6 7`) under the board, and use `S.board`. | F1.6 |
| 4 | `src/test/java/cis3723/okbu/edu/Test_Game.java:16-19, 78-86` | `test_main_runs_without_crashing` and `test_display_runs_on_empty_and_occupied_board` don't assert anything. There are no tests for `TreeNode`, or that `move` places the piece and leaves the input unchanged. | Assert the output or board contents; add a `TreeNode` parent/children test and a `move` placement test. | Tests |
| 5 | `src/main/java/cis3723/okbu/edu/GameState.java:77-233`; `src/main/java/cis3723/okbu/edu/GameState.java:235-258` | `is_terminal` reads `this.board` instead of `state.board`, and `check_winner`/`get_result` only work after `is_terminal` has been called (they read `curent_winner`, which `is_terminal` sets). | Use `state.board` throughout; have `get_result` work out the winner itself (or call `is_terminal` first). | F1.3 / F1.4 |
| 6 | `src/main/java/cis3723/okbu/edu/Interface.java:7` | The PRD calls this method `apply_move`; yours is `move`. | Rename it to `apply_move`. | F1.2 |

## How your code did on the probe checks

| Check | Expected | Yours |
|---|---|---|
| `get_legal_moves`: empty board / column 3 full / full board | `[0..6]` / `[0,1,2,4,5,6]` / `[]` | ✓ ✓ ✓ |
| `move` in column 3: piece at the bottom | bottom row `[0,0,0,1,0,0,0]` | ✓ |
| `move` returns a new state and leaves the input unchanged | new object; input still empty | **✗ same object; input changed** |
| A second move in column 3 | stacks as Player 2 | ✓ (1 then 2) |
| `move` into a full column / column 7 | rejected | **✗ silently ignored / ✗ crash** |
| `get_current_player` at the start, then after 1 and 2 moves | alternates | ✓ 1, 2, 1 |
| `is_terminal` / `get_result`: empty, Player 1 win, full-board draw, game in progress | F/0, T/+1, T/0, F/0 | ✓ ✓ ✓ ✓ |
| `display` | a labelled board | **✗ no labels** |
| `TreeNode`: state, parent link, children list | linked correctly | ✓ |

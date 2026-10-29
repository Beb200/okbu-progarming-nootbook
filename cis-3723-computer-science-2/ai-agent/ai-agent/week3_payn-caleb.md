# Week 3 Defect Index — Caleb Payn

**Assignment:** Week 3 — Win Detection & Terminal State Validation (F3.1–F3.4)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `4b0c0fa` (2026-10-02)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`, **game:** Connect Four  
**Your tests:** **14 passed, 3 failed** (`Test_Game.java:113`, `:123`, `:133`).

All file paths below are relative to your project folder (`cis-3723-computer-science-2/ai-agent/ai-agent/`), and the line numbers refer to that commit. The **Requirement** column shows which Week 3 requirement (F3.1–F3.4) each item relates to; items marked "Week 1/2 code" or "General" are worth fixing before Week 7 (Minimax).

## Defects

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/GameState.java:98-101` | `(board[i][j] != 1) || (board[i][j] != 2)` is always true (every value differs from 1 or from 2), so the loop `break`s on every row and the down-right diagonal is never checked. | Use `board[i][j] == 0` to skip empty cells, with `continue` rather than `break`. | F3.1 |
| 2 | `src/main/java/cis3723/okbu/edu/GameState.java:135-137, 165-167, 195-197` | The other three scans `break` at the first empty cell in a row, so they only check pieces connected to column 0. Missed: probe B (Player 2 horizontal at the right edge), C (vertical) and E (`\` diagonal). | Replace the four loops with one `for r / for c / for each direction` scan that skips empty cells (`continue`). | F3.1 |
| 3 | `src/main/java/cis3723/okbu/edu/GameState.java:168, 198-199` | The bounds use `board.length` (6 rows) for columns, and `i+3 <= board.length` lets `board[i+3]` reach index 6. The full-board draw **crashes** with `ArrayIndexOutOfBoundsException`. | Use `r + 3*dr` in `0..5` and `c + 3*dc` in `0..6` (`board.length` and `board[0].length`, with `<`). | F3.2 |
| 4 | `src/main/java/cis3723/okbu/edu/GameState.java:219` | `get_legal_moves(state) == null` is never true, because the method returns an empty list, not `null`. A draw is never detected. | `if (get_legal_moves(state).isEmpty()) return true;` | F3.2 |
| 5 | `src/main/java/cis3723/okbu/edu/GameState.java:239-249` (`:243`) | `get_result` returns +1 only if the winner is the player whose turn it is, so a Player 1 win returns −1 (probes A, D). | Return +1 when `curent_winner == 1` and −1 when it's 2, whoever is to move. | F3.3 |
| 6 | `src/test/java/cis3723/okbu/edu/Test_Game.java:113-121, 123-131, 133-141` | 3 of the 17 tests fail. They are good tests that correctly expose the bugs above (Player 2 right-edge horizontal, vertical reaching the top, down-right diagonal). | Fix the code; keep the tests. | F3.4 |
| 7 | `src/main/java/cis3723/okbu/edu/GameState.java:226-236` | `check_winner` returns `curent_winner`, a field that only `is_terminal` sets, so it's wrong unless `is_terminal` was called first. | Have `check_winner` do the scan, and have `is_terminal` call it. | General (before Week 7) |
| 8 | `src/main/java/cis3723/okbu/edu/GameState.java:80-112` | `println` runs on every cell, and `is_terminal(state)` reads `this.board` instead of `state.board`. | Remove the prints and use `state.board`. | General (before Week 7) |
| 9 | `src/main/java/cis3723/okbu/edu/Interface.java:7` | The interface names the move method `move(...)`; the PRD (F1.2) calls it `apply_move(...)`. | Rename it to `apply_move`. | General (before Week 7) |

## How your code did on the probe games

Each game was played through **your** `apply_move`, column by column (columns 0–6, Player 1 first). Then your `is_terminal` (T/F) and `get_result` were called on the final position.

| Game | Columns played | Expected | Yours | |
|---|---|:-:|:-:|:-:|
| A Horizontal, Player 1, bottom-left | `0 0 1 1 2 2 3` | T / +1 | T / **−1** (wrong sign) | ✗ |
| B Horizontal, Player 2, right edge | `0 3 0 4 1 5 1 6` | T / −1 | **F / 0** (missed) | ✗ |
| C Vertical, Player 1, column 6, reaching the top row | `0 6 0 6 6 1 6 1 6 2 6` | T / +1 | **F / 0** (missed) | ✗ |
| D Rising diagonal /, Player 1 | `0 1 1 2 2 3 2 3 3 5 3` | T / +1 | T / **−1** (wrong sign) | ✗ |
| E Falling diagonal \, Player 2 | `0 6 5 5 4 4 3 4 3 3 1 3` | T / −1 | **F / 0** (missed) | ✗ |
| K 2×2 square (not a win) | `0 6 1 6 1 6 0` | F / 0 | F / 0 | ✓ |
| J X X O X (not a win) | `0 2 1 6 3 6` | F / 0 | F / 0 | ✓ |
| T Three in a row (not a win) | `0 0 1 1 2` | F / 0 | F / 0 | ✓ |
| N Game in progress (3 moves) | `0 1 2` | F / 0 | F / 0 | ✓ |
| DRAW Full board, no winner | `42 moves (see below)` | T / 0 | **crash**: `ArrayIndexOutOfBoundsException` | ✗ |
| W Full board, Player 2 wins on move 42 | `42 moves (see below)` | T / −1 | T / −1 | ✓ |

- **DRAW** columns: `5 4 5 0 6 2 4 5 5 0 4 1 1 0 4 5 6 5 3 1 1 2 2 6 2 6 6 3 6 2 0 3 0 3 3 4 3 1 4 2 1 0`
- **W** columns: `1 3 1 2 6 6 4 6 5 3 4 1 6 2 6 0 2 5 6 1 3 3 0 0 5 2 1 1 3 3 2 4 5 5 5 2 4 4 0 0 0 4`

You can paste these sequences into your own tests.


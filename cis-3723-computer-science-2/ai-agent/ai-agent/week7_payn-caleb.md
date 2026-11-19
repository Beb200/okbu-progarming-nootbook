# Week 7 Defect Index — Caleb Payn

**Assignment:** Week 7 — Minimax AI (Full Tree Search) (F7.1–F7.5)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `ba7de62` (2026-11-12)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/` · **Game:** Connect Four  
**Your tests:** the project doesn't compile (`ArraryList.java:6`); with that file removed, 17 run, 2 errors (Week 1 tests)

File paths are relative to your project folder, and the line numbers refer to commit `ba7de62`. If the branch you have open is different, view that version with `git show ba7de62:cis-3723-computer-science-2/ai-agent/ai-agent/<path>`. The **Requirement** column shows which requirement each item relates to.

## Defects and suggestions

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/Game.java:86-110` | `ai_turn` still chooses a random move; the Minimax AI (`choose_minimax_move`, `:331-377`) is never used in the game. | In `ai_turn`, replace the random choice with `Move best = choose_minimax_move(state, depth, state);` and apply it (keep the timer). | F7.3 |
| 2 | `src/main/java/cis3723/okbu/edu/Game.java:331-377` | The depth has no default; nothing sets it to 4. | Add `static final int DEFAULT_DEPTH = 4;` and a `depth` field the game uses (optionally set from a prompt). | F7.4 |
| 3 | `src/main/java/cis3723/okbu/edu/Game.java:288-329, 178-186` | Nodes explored aren't counted or written to `performance_log.csv`. | Add a counter that `minimax` increments on each call, reset it before each AI move, and add it as a fourth column in `write_to_csv`. | F7.5 |
| 4 | `src/main/java/cis3723/okbu/edu/GameState.java:129` | `(board[i][j] != 1) || (board[i][j] != 2)` is always true, so the falling-diagonal check never runs. Minimax misses those wins: after `6 3 2 5 3 2 2 3 3 6 5 5 3 2 4` (0-based) Player 2 wins by playing 4, but your search scores the position 0. | Use `&&`, as in your other checks. | General (before Week 7) |
| 5 | `src/main/java/cis3723/okbu/edu/ArraryList.java:6` | `public class ArraryList<> {` doesn't compile, so `mvn test` fails and nothing runs. | Delete the file (nothing uses it). | General (before Week 7) |
| 6 | `src/test/java/cis3723/okbu/edu/` (no Week 7 test) | No test exercises `minimax` or `choose_minimax_move`. | Add tests: from `0 1 0 1 0 1 2` Player 2 plays column 1; from `0 1 0 1 0` it plays 0; a finished game at depth 0 scores ±1. | Week 7 tests |

## How your code did on the probe checks

Each check called **your** code directly, or ran your program with typed input in an empty folder. Columns in this table are 0–6. Moves list the columns played from the empty board, Player 1 first. Expected values come from a separate Minimax that follows the PRD's search conventions.

| Check | Expected | Yours |
|---|---|---|
| Minimax values on 19 cases: the empty board at depths 0, 1, 2 and 4; `0 1 0 1 0 1` (Player 1 can win now) at depths 0, 1 and 4; `0 1 0 1 0 1 2` (Player 2 can win now) at depths 1 and 4; `2 6 3 6` (Player 1 forces a win in 3 plies) at depths 2 and 3; the block positions below; a finished P1 win `0 1 0 1 0 1 0` and P2 win `0 1 0 1 0 1 2 1` at depths 0 and 4 | 0 / +1 / −1 as the position warrants (depth 3 for `2 6 3 6` → +1, depth 2 → 0; finished games ±1 even at depth 0) | ✓ all 19 |
| AI move at depth 4 from `0 1 0 1 0 1 2`: Player 2 to move can win in column 1 (Player 1 also threatens column 0) | 1 | ✓ 1 |
| AI move at depth 4 from `0 1 0 1 0`: Player 2 must block column 0 | 0 | ✓ 0 |
| AI move at depth 4 from `0 6 1 6 2`: Player 2 must block column 3 | 3 | ✓ 3 |
| AI move at depth 4 from `0 1 0 1 0 1`: Player 1 to move can win in column 0 | 0 | ✓ 0 |
| AI move at depth 4 from `0 1 2 1 2 1`: Player 1 must block column 1 | 1 | ✓ 1 |
| Nodes explored for the AI's move after `3`, depth 4 | 2,801 (including the root) | not counted ✗ |
| 60 random positions (seeded), Minimax value at depths 3 and 4 and the AI's move at depth 4, compared with an independent Minimax | the same values and best moves | 2/120 values wrong and 1/60 moves not optimal, all from the falling-diagonal win check (`GameState.java:129`) |
| In the program: a full game against the AI, then `performance_log.csv` | the game ends with a result; one row per AI move with a correct node count | ✗ the game's AI is still random; `choose_minimax_move` is never called |

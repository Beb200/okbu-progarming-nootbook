# Week 7 Defect Index — Caleb Payn

**Assignment:** Week 7 — Minimax AI (Full Tree Search) (F7.1–F7.5)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `f665e06` (2026-11-19)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/` · **Game:** Connect Four  
**Your tests:** the project doesn't compile (`ArraryList.java:6`); with that file removed, 17 run, 1 failure and 2 errors (Week 1–3 tests; the failure, `test_is_terminal_full_board_without_winner_is_true`, is new and comes from item 1 below)

File paths are relative to your project folder, and the line numbers refer to commit `f665e06`. If the branch you have open is different, view that version with `git show f665e06:cis-3723-computer-science-2/ai-agent/ai-agent/<path>`. The **Requirement** column shows which requirement each item relates to.

## Defects and suggestions

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/GameState.java:134-137` | The falling-diagonal check now runs, but `j+3 <= board[i].length` lets it read column 7. When a falling diagonal of the same player's pieces ends in column 6 (two pieces from column 5, or three from column 4), reading `board[..][7]` throws; the `catch` (`:260-262`) prints a stack trace and returns `false`, so no other win check runs. In 3,000 random games this missed 169 of 2,990 game-ending wins and 4 of 10 full-board draws, your test `test_is_terminal_full_board_without_winner_is_true` now fails, and when it happens on a full board the game never ends (Player 1 is asked for a move forever). | Use `j+3 < board[i].length`, as your rising-diagonal check does (`:167`), and drop the repeated bounds checks on `:135-137`. Let exceptions in `is_terminal` surface instead of returning `false`. | General (before Week 7) |
| 2 | `src/main/java/cis3723/okbu/edu/Game.java:23` | `MINIMAX_DEPTH = 5` is a constant, so the depth can't be changed without editing the code, and the PRD's default is 4. | Give `Game` a `depth` field that defaults to 4 and can be set (a constructor argument, a setter, or a prompt in `main`), and pass it to `choose_minimax_move`. | F7.4 |
| 3 | `src/main/java/cis3723/okbu/edu/ArraryList.java:6` | `public class ArraryList<> {` doesn't compile, so `mvn test` fails and nothing runs. | Delete the file (nothing uses it). | General (before Week 7) |
| 4 | `src/test/java/cis3723/okbu/edu/` (no Week 7 test) | No test exercises `minimax`, `minimax_ab` or `choose_minimax_move`. | Add tests: from `0 1 0 1 0 1 2` Player 2 plays column 1; from `0 1 0 1 0` it plays 0; a finished game at depth 0 scores ±1. | Week 7 tests |

**Notes:**

- The game now uses `minimax_ab` (alpha-beta, the Week 8 algorithm) through `choose_minimax_move` (`src/main/java/cis3723/okbu/edu/Game.java:426-484`), ordered by `rank_moves`. It picks the same moves as plain Minimax (60 random positions checked), and its node counts are correct for that search. `minimax` (`:374-415`) is no longer used by the game; Week 8 asks you to log both counts so they can be compared.
- `performance_log.csv` (committed) still starts with the old 3-column rows from Week 5, followed by the new 4-column rows. Start a fresh file, or add a header row, so each column is clear.
- Your earlier commit `b1bfb35` (11/19 22:59) treated every position as finished (the win check sat in the wrong branch of the `if`); `f665e06` fixed that. Run your tests before each push to catch this kind of slip.

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
| Nodes explored for the AI's move after `3`, depth 4 | 2,801 (including the root) | 173 ✓ (alpha-beta, `rank_moves` order: matches a separate alpha-beta with the same order) |
| 60 random positions (seeded), Minimax value at depths 3 and 4 and the AI's move at depth 4, compared with an independent Minimax | the same values and best moves | ✓ 0/120 values and 0/60 moves wrong |
| In the program: a full game against the AI, then `performance_log.csv` | the game ends with a result; one row per AI move with a correct node count | ✓ two full games from `Game.main`: the AI blocked and won both; 16/16 log rows have the correct node count for your alpha-beta search at depth 5 |

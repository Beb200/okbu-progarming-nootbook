# Weeks 1–6 Review at Your Latest Commit — Caleb Payn

**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `b40be1c` (2026-11-10, "fix the move application")  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/` · **Game:** Connect Four  
**Your tests:** the project still **does not compile**, because of `ArraryList.java:6`. With that file removed, 17 tests run and **2 error** (see items 3 and 4).

Every week's requirements were re-checked against this one commit. File paths are relative to your project folder, and the line numbers refer to commit `b40be1c` (`git show b40be1c:cis-3723-computer-science-2/ai-agent/ai-agent/<path>`).

## What your fix achieved

Rewriting `GameState.move` (`src/main/java/cis3723/okbu/edu/GameState.java:20-61`) was exactly the right change, and it fixed a lot at once:
- `move` now returns a new state, leaves the original alone, and rejects bad columns, full columns and the wrong player.
- Your Week 2 tree code now works: 7 children from the start; `count_nodes` gives 7, 56, 399, 2800, 19607; BFS prints all 49 depth-2 boards; DFS finds a real win.
- The winner is now stored per state, so an old win no longer leaks into later games.
- The random AI is now uniform and never picks a full column.

## The one thing that broke: the game can't be played any more

Because `move` no longer changes the state you pass in, code that ignored its return value stopped working:

| # | File:line | What's wrong | How to fix it | Weeks affected |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/Game.java:22-82, 84-108, 235-247` | `human_turn` and `ai_turn` are `void`. `state = state.move(...)` (`:63`, `:93`) only changes their local variable, so `game_play` never sees the new board. The human's move is shown and then lost. On the AI's turn the old state still says it's Player 1's turn, so `move` throws "Move player does not match the current player" and **the game crashes on the first AI move**. | Make both methods return the new state (`public GameState human_turn(...)` … `return state;`), and in `game_play` write `state = human_turn(state, …)` / `state = ai_turn(state, …)`. Then you don't need `move1.player` at all, because `move` uses the state's current player. | 4, 5, 6 |
| 2 | `src/main/java/cis3723/okbu/edu/ArraryList.java:6` | `public class ArraryList<> {` is a syntax error; the project doesn't compile. | Delete the file. | All |
| 3 | `src/main/java/cis3723/okbu/edu/GameState.java:74-88` | `main` has the same problem: the result of `game.move(...)` is ignored (`:80`), so the Player 2 move at `:83` throws. This makes `test_main_runs_without_crashing` (`src/test/java/cis3723/okbu/edu/Test_Game.java:31-34`) fail. | `game = game.move(game, move1);` and so on. | 1 |
| 4 | `src/test/java/cis3723/okbu/edu/Test_Game.java:36-48` | `test_move_full_column_does_not_change_board` expects a full column to be ignored, but `move` now (correctly) throws. | Change it to expect the exception: `assertThrows(IllegalArgumentException.class, () -> state.move(state, makeMove(0, 1)));`. | 1 |

## Still open from earlier

| # | File:line | What's wrong | How to fix it | Weeks affected |
|:-:|---|---|---|---|
| 5 | `src/main/java/cis3723/okbu/edu/Game.java:53-60` | The range check `the_move < 1 && the_move > 7` can never be true (it only prints `true`), so `99` or `0` crashes the game. A full column now crashes it too. | `if (the_move < 1 \|\| the_move > 7)` → re-prompt. Also re-prompt when the column isn't in `get_legal_moves(state)`. | 4 |
| 6 | `src/main/java/cis3723/okbu/edu/GameState.java:129` | `(board[i][j] != 1) \|\| (board[i][j] != 2)` is always true, so falling-diagonal wins are never detected. | Use `&&`. Then un-comment your falling-diagonal test (`Test_Game.java:145-155`). | 3 |
| 7 | `src/main/java/cis3723/okbu/edu/GameState.java:63-73` | `display` has no column labels. | Print `1 2 3 4 5 6 7` under the board. | 1 |
| 8 | `src/test/java/cis3723/okbu/edu/` | No tests for the tree (Week 2), the game loop (4), the AI (5) or save/load (6). The display test (`Test_Game.java:91-99`) asserts nothing. | Now that the tree works, assert 7 children, `count_nodes` = 7 / 56 / 399, and 49 BFS boards. | 2, 4, 5, 6 |
| 9 | `src/main/java/cis3723/okbu/edu/Game.java` | There's no `rank_moves` (the old `rankmove` is commented out). | Add `rank_moves(state)` that puts the legal moves in a `PriorityQueue<Move>` ordered by distance from column 3. | 5 |
| 10 | `src/main/java/cis3723/okbu/edu/Game.java:249-265` | `save_game` appends with no newline and needs the file to exist; `load_game` asks you to type the saved string instead of reading the file. | `save_game(String filepath, GameState state)` overwrites the file with one line; `load_game(String filepath)` reads it back. | 6 |
| 11 | `src/main/java/cis3723/okbu/edu/Game.java:20, 267-304` | The `Connect4Trie` is never used, and the history prints as a raw 0-based list. | Insert the finished game into a `Connect4Trie`, and print `1. Column 4`, `2. Column 1`, … | 6 |

## How your code did on the probe checks

| Check | Expected | Yours |
|---|---|---|
| `move` returns a new state and leaves the input alone | yes | ✓ |
| `move` with column 7, −1, `null`, a full column, the wrong player | rejected | ✓ `IllegalArgumentException` |
| `generate_children` from the start | 7 children | ✓ 7 |
| `count_nodes` depths 1–5 | 7, 56, 399, 2800, 19607 | ✓ exact |
| BFS boards at depth 2 | 49 | ✓ 49 |
| DFS | stops at a real win or draw | ✓ |
| Wins: horizontal, right edge, vertical, rising diagonal, full-board P2 win | terminal, correct winner | ✓ |
| Falling diagonal (P2) `0 6 5 5 4 4 3 4 3 3 1 3` | terminal, −1 | ✗ not detected |
| A draw checked after a Player 1 win | 0 | ✓ 0 |
| Random AI: 700 picks / column 3 full / one legal move | uniform / never 3 / that move | ✓ 89–121 / ✓ / ✓ |
| Human vs AI game from `Game.main` | finishes with a result | ✗ crashes on the first AI move |
| Typing `99` | re-prompt | ✗ crash |

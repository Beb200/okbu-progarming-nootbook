# Week 4 Defect Index — Caleb Payn

**Assignment:** Week 4 — Game Engine & Human Player Interface (F4.1–F4.4)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `c99127a` (2026-10-08)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`, **game:** Connect Four  
**Your tests:** 16 passed, 0 failed. No Week 4 tests.

All file paths below are relative to your project folder (`cis-3723-computer-science-2/ai-agent/ai-agent/`), and the line numbers refer to that commit. The **Requirement** column shows which Week 4 requirement (F4.1–F4.4, or tests) each item relates to; "General" items are worth fixing before the AI games in Weeks 5–9.

## Defects

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/Game.java:26-31` | When `nextInt()` fails on something like `abc`, the bad text is never removed, so the next `nextInt()` fails on the same text, **forever**. | In the `catch`, call `scanner.next()` to throw away the bad token (or read whole lines with `nextLine()` and parse them). | F4.4 |
| 2 | `src/main/java/cis3723/okbu/edu/Game.java:33-43` | There's no full-column check. Choosing a full column passes the 1–7 check, nothing is placed, and the turn is lost. | Check that the column is in `get_legal_moves(state)` before moving, and re-prompt if not. | F4.2 |
| 3 | `src/main/java/cis3723/okbu/edu/Game.java:63, 68-69` | The game starts with `move1.player = 2`, so the person who moves first is called "Player 2", and a first-player win is announced as "Player 2 Wins". A draw is announced as "No Winner". | Start with Player 1. Announce "Draw!" when there's no winner and no legal moves. | F4.3 |
| 4 | `src/main/java/cis3723/okbu/edu/Game.java:8-56` | `human_turn` is really the whole game loop, and it works directly with `GameState` and one shared `Move` object. | Split it: `human_turn(state)` reads and returns one move, and a `play()` method loops until `is_terminal`. | F4.1 |
| 5 | `src/main/java/cis3723/okbu/edu/Game.java:53` | After every move it prints the next player's number ("1" or "2"). | Remove the debug print. | General (before Week 7) |
| 6 | `src/test/java/cis3723/okbu/edu/Test_Game.java` | There are no tests for the game loop. | Test with `System.setIn(new ByteArrayInputStream("abc\n4\n".getBytes()))`. | Tests |

## How your game did on the scripted games

Each game was run through **your** program, with the input typed one line at a time (columns typed as 1–7). Columns below are numbered 0–6, left to right.

| Script | Input | Expected | Yours |
|---|---|---|---|
| Player 1 wins | columns `0 1 0 1 0 1 0` | Player 1 win announced | first player's win announced as **"Player 2 Wins"** ✗ (see defect 3) |
| Player 2 wins | columns `0 1 0 1 0 1 2 1` | Player 2 win announced | announced as "Player 1 Wins" ✗ (labels swapped) |
| Full-board draw | the 42-move draw sequence (below) | draw announced after move 42 | announced as **"No Winner"** ✗ |
| Invalid input | `abc`, a blank line, out-of-range numbers, `3.5`, then a full column, then a Player 1 win | each rejected with a message, no turn lost, Player 1 wins | ✗ **stuck forever** on `abc` (printed "not a int try agin" until stopped) |
| Input ends mid-game | one move, then no more input | the program stops cleanly | ✗ stuck forever |

The 42-move draw (columns 0–6): `5 4 5 0 6 2 4 5 5 0 4 1 1 0 4 5 6 5 3 1 1 2 2 6 2 6 6 3 6 2 0 3 0 3 3 4 3 1 4 2 1 0`. You can use it in your own tests.


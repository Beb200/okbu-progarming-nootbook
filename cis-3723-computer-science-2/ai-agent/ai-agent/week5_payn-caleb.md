# Week 5 Defect Index — Caleb Payn

**Assignment:** Week 5 — AI Placeholder & Performance Timing (F5.1–F5.5)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `32d03e5` (2026-10-27)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`  
**Your tests:** 16 passed, 0 failed. No Week 5 tests.

File paths are relative to your project folder, and the line numbers refer to commit `32d03e5`. If the branch you have open is different, view that version with `git show 32d03e5:cis-3723-computer-science-2/ai-agent/ai-agent/<path>`. The **Requirement** column shows which Week 5 requirement each item relates to.

## Defects

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/Game.java:75-95, 97-131` | After choosing a random legal column, `rankmove` moves it toward the centre half the time **without checking it's still legal**. So the AI isn't uniformly random, and it sometimes plays a full column (nothing is placed). | Remove the `rankmove` call from `ai_turn`: pick `moves.get(random.nextInt(moves.size()))` and use that column. | F5.1 |
| 2 | `src/main/java/cis3723/okbu/edu/Game.java` | There's no `rank_moves(state)` method and no priority queue. | Add `rank_moves`: put the legal moves into a `PriorityQueue<Move>` ordered by `Math.abs(m.colomn - 3)`, then poll them into a list. | F5.5 |
| 3 | `src/main/java/cis3723/okbu/edu/Game.java:149-159` | The CSV (via opencsv) has no header row. | Write `move_number,time_ms,move` first. | F5.4 |
| 4 | `src/test/java/cis3723/okbu/edu/Test_Game.java` | There are no Week 5 tests. | Test that `ai_turn` never plays a full column and that `rank_moves` starts with column 3. | Tests |
| 5 | `src/main/java/cis3723/okbu/edu/GameState.java:20-41` | Still open from Week 2: `move` returns the old state (your copy `next` isn't used yet). | See your Week 2 file. | Week 2 code |

## How your code did on the probe checks

Each check called **your** code directly, or ran your program with typed input in an empty folder.

| Check | Expected | Yours |
|---|---|---|
| `random_ai` on an empty board (700 calls) | every column chosen roughly equally | **✗ skewed**: column 3 chosen 196 of 700 times |
| `random_ai` with column 3 full (300 calls) | column 3 never chosen; no errors | **✗ 51 of 300** played the full column (nothing placed, turn lost) |
| `random_ai` with only one legal move left | returns that move | ✓ |
| `rank_moves` on an empty board | centre first: 3, then 2/4, 1/5, 0/6 (or a smarter order) | **✗ no `rank_moves`** |
| A human vs. AI game from your program's start | the game finishes and announces the result | ✓ (the AI moves first) |
| `performance_log.csv` after that game | one row per AI move: move number, time, move | ✓ move, ms, column (quoted values, no header) |

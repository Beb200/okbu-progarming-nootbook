# Week 6 Defect Index — Caleb Payn

**Assignment:** Week 6 — Move History & State Serialization (F6.1–F6.5)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, branch `ai-agent-003`, commit `4d6ba8d` (2026-10-30)  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/` · **Game:** Connect Four  
**Your tests:** **does not compile** (`ArraryList.java:6`); with that file removed, 16 passed, 0 failed; no Week 6 tests

File paths are relative to your project folder, and the line numbers refer to commit `4d6ba8d`. If the branch you have open is different, view that version with `git show 4d6ba8d:cis-3723-computer-science-2/ai-agent/ai-agent/<path>`. The **Requirement** column shows which requirement each item relates to.

## Defects and suggestions

| # | File:line | What's wrong | How to fix it | Requirement |
|:-:|---|---|---|---|
| 1 | `src/main/java/cis3723/okbu/edu/ArraryList.java:6` | `public class ArraryList<> {` has an empty type-parameter list, a syntax error, so the whole project fails to compile (`mvn test`: BUILD FAILURE) and none of the Week 6 code can run. | Delete `ArraryList.java` (nothing uses it), or write `public class ArraryList<T> {`. | General (before Week 7) |
| 2 | `src/main/java/cis3723/okbu/edu/Game.java:20, 64, 94` | `Connect4Trie` is never used: the game records moves in an `ArrayList`. | Create a `Connect4Trie` in `Game`, and `insert` the finished game's columns at the end of `game_play`. | F6.3 |
| 3 | `src/main/java/cis3723/okbu/edu/Game.java:259-264` | `load_game` asks the user to type the serialized string (`:261`) instead of reading a file, so a saved game can't be resumed from `saved_games.txt`. | Write `load_game(String filepath)`: read the file's last line with `Files.readAllLines` and return `deserialize_state(line)`. | F6.4 |
| 4 | `src/main/java/cis3723/okbu/edu/Game.java:248-257` | `save_game` appends with no newline (`:252`), so a second save runs into the first (`…1200` + `2,000…`). `APPEND` without `CREATE` throws if `saved_games.txt` doesn't exist yet (the exception is printed and the save is lost). | Write `save_game(String filepath, GameState state)` with `Files.writeString(path, line + "\n")` (overwrite, which creates the file). | F6.4 |
| 5 | `src/main/java/cis3723/okbu/edu/Game.java:266-303` | The history is printed as a raw list `[3, 1, 0, …]` (`:278`, `:283`): not numbered, 0-based while the player types 1–7 (`:51`), and with 47 entries for a 42-cell board, because moves into a full column are still recorded (`human_turn` `:22-82`, `ai_turn` `:84-108`). | Print `(i + 1) + ". Column " + (move + 1)` per move, and only record a move after a piece was actually placed. | F6.5 |
| 6 | `src/main/java/cis3723/okbu/edu/Game.java:84-108` | Still open from Week 5: `ai_turn` can choose a full column (the `rankmove` nudge), and `human_turn` (Week 4) doesn't reject full columns. | Pick only from `get_legal_moves`, and re-prompt the human on a full column. | Week 4 code |
| 7 | `src/test/java/cis3723/okbu/edu/AppTest.java` | There are no Week 6 tests. | Test the round trip, `Connect4Trie`, and save → load with a temp file. | Tests |

## How your code did on the probe checks

Each check called **your** code directly, or ran your program with typed input in an empty folder. Columns are 0–6 unless your program uses 1–7.

| Check | Expected | Yours |
|---|---|---|
| `mvn test` on your commit | builds | ✗ `ArraryList.java:6` syntax error |
| `serialize_state` on the empty board (file removed) | a compact string | ✓ `1,` + 42 zeros |
| Round trip on 9 positions (the empty board, `3`, `3 4 3 4 3`, the Week 3 E game one move before the win and at the win, the D win, the 41-move draw prefix, the full draw, and the full board P2 wins on move 42) | same board and player | ✓ all 9 |
| All 1,415 distinct positions up to 4 moves | 1,415 distinct strings | ✓ 1,415 |
| Trie: insert `3 4 3 4 3 4 3` and `3 4 2` | both found; `3 4` a prefix only; `1` absent | ✓ (but the game doesn't use it) |
| Save twice, then look at `saved_games.txt` | one readable saved game | ✗ the two strings run together |
| Load a game from the file | reads the file | ✗ asks the user to type the string |
| Move history at game end | numbered list of the moves played, in your 1–7 columns | ✗ raw 0-based list, 47 entries |

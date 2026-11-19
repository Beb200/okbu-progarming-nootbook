# Product Requirements Document
## Game-Playing AI Agent
### DSA Trees Module | Second-Year Data Structures & Algorithms

---

## 1. Overview

This document defines the requirements for building a fully functional game-playing AI agent. You will design and implement a system capable of playing a two-player, turn-based game of your choice using game tree search and heuristic evaluation. The system is built incrementally over ten weeks, with each week's features tied directly to the data structures and algorithms covered in lecture.

**Reference Implementation:** Connect Four is used throughout this document as the worked example. All concepts, feature descriptions, and acceptance criteria apply equally to other approved games (see Section 2.3).

**Final Product:** A human-vs-AI game featuring:
- A pluggable game engine with rule enforcement
- A Minimax AI with alpha-beta pruning
- Two distinct heuristic evaluation functions
- A performance analysis comparing search strategies and heuristics

---

## 2. Scope & Game Selection

### 2.1 Stakeholders

| Role | Description |
|---|---|
| **Developer** | You — the student designing and building the system |
| **End User** | A human player competing against the AI |
| **Evaluator** | Course instructor assessing correctness, design, and analysis |

### 2.2 Architecture Principle

Your system must be designed so that the **AI search logic is fully decoupled from the game logic**. The Minimax engine should not contain any game-specific code. Instead, it should operate through a standard interface (see Section 4, F1) that any game can implement. This is the most important design decision in the project.

### 2.3 Approved Games

You may build your agent for any of the following games. Confirm your choice with your instructor before the start of the project.

| Game | Difficulty | Notes |
|---|---|---|
| **Connect Four** *(recommended)* | ⭐⭐⭐ | Large enough to require pruning; heuristic design is rich and well-documented |
| **Tic-Tac-Toe** | ⭐⭐ | Fully solvable; use only if extending to a harder variant (e.g., 4×4, 3D) |
| **Othello / Reversi** | ⭐⭐⭐ | Mobility and corner heuristics make for interesting design challenges |
| **Checkers** | ⭐⭐⭐⭐ | Complex move generation; rich heuristic space |
| **Chess (endgame only)** | ⭐⭐⭐⭐ | Restrict to K+R vs. K or similar; transposition tables recommended (not graded) |
| **Custom game** | Varies | Must be approved by instructor; must have at least 4 legal moves from the starting position (G4) |

### 2.4 Game Eligibility Requirements

Whatever game you choose must satisfy:

| ID | Requirement |
|---|---|
| G1 | Two-player, turn-based (no simultaneous moves) |
| G2 | Perfect information (no hidden state, no randomness) |
| G3 | Finite state space with terminal win/loss/draw conditions |
| G4 | At least 4 legal moves from the starting position |
| G5 | Approved by instructor before project start |

---

## 3. Technology Requirements

| ID | Requirement |
|---|---|
| T1 | Language: Java 17+ (confirm with instructor) |
| T2 | UI: Command-line interface (CLI) minimum; graphical UI optional for bonus credit |
| T3 | Version Control: Git repository required; commit often |
| T4 | Testing: Unit tests required for core game logic and tree search functions |
| T5 | Documentation: README.md required at submission |

---

## 4. Feature Map by Week

Each week introduces new features that build directly on what you learned in class. Features are cumulative — do not skip ahead.

The **Game Interface** (F1) is the contract between your game logic and your AI engine. Every game-specific class must implement this interface. The Minimax engine calls only these methods — it never touches game-specific internals directly.

---

### Week 1 — Core Abstractions: Game Interface & State Representation
**Learning Focus:** Tree fundamentals, nodes, recursive structure

**The Game Interface (implement this first — everything else depends on it):**

| ID | Method / Property | Description |
|---|---|---|
| F1.1 | `get_legal_moves(state)` | Returns a list of all legal moves from the given state |
| F1.2 | `apply_move(state, move)` | Returns a new state after applying the given move |
| F1.3 | `is_terminal(state)` | Returns True if the game is over (win or draw) |
| F1.4 | `get_result(state)` | Returns +1 (player 1 wins), -1 (player 2 wins), 0 (draw) |
| F1.5 | `get_current_player(state)` | Returns which player's turn it is |
| F1.6 | `display(state)` | Prints a human-readable representation of the state |

**State Representation:**

| ID | Feature | Status |
|---|---|---|
| F1.7 | Define a `GameState` class/struct for your chosen game (e.g., board grid, current player, last move) | [ ] |
| F1.8 | Implement all six Game Interface methods for your chosen game | [ ] |
| F1.9 | Define a `TreeNode` class/struct wrapping a `GameState` with a parent reference and list of children | [ ] |

**Connect Four Example:**
- `GameState` holds a 6×7 grid and a current player indicator
- `get_legal_moves()` returns column indices where a piece can be dropped
- `apply_move()` drops a piece in the specified column and switches the active player

**Acceptance Criteria:**
- **F1.7/F1.8:** All six interface methods are implemented and callable
- **F1.8:** `display()` renders the board correctly with position labels
- **F1.9:** A `TreeNode` can hold a `GameState` and link to child nodes
- **G1–G5:** Chosen game satisfies all eligibility requirements

---

### Week 2 — Game Tree Construction & Traversal
**Learning Focus:** Tree traversals, recursion, BFS/DFS

| ID | Feature | Status |
|---|---|---|
| F2.1 | Implement `generate_children(node)` — expands all legal moves from a node using F1.1 and F1.2 | [ ] |
| F2.2 | Implement `count_nodes(node, depth)` — recursively counts total nodes in the game tree up to a given depth | [ ] |
| F2.3 | Write a BFS traversal that prints all states at depth 2 (all positions after 2 moves) | [ ] |
| F2.4 | Write a DFS traversal that finds and prints the first terminal state reachable from the initial state | [ ] |

**Connect Four Example:**
- At depth 1, there are 7 children (one per column)
- At depth 2, there are up to 49 children (7 × 7)

**Acceptance Criteria:**
- **F2.1:** All legal children are generated with no duplicates or illegal states
- **F2.2:** `count_nodes()` returns correct counts (verify against known values for your game)
- **F2.3:** All depth-2 states are printed without error
- **F2.4:** DFS correctly identifies and prints a terminal state

---

### Week 3 — Win Detection & Terminal State Validation
**Learning Focus:** BST search logic, structured search, invariant checking

| ID | Feature | Status |
|---|---|---|
| F3.1 | Implement `check_winner(state)` — detects all winning conditions for your chosen game | [ ] |
| F3.2 | Verify `is_terminal(state)` correctly handles wins, losses, and draws | [ ] |
| F3.3 | Verify `get_result(state)` returns correct values for all terminal state types | [ ] |
| F3.4 | Write unit tests covering all win conditions and edge cases specific to your game | [ ] |

**Connect Four Example:**
- Win conditions: four-in-a-row horizontally, vertically, diagonally (both directions)
- Draw: board is full with no winner
- Edge cases: wins in the top row, wins spanning the board boundary

**Acceptance Criteria:**
- **F3.1:** All win conditions for your game are detected correctly
- **F3.2:** Draw condition detected correctly (no false positives or negatives)
- **F3.3:** `get_result()` returns correct values for all terminal state types
- **F3.4:** All unit tests pass; edge cases are explicitly covered

---

### Week 4 — Game Engine & Human Player Interface
**Learning Focus:** Balanced trees, structured data management

| ID | Feature | Status |
|---|---|---|
| F4.1 | Implement a `Game` controller class that manages game flow using the Game Interface | [ ] |
| F4.2 | Implement `human_turn(state)` — prompts user for move input, validates input, applies move | [ ] |
| F4.3 | Implement a full game loop: alternate turns, display state after each move, detect and announce result | [ ] |
| F4.4 | Handle all invalid inputs gracefully with a helpful message and re-prompt | [ ] |

**Connect Four Example:**
- `human_turn()` prompts for a column number (1–7), rejects full columns and out-of-range input
- Game loop displays the board after every move and announces "Player 1 wins!", "Player 2 wins!", or "Draw!"

**Acceptance Criteria:**
- **F4.1/F4.3:** Two human players can complete a full game via CLI
- **F4.2/F4.4:** All invalid inputs are rejected with a helpful message; player is re-prompted
- **F4.3:** Game correctly ends and announces the result
- **F4.1:** Game controller uses only the Game Interface methods — no game-specific logic in the controller

---

### Week 5 — AI Placeholder & Performance Timing
**Learning Focus:** Heaps, priority queues, performance measurement

| ID | Feature | Status |
|---|---|---|
| F5.1 | Implement `random_ai(state)` — selects a random legal move using `get_legal_moves()` | [ ] |
| F5.2 | Integrate `random_ai` into the game loop as the AI player | [ ] |
| F5.3 | Implement a `timer` utility that measures and logs the time taken for any function call | [ ] |
| F5.4 | Log move selection time for each AI turn to `performance_log.csv` (move number, time, move chosen) | [ ] |
| F5.5 | Implement `rank_moves(state)` — returns legal moves sorted by a game-specific priority using a priority queue/heap | [ ] |

**Connect Four Example:**
- `rank_moves()` prioritizes center columns (column 4 > columns 3,5 > columns 2,6 > columns 1,7)

**Acceptance Criteria:**
- **F5.1/F5.2:** Human vs. Random AI game is fully playable end-to-end
- **F5.3/F5.4:** Move times are logged correctly to CSV with move number and timestamp
- **F5.5:** `rank_moves()` uses a heap/priority queue structure and returns a game-meaningful ordering

---

### Week 6 — Move History & State Serialization
**Learning Focus:** Tries, general trees, serialization/deserialization

| ID | Feature | Status |
|---|---|---|
| F6.1 | Implement `serialize_state(state)` — converts a game state to a compact, unique string representation | [ ] |
| F6.2 | Implement `deserialize_state(string)` — reconstructs a `GameState` from a serialized string | [ ] |
| F6.3 | Implement a `MoveHistory` trie or prefix tree that stores sequences of moves made during a game | [ ] |
| F6.4 | Implement `save_game(filepath)` and `load_game(filepath)` — save and restore a game in progress | [ ] |
| F6.5 | Display move history as a numbered list at game end | [ ] |

**Connect Four Example:**
- `serialize_state()` encodes the 6×7 grid as a 42-character string (e.g., `"0"` = empty, `"1"` = Player 1, `"2"` = Player 2)
- Move history trie stores sequences like [3, 4, 3, 4, 3, 4, 3] (column choices per turn)

**Acceptance Criteria:**
- **F6.1/F6.2:** Serialization round-trips correctly (serialize → deserialize → identical state)
- **F6.3:** Move sequences are stored and retrievable from the trie
- **F6.4:** A game can be saved mid-play and resumed correctly
- **F6.5:** Move history is displayed accurately at game end

---

### Week 7 — Minimax AI (Full Tree Search)
**Learning Focus:** Game trees, Minimax algorithm, recursive search

**Search conventions (apply to `minimax()` and to `minimax_ab()` in Week 8):**
- **Score perspective:** Scores are from Player 1's point of view, matching `get_result()` (F1.4): a higher score is better for Player 1. `maximizing_player` is True when the player to move is Player 1.
- **Finished games first:** Check `is_terminal(state)` before checking the depth limit, so a finished game is always scored as a finished game, even at depth 0.
- **Depth limit:** Until Week 9, score a non-terminal state at the depth limit as 0.
- **States with no legal moves:** The search assumes every non-terminal state has at least one legal move. If a player in your game can be left with no move while the game goes on (a pass in Othello), `get_legal_moves()` must return a single "pass" move. If having no move ends the game (a loss in checkers, stalemate in chess), `is_terminal()` must return True and `get_result()` must give that outcome.
- **Ties:** When two or more moves share the best score, the search may return any of them.

| ID | Feature | Status |
|---|---|---|
| F7.1 | Implement `minimax(state, depth, maximizing_player, game)` — recursive Minimax using only the Game Interface | [ ] |
| F7.2 | Use `is_terminal()` and `get_result()` as base cases; recurse through `generate_children()` | [ ] |
| F7.3 | Integrate Minimax AI into the game loop (replacing random AI) | [ ] |
| F7.4 | Add a `depth` parameter to control search depth (default: 4) | [ ] |
| F7.5 | Log nodes explored per move to `performance_log.csv` | [ ] |

**Connect Four Example:**
- Minimax at depth 4 explores up to 7⁴ = 2,401 nodes per move (before pruning)
- Verify correctness first on Tic-Tac-Toe (fully solvable). Search at depth 6 or more (or the full game, depth 9); there the AI should never lose. At depth 4 a correct Minimax *can* lose Tic-Tac-Toe: after a corner opening, the losing replies lose only six moves later, beyond what depth 4 can see (the *horizon effect*). A loss at depth 4 is not by itself a bug.

**Acceptance Criteria:**
- **F7.1:** Minimax uses only Game Interface methods — no game-specific code inside the search
- **F7.2:** Base cases are correctly handled for both terminal and depth-limit states
- **F7.3/F7.4:** At depth 4, the Minimax AI makes only legal moves, always takes an immediate win, and always blocks the opponent's immediate win when a block exists
- **F7.5:** Node counts are logged correctly per move

---

### Week 8 — Alpha-Beta Pruning & Search Optimization
**Learning Focus:** Alpha-beta pruning, IDDFS, move ordering

| ID | Feature | Status |
|---|---|---|
| F8.1 | Implement `minimax_ab(state, depth, alpha, beta, maximizing_player, game)` — Minimax with alpha-beta pruning. Stop searching a node's remaining children as soon as `alpha >= beta` | [ ] |
| F8.2 | Replace `minimax()` with `minimax_ab()` in the game loop | [ ] |
| F8.3 | Implement move ordering: sort children by `rank_moves()` before recursing to improve pruning efficiency | [ ] |
| F8.4 | Run benchmarking experiment: search depths 1–8, record nodes explored for plain Minimax, alpha-beta without move ordering, and alpha-beta with move ordering (F8.3) | [ ] |
| F8.5 | Save benchmark results to `benchmark_results.csv` (not `performance_log.csv`, which keeps the per-move AI log) and print a summary table to console | [ ] |

**File format for F8.5:** Overwrite `benchmark_results.csv` on each benchmark run, starting with this header:
```
depth,minimax_nodes,alphabeta_nodes,alphabeta_ordered_nodes,percent_reduction
```
`percent_reduction` is (`minimax_nodes` − `alphabeta_ordered_nodes`) ÷ `minimax_nodes` × 100.

**Benchmark setup for F8.4:** Run every search from your game's starting state, with Player 1 to move. Count every state the search visits, including the starting state. The benchmark uses no randomness, so running it twice gives the same file. Above the summary table, print the game name and the evaluation used at the depth limit (0 until Week 9 adds a heuristic).

**Connect Four Example:**
- At depth 8 without pruning: up to 7⁸ ≈ 5.7 million nodes
- With alpha-beta and good move ordering: typically reduced by 50–90%

**Acceptance Criteria:**
- **F8.1/F8.2:** From the same state and depth, `minimax_ab()` returns the same score as `minimax()`, and the move it picks is one of the moves with that best score. It may pick a different tied move than plain Minimax, because move ordering changes which tied move is seen first
- **F8.3:** The benchmark reports node counts for alpha-beta with and without move ordering at every depth. Ordering does not have to help at every depth; if it does not help at some depth, your A1 analysis says so and suggests why
- **F8.4/F8.5:** `benchmark_results.csv` has one row for each depth 1–8 and is the same when the benchmark is run twice. At every depth, alpha-beta explores no more nodes than plain Minimax, and at depth 4 and above it explores fewer. (At depth 1 there is nothing to prune, so equal counts are expected there.)

---

### Week 9 — Heuristic Evaluation Functions
**Learning Focus:** Heuristic design, depth-limited search, empirical evaluation

#### 9A — Depth-Limited Search

| ID | Feature | Status |
|---|---|---|
| F9.1 | Modify `minimax_ab()` to accept an `evaluate(state)` function parameter. `evaluate` estimates how good an unfinished state is without searching further, and returns an integer: positive favors Player 1, negative favors Player 2, and 0 means even. It must not change the state | [ ] |
| F9.2 | When `depth == 0` and state is non-terminal, call `evaluate(state)` instead of recursing | [ ] |
| F9.3 | Confirm the AI plays legally at the depth limit with a non-trivial heuristic, and still takes immediate wins and blocks immediate losses (the F7.3/F7.4 check) | [ ] |

**Scoring rules for 9A–9C:**
- Heuristics return scores from Player 1's point of view, the same as the search.
- Heuristics are game-specific, so they belong with your game code, not in the search. The search receives `evaluate` as a parameter and only calls it, which keeps it within NF4 even though `evaluate` is not one of the Game Interface methods.
- A finished game must outscore every heuristic value. Score a terminal state as `get_result(state)` times a number larger than any score your heuristics can return (for example, 1,000,000).

#### 9B — Heuristic 1: Pattern/Threat Scoring

| ID | Feature | Status |
|---|---|---|
| F9.4 | Implement `heuristic_threat(state)` — score the state by identifying and weighting offensive and defensive patterns | [ ] |
| F9.5 | Assign scores to patterns: immediate win threat, two-step win setup, opponent threats to block | [ ] |

**Connect Four Example for F9.4/F9.5:**
- Scan all windows of 4 cells that hold pieces of only one player. Score 3 Player 1 pieces + 1 empty as +50 and 2 Player 1 pieces + 2 empty as +10; score the same windows for Player 2 as −50 and −10. Four in a row is a finished game, so `get_result()` scores it, not the heuristic

#### 9C — Heuristic 2: Positional/Strategic Scoring

| ID | Feature | Status |
|---|---|---|
| F9.6 | Implement `heuristic_positional(state)` — score the state using a weighted table reflecting strategic position value | [ ] |
| F9.7 | Combine positional scores with threat scores in `heuristic_positional` | [ ] |

**Connect Four Example for F9.6/F9.7:**
- Apply a 6×7 weight table awarding higher scores for center columns and middle rows; add window threat scores on top

#### 9D — Tournament

| ID | Feature | Status |
|---|---|---|
| F9.8 | Implement `tournament(heuristic_a, heuristic_b, num_games, game, seed)` — AI vs. AI for N games, alternating first move. Both sides search to the same depth. Because the search has no randomness, the first two moves of each game (one per player) are chosen at random using a random number generator created from `seed`, so the games are not all the same | [ ] |
| F9.9 | Save one row per game to `tournament_results.csv`, then print each heuristic's wins, losses, and draws, along with the seed and depth used | [ ] |

**File format for F9.9:** Overwrite `tournament_results.csv` on each tournament run, starting with this header:
```
game_number,first_player_heuristic,winner_heuristic,num_moves
```
`winner_heuristic` is `draw` when the game is a draw.

**Acceptance Criteria:**
- **F9.3:** With either heuristic at the depth limit, the AI takes immediate wins and blocks immediate losses
- **F9.4/F9.6:** Unit tests show each heuristic giving a positive score to at least one non-terminal state that favors Player 1 and a negative score to one that favors Player 2
- **F9.8/F9.9:** Tournament runs without errors for at least 20 games, and running it twice with the same seed and depth produces the same `tournament_results.csv`
- **F9.9:** Results are recorded and summarized. The heuristics do not have to differ; A2 explains the results whichever way they come out
- **F9.1–F9.7:** Neither heuristic causes the AI to make illegal moves

---

### Week 10 — Final Polish, Analysis & Submission
**Learning Focus:** Integration, documentation, empirical analysis

| ID | Feature | Status |
|---|---|---|
| F10.1 | Final game loop supports three CLI modes: `--mode human`, `--mode benchmark`, `--mode tournament`. `--mode human` and `--mode benchmark` accept `--heuristic threat` or `--heuristic positional` to choose the evaluation function | [ ] |
| F10.2 | README.md completed: project description, game choice rationale, how to run, heuristic descriptions, findings summary | [ ] |
| F10.3 | All unit tests passing | [ ] |
| F10.4 | Code reviewed for clarity, inline comments, and style consistency | [ ] |
| F10.5 | **Save and load in human mode.** During a human turn, the player can type `save` (default file) or `save <file>` instead of a move; the game confirms where it saved and the same player moves next. At startup, before a new game, the program asks for a saved-game file to resume (Enter starts a new game). A missing or invalid file prints an error and asks again, without crashing. The save file must use `serialize_state()` (F6.1) for the board and must also store the moves played so far, in order | [ ] |
| F10.6 | **Move history across games.** The `MoveHistory` trie (F6.3) keeps every game played during one run of the program, not just the current one. At game end, add the finished game to the trie, show its moves as a numbered list (F6.5), and use the trie to report how many recorded games began with the same opening move. The trie stays in memory only; it is not saved to disk | [ ] |
| F10.7 | **Performance log format.** Each AI move appends one row to `performance_log.csv` with this header (written only when the file is new or empty): `timestamp,move_number,player,move,time_ms,nodes_explored`. `timestamp` is the wall-clock date and time; `time_ms` is how long the AI took to choose the move. The file is appended to across runs, never overwritten | [ ] |

**Connect Four Example for F10.5:**
```
state=000000000000000000000000000000000000012000:1
moves=1:3,2:4
```

**Written Analysis (2–3 pages, submitted separately):**

| ID | Section | Status |
|---|---|---|
| A1 | Pruning Effectiveness — graph of nodes explored (Minimax vs. alpha-beta, depths 1–8) | [ ] |
| A2 | Heuristic Comparison — tournament results table, win rates, hypothesis for outcome | [ ] |
| A3 | Reflection — what worked, what didn't, what you'd improve with more time. Use your `performance_log.csv` or `benchmark_results.csv` data to support at least one claim | [ ] |

**Acceptance Criteria:**
- **F10.1:** All three modes run without errors from the command line
- **F10.2:** README is complete; a new reader can run the project without additional instructions
- **F10.3:** All unit tests pass with no failures. The tests include at least: win detection, draw detection, the serialization round-trip (F6.1/F6.2), the AI taking an immediate win, the AI blocking an immediate loss, and `minimax_ab()` returning the same score as `minimax()`
- **F10.5:** A game saved mid-play and loaded in a new run continues from the same board with the same player to move. Its numbered move history at game end includes the moves from before the save
- **F10.6:** After two or more games in one run, the opening count comes from the trie and is correct
- **F10.7:** Each AI move adds exactly one row with all six columns, and a second run adds rows after the first run's
- **A1–A3:** Written analysis addresses all three sections with data, graphs, and reasoning

---

## 5. Non-Functional Requirements

| ID | Requirement | Specification |
|---|---|---|
| NF1 | Performance | On your own computer, at depth 6, every AI move in a full game against the random AI (F5.1) takes under 5 seconds, as recorded in the `time_ms` column of `performance_log.csv`. List your computer's processor and memory in the README |
| NF2 | Correctness | AI must never make an illegal move |
| NF3 | Modularity | Game logic, AI search logic, and UI must be in separate modules/files |
| NF4 | Decoupling | Minimax engine must use only the Game Interface (F1.1–F1.6) — no game-specific code in the search |
| NF5 | Testability | Minimum 10 unit tests, including the tests listed in the F10.3 acceptance criterion |
| NF6 | Reproducibility | Running the benchmark twice produces the same `benchmark_results.csv`; running the tournament twice with the same seed and depth produces the same `tournament_results.csv` |
| NF7 | Version Control | At least one Git commit per week with a meaningful commit message |

---

## 6. Deliverables Checklist

| ID | Deliverable | Due |
|---|---|---|
| D1 | Game choice confirmed with instructor; F1.1–F2.4 complete | End of Week 2 |
| D2 | Weeks 3–4 milestone: F3.1–F4.4 complete | End of Week 4 |
| D3 | Weeks 5–6 milestone: F5.1–F6.5 complete | End of Week 6 |
| D4 | Week 7 checkpoint: F7.1–F7.5 complete, Minimax AI playable | End of Week 7 |
| D5 | Week 8 checkpoint: F8.1–F8.5 complete, benchmark results saved | End of Week 8 |
| D6 | Week 9 checkpoint: F9.1–F9.9 complete, tournament results saved | End of Week 9 |
| D7 | Final submission: F10.1–F10.7 complete + README + written analysis (A1–A3) | End of Week 10 |
| D8 | *(Optional)* Demo day: live match vs. another student's AI (same or different game) | Week 10 |

---

## 7. Grading Rubric Summary

| Component | Weight |
|---|---|
| Weekly milestone completeness (D1–D6) | 30% |
| Minimax + alpha-beta correctness (F7–F8) | 20% |
| Heuristic design quality & creativity (F9) | 20% |
| Written analysis (A1–A3) | 20% |
| Code quality — modularity, decoupling, comments, tests (NF3–NF5) | 10% |

---

## 8. Academic Integrity

You are expected to write your own code. You may use AI tools to help you understand concepts, debug errors, or explore ideas — but the design decisions, heuristic choices, and written analysis must be your own original work. If you use AI assistance, note it briefly in your README.

---

## 9. Feature ID Quick Reference

| ID Range | Week | Focus |
|---|---|---|
| F1.1–F1.6 | Week 1 | Game Interface (the core contract) |
| F1.7–F1.9 | Week 1 | State representation & tree nodes |
| F2.1–F2.4 | Week 2 | Game tree construction & traversal |
| F3.1–F3.4 | Week 3 | Win detection & terminal states |
| F4.1–F4.4 | Week 4 | Game engine & human interface |
| F5.1–F5.5 | Week 5 | Random AI, timing & priority queues |
| F6.1–F6.5 | Week 6 | Serialization & move history |
| F7.1–F7.5 | Week 7 | Minimax AI |
| F8.1–F8.5 | Week 8 | Alpha-beta pruning & benchmarking |
| F9.1–F9.9 | Week 9 | Heuristic evaluation & tournament |
| F10.1–F10.7 | Week 10 | Final polish & submission |
| A1–A3 | Week 10 | Written analysis sections |
| NF1–NF7 | All | Non-functional requirements |
| D1–D8 | All | Deliverable milestones |
| T1–T5 | All | Technology requirements |
| G1–G5 | Week 1 | Game eligibility requirements |

---

## 10. Revision History

| Version | Date | Changes |
|---|---|---|
| 3.0 | 2026-09-08 | Version issued at the start of the term. |
| 3.1 | 2026-10-26 | Week 7: the Tic-Tac-Toe check now uses depth 6 or more, because a correct depth-4 Minimax can lose Tic-Tac-Toe (horizon effect). The F7.3/F7.4 acceptance criterion now says what depth 4 must do: take immediate wins and block immediate losses. Added guidance to score non-terminal depth-limit states as 0 until Week 9. Added this revision history. |
| 3.2 | 2026-11-03 | Week 8: benchmark results now go to `benchmark_results.csv`, with a set header, so they no longer mix with the per-move log. Week 10: added F10.5 (when saving and loading happen, and what the save file holds), F10.6 (the move history trie keeps all games in a run and reports opening counts), and F10.7 (`performance_log.csv` columns; settles "time" vs. "timestamp" in F5.4). A3 now asks for at least one claim backed by `performance_log.csv` or `benchmark_results.csv` data. |
| 3.3 | 2026-11-03 | Changes in response to the adversarial review of 3.2. G4: eligibility is now at least 4 legal moves from the starting position; the chess endgame note no longer requires transposition tables. Week 8: added search conventions (Player 1 score perspective, finished games checked before the depth limit, pass moves and no-move endings, ties). F8.1 says when to cut off; F8.4 benchmarks alpha-beta with and without ordering from the starting state, and `percent_reduction` is defined. Acceptance criteria now compare scores instead of exact moves, do not require ordering to help at every depth, and expect no reduction at depth 1. Week 9: terminal scores must outrank heuristic scores; the Connect Four example uses Player 1 and Player 2 instead of AI and opponent; F9.3 and F9.4/F9.6 have testable checks; the tournament takes a seed, randomizes each game's first two moves, and writes a defined `tournament_results.csv`; a difference between heuristics is no longer required. F10.3 lists required tests. NF1, NF5, and NF6 now say how they are measured. |
| 3.4 | 2026-11-03 | Search conventions moved from Week 8 to the start of Week 7, because students are just starting `minimax()`. They now require Player 1's point of view, removing the 3.3 option to keep a different one, and state for every game that a non-terminal state at the depth limit scores 0 until Week 9 (replacing the Connect Four example bullet). Week 9: F9.1 defines what `evaluate(state)` returns, and the scoring rules say heuristics belong with the game code and are passed to the search, which keeps it within NF4. F10.1: `--mode human` and `--mode benchmark` accept `--heuristic threat` or `--heuristic positional`. |

---

*Document version 3.4 | DSA Trees Module | Ten-Week Project | Generic Game-Playing Agent*

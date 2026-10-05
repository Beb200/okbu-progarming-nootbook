# Week 2 Defect Index — Caleb Payn

**Assignment:** Week 2 — Game Tree Construction & Traversal (F2.1–F2.4)  
**Reviewed version:** `payn-caleb/obu-programming-notebook`, all branches, as of 2026-09-27  
**Project folder:** `cis-3723-computer-science-2/ai-agent/ai-agent/`, **game:** Connect Four

## Findings

No Week 2 code was found when your repository was reviewed. Every branch was checked (`ai-agent-001`, `main`, and all remote branches), and none had a `generate_children`, `count_nodes`, BFS or DFS method. The latest AI-agent commit at the time was `fe3c05d` (2026-09-15, "fixed get_current_player ann added board"), which is Week 1 work.

Week 2 asks for:

| Requirement | What to build |
|---|---|
| F2.1 | `generate_children(node)`: one child node per legal move, using `get_legal_moves` and `apply_move`; no children for a won or full board. |
| F2.2 | `count_nodes(node, depth)`: recursively generates and counts nodes. From the empty board: 1, 8, 57, 400, 2801 for depths 0–4. |
| F2.3 | A BFS that prints the 49 positions at depth 2, as boards. |
| F2.4 | A DFS (a stack or recursion) that finds and prints the first terminal (won or drawn) position. |

The Week 3 defect index lists issues in your `GameState` that will also affect these (for example, `apply_move` changing the state it's given).
